package edu.cuny.brooklyn.net.client.stream;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.net.Protocol;

public class OutputMessageHandler implements Runnable {
	private final static Logger LOGGER = LoggerFactory.getLogger(OutputMessageHandler.class);
	private Socket socket;
	private boolean running;
	private BufferedWriter writer;
	private Scanner scanner;

	public OutputMessageHandler(Socket socket) {
		this.socket = socket;
		this.running = false;
	}

	public void close() {
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				LOGGER.error("Cannot close the reader.", e);
			}
		}
		running = false;
	}

	@Override
	public void run() {
		LOGGER.info("OutputMmessageHandler starts running.");
		running = true;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
			scanner = new Scanner(System.in);
			String line;
			System.out.println("Enter a message: ");
			while (running && (line = scanner.nextLine()) != null) {
				System.out.println("Sending a message to a random client: " + line);
				
				String msg = "-1,-1," + line;
				writer.write(msg);
				writer.newLine();
				writer.flush();
				
				LOGGER.debug("Sent to the forwarder: " + msg);
				
				System.out.print("Enter a message: ");
				if (Protocol.isClosingRequest(msg)) {
					LOGGER.debug("Client entered Bye bye.");
					running = false;
				}
			}
		} catch (IOException e) {
			LOGGER.error("Cannot open reader for the socket: " + e.getMessage(), e);
			running = false;
			scanner.close();
		}
		System.out.println("OutputMmessageHandler exits.");
		LOGGER.info("Exiting the OutputMessageHandler.");
	}
}
