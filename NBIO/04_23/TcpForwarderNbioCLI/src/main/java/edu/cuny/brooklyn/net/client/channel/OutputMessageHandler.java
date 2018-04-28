package edu.cuny.brooklyn.net.client.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.net.Protocol;

public class OutputMessageHandler implements Runnable {
	private final static Logger LOGGER = LoggerFactory.getLogger(OutputMessageHandler.class);
	private SocketChannel channel;
	private boolean running;
	private Scanner scanner;
	private ByteBuffer outBuffer;

	public OutputMessageHandler(SocketChannel channel) {
		this.channel = channel;
		this.running = false;
	}

	public void close() {
		if (channel != null) {
			try {
				channel.close();
			} catch (IOException e) {
				LOGGER.error("Cannot close the reader.", e);
			}
		}
		running = false;	}

	@Override
	public void run() {
		LOGGER.info("Start the OutputMessageHandler");
		running = true;
		try {
			scanner = new Scanner(System.in);
			String line;
			System.out.println("Enter a message: ");
			while (running && (line = scanner.nextLine()) != null) {
				System.out.println("Sending a message to a random client: " + line);
				
				String msg = "-1,-1," + line + Protocol.getLineBreak();
				
				outBuffer = ByteBuffer.wrap(msg.getBytes());
				channel.write(outBuffer);
				
				LOGGER.debug("Sent to the forwarder: " + msg);
				
				System.out.print("Enter a message: ");
				if (Protocol.isClosingRequest(msg)) {
					LOGGER.debug("Client entered Bye bye.");
					running = false;
				}
			}
		} catch (IOException e) {
			LOGGER.error("Cannot write to the channel: " + e.getMessage(), e);
			running = false;
			scanner.close();
		}
		System.out.println("OutputMmessageHandler exits.");
		LOGGER.info("Exiting the OutputMessageHandler.");
	}
}
