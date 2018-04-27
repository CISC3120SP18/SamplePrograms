package edu.cuny.brooklyn.net.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Listener implements Runnable {
	private final static Logger LOGGER = LoggerFactory.getLogger(Listener.class);
	private InetAddress listenAddress;
	private int listenPort;
	private int backlog;
	private ServerSocket serverSocket;
	private boolean running;
	private MsgForwardService msgForwardService;

	public Listener(InetAddress listenAddress, int port, int backlog) throws IOException {
		this.listenAddress = listenAddress;
		this.listenPort = port;
		this.backlog = backlog;
		this.serverSocket = new ServerSocket(this.listenPort, this.backlog, this.listenAddress);
		this.running = false;
		this.msgForwardService = new MsgForwardService();
	}

	@Override
	public void run() {
		int clientId = 0;
		running = true;
		while (running) {
			try {
				// 1. get a socket connection between the server and the client
				Socket clientSocket = serverSocket.accept();
				LOGGER.info("The listener accepted a connection.");

				// 2. set up a client
				ClientHandler clientRunnable = new ClientHandler(clientId, clientSocket);
				System.out.println("Accepted client " + clientId);
				clientId++; // what if another connection comes before it completes the setup?

				msgForwardService.addClient(clientRunnable);
				clientRunnable.setMsgForwardService(msgForwardService);

				// 3. run the client
				Thread th = new Thread(clientRunnable);
				th.setDaemon(true);
				th.start();
			} catch (IOException e) {
				LOGGER.error("Failed to accept a connection: " + e.getMessage(), e);
			}
		}
	}

	public void stop() {
		running = false;
		msgForwardService.stop();
		if (serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				LOGGER.error("Failed to close the server socket: " + e.getMessage(), e);
			}
		}
	}
}
