package edu.cuny.brooklyn.net.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputMessageHandler implements Runnable {
	private final static Logger LOGGER = LoggerFactory.getLogger(InputMessageHandler.class);
	private Socket socket;
	private boolean running;
	private BufferedReader reader;

	public InputMessageHandler(Socket socket) {
		this.socket = socket;
		this.running = false;
	}
	
	public void close() {
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				LOGGER.error("Cannot close the reader.", e);
			}
		}
		running = false;
	}

	@Override
	public void run() {
		running = true;
		while (running) {
			try {
				reader = new BufferedReader(
						new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
				String line;
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split(",");
					if (parts.length < 3) {
						LOGGER.error("Unparsable message: " + line);
					} else {
						System.out.println("Received a message (" + parts[0] + " >> " + parts[1] + "): " + parts[2]);
					}
				}
			} catch (IOException e) {
				LOGGER.error("Cannot open reader for the socket: " + e.getMessage());
				running = false;
			}
		}
		LOGGER.info("Exit the InputMessageHandler");
	}
}
