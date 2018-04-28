package edu.cuny.brooklyn.net.server;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.net.Protocol;

public class Listener {
	private final static Logger LOGGER = LoggerFactory.getLogger(Listener.class);

	private SocketAddress listenAddress;
	private boolean running;

	// resources that are needed to be released in this class
	private ServerSocketChannel serverSocketChannel;
	private Selector selector;

	private MsgForwardService msgForwardService;

	public Listener(SocketAddress listenAddress) {
		this.listenAddress = listenAddress;
		this.msgForwardService = new MsgForwardService();
		this.running = false;
	}

	public void open() throws IOException {
		selector = Selector.open();
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.bind(listenAddress);
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, serverSocketChannel);
	}

	public void run() throws IOException {

		int clientId = 0;
		running = true;
		LOGGER.debug("Waiting for I/O requests to come");
		while (running && selector.select() > 0) {
			LOGGER.debug("Selector saw an I/O request.");
			Set<SelectionKey> keySet = selector.selectedKeys();
			for (SelectionKey key : keySet) {
				if (key.isAcceptable()) {
					LOGGER.debug("The listener saw a connection request.");
					if (acceptClient(key, clientId)) {
						clientId++;
					}
				}

				if (key.isReadable()) {
					LOGGER.debug("The listener saw a channel is ready to read.");
					processClientMsg(key);
				}
			}
			keySet.clear();
			LOGGER.debug("Waiting for I/O requests to come");
		}
		LOGGER.info("Listener exited the listening loop.");
	}

	public void stop() {
		running = false;
		msgForwardService.stop();
		if (serverSocketChannel != null) {
			try {
				serverSocketChannel.close();
			} catch (IOException e) {
				LOGGER.error("Failed to close the server socket channel: " + e.getMessage(), e);
			}
		}
		if (selector != null) {
			try {
				selector.close();
			} catch (IOException e) {
				LOGGER.error("Failed to close the selector: " + e.getMessage(), e);
			}
		}
	}

	private boolean acceptClient(SelectionKey key, int clientId) {
		boolean success = false;
		SocketChannel clientChannel = null;
		try {
			clientChannel = ((ServerSocketChannel) key.channel()).accept();
			ClientHandler clientHandler = new ClientHandler(clientId, clientChannel);
			msgForwardService.addClient(clientHandler);
			LOGGER.debug("Created client " + clientId);

			clientChannel.configureBlocking(false);
			clientChannel.register(selector, SelectionKey.OP_READ, clientHandler);
			LOGGER.debug("Registered client " + clientId + " with the selector object.");

			success = true;
		} catch (IOException e) {
			LOGGER.error("The listener failed to accept the client.", e);
		}

		return success;
	}

	private void processClientMsg(SelectionKey key) {
		ClientHandler client = (ClientHandler) key.attachment();
		try {
			List<String> lines = client.receive();

			for (String line : lines) {
				if (Protocol.isClosingRequest(line)) {
					msgForwardService.remove(client.getId());
					LOGGER.debug("Upon the client's request, the listerner removed client " + client.getId());
				} else {
					String msg = msgForwardService.forwardMsg(client.getId(), line);
					LOGGER.debug("Listerner forwarded message from " + client.getId() + " with message " + msg);
				}
			}
		} catch (IOException e) {
			LOGGER.error("Client failed to read a message: " + e.getMessage(), e);
			msgForwardService.remove(client.getId());
			key.channel();
			LOGGER.debug("Due to client's failure, the listerner removed client " + client.getId());
		}
	}
}
