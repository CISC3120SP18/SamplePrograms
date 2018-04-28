package edu.cuny.brooklyn.net.client.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.net.Protocol;

public class InputMessageHandler implements Runnable {
	private final static Logger LOGGER = LoggerFactory.getLogger(InputMessageHandler.class);
	private final static int BUF_SIZE = 1024;
	private SocketChannel channel;
	private boolean running;
	private ByteBuffer inBuffer;

	public InputMessageHandler(SocketChannel channel) {
		this.channel = channel;
		this.running = false;
		this.inBuffer = ByteBuffer.allocate(BUF_SIZE);
	}

	public void close() {
		if (channel != null) {
			try {
				channel.close();
			} catch (IOException e) {
				LOGGER.error("Cannot close the channel.", e);
			}
		}
		running = false;
	}

	@Override
	public void run() {
		LOGGER.info("Start the InputMessageHandler");
		running = true;
		while (running) {
			try {

				channel.read(inBuffer);
				String line = new String(inBuffer.array(), Protocol.getCharset()).trim();
				String[] parts = line.split(",");
				if (parts.length < 3) {
					LOGGER.error("Unparsable message: " + line);
				} else {
					System.out.println("Received a message from the forwarder: "  + String.join(",", parts));
					LOGGER.debug("Received a message from the forwarder: "  + String.join(",", parts));
				}

			} catch (IOException e) {
				LOGGER.error("Cannot open reader for the socket: " + e.getMessage());
				running = false;
			}
		}
		LOGGER.info("Exit the InputMessageHandler");
	}
}
