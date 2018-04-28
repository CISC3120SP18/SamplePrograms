package edu.cuny.brooklyn.net.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.net.Protocol;

public class ClientHandler {
	private final static Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);
	private final static int BUF_SIZE = 1024;

	private int id;
	private SocketChannel socketChannel;
	private ByteBuffer sockInBuffer;
	private StringBuilder inSb;

	public ClientHandler(int id, SocketChannel clientChannel) throws IOException {
		this.id = id;
		this.socketChannel = clientChannel;
		sockInBuffer = ByteBuffer.allocate(BUF_SIZE);
		inSb = new StringBuilder();
	}

	public int getId() {
		return id;
	}

	public List<String> receive() throws IOException {
		socketChannel.read(sockInBuffer);
		LOGGER.debug("has remaining before flip: " + sockInBuffer.remaining());
		sockInBuffer.flip();
		LOGGER.debug("has remaining after flip: " + sockInBuffer.remaining());
		List<String> lines = readLines(sockInBuffer);
		sockInBuffer.compact();
		return lines;
	}

	public void send(String msg) throws IOException {
		ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
		LOGGER.debug("has remaining before flip: " + sockInBuffer.remaining());
		sockInBuffer.flip();
		LOGGER.debug("has remaining after flip: " + sockInBuffer.remaining());
		while (buffer.hasRemaining()) {
			socketChannel.write(buffer);
		}
	}

	public void close() {
		if (socketChannel != null) {
			try {
				socketChannel.close();
			} catch (IOException e) {
				LOGGER.error("Failed to close the socket channel: " + e.getMessage(), e);
			}
		}
	}
	
	private List<String> readLines(ByteBuffer buffer) {
		List<String> lines = new LinkedList<String>();
		
		int numBytes = buffer.limit() - buffer.position();
		byte[] bytes = new byte[numBytes];
		for (int i=0; i<numBytes; i ++) {
			bytes[i] = buffer.get();
		}
		LOGGER.debug("Has remaining: " + buffer.remaining());
		
		String messages = new String(bytes, Protocol.getCharset());
		for (int i=0; i<messages.length(); i++) {
			char c = messages.charAt(i);
			if (Protocol.isLineBreak(c)) {
				String s = inSb.toString();
				lines.add(s);
				LOGGER.debug("Read line: " + s);
				inSb.setLength(0);
			} else {
				if (!Protocol.shouldDrop(c)) {
					inSb.append(c);
				}
			}
		}
		if (inSb.length() > 0) {
			LOGGER.debug("Partial line: " + inSb.toString());
		}
		return lines;
	}
}
