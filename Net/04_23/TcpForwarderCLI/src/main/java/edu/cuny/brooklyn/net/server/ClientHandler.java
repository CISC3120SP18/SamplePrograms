package edu.cuny.brooklyn.net.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientHandler implements Runnable {
	private final static Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);
	
	public final static String BYE_BYE_MSG = "-1,-1,Bye bye";
	
	private int id;
	private Socket socket;
	private BufferedWriter writer;
	private BufferedReader reader;
	private MsgForwardService msgForwardService;
	private boolean running;

	public ClientHandler(int id, Socket socket) throws IOException {
		this.id = id;
		this.socket = socket;
		this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
		this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
		this.running = false;
	}

	public int getId() {
		return id;
	}

	@Override
	public void run() {
		validateMsgForwardService();

		running = true;
		while (running) {
			String msg = null;
			try {
				msg = reader.readLine();
			} catch (IOException e) {
				msgForwardService.remove(id);
				running = false;
				LOGGER.error("Cannot read message from client " + id + " : " + e.getMessage()
						+ " and removed the client from the listener.");
			}
			if (msg != null && msg.equals(BYE_BYE_MSG)) {
				running = false;
				msgForwardService.remove(id);
				close();
				LOGGER.info("Client " + id + " said Bye bye, and removed the client from the listener.");
			} else if (msg != null) {
				msgForwardService.forwardMsg(id, msg);
			}
		}
		LOGGER.info("Exited the ClientHandler for client " + id);
	}

	public void send(String msg) throws IOException {
		writer.write(msg);
		writer.newLine();
		writer.flush();
	}

	public void setMsgForwardService(MsgForwardService service) {
		if (service == null) {
			throw new IllegalArgumentException("MsgForwardService object must not be null");
		}
		msgForwardService = service;
	}

	public void close() {
		running = false;
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				LOGGER.error("Failed to close a socket/reader/writer: " + e.getMessage(), e);
			}
		}
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				LOGGER.error("Failed to close a socket/reader/writer: " + e.getMessage(), e);
			}
		}
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				LOGGER.error("Failed to close a socket/reader/writer: " + e.getMessage(), e);
			}
		}
	}

	private void validateMsgForwardService() {
		if (msgForwardService == null) {
			throw new IllegalStateException("MsgForwardService object must not be null");
		}
	}
}
