package edu.cuny.brooklyn.net.client.stream;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpForwarderClientApp {
	private final static Logger LOGGER = LoggerFactory.getLogger(TcpForwarderClientApp.class);

	public static void main(String[] args) {
		LOGGER.info("TcpForwarderClientApp starts.");

		if (args.length < 2) {
			System.out.println("Usage: TcpForwarderServerApp <server_ip> <server_port>");
			System.exit(-1);
		}

		try {
			InetAddress inetAddress = InetAddress.getByName(args[0]);
			int port = Integer.parseInt(args[1]);
			SocketAddress inetSocketAddress = new InetSocketAddress(inetAddress, port);

			try (Socket socket = new Socket()) {
				socket.connect(inetSocketAddress);
				
				InputMessageHandler inRunnable = new InputMessageHandler(socket);
				Thread inTh = new Thread(inRunnable);
				inTh.setDaemon(true);
				inTh.start();
				
				OutputMessageHandler outRunnable = new OutputMessageHandler(socket);
				Thread outTh = new Thread(outRunnable);
				outTh.setDaemon(true);
				outTh.start();

				outTh.join();
			} catch (InterruptedException e) {
				LOGGER.error("Threads interrupted.", e);
			}

		} catch (UnknownHostException e) {
			System.err.println(args[0] + " is an unknown host.");
		} catch (IOException e) {
			System.err.println("I/O error: " + e.getMessage());
		}

		System.out.println("TcpForwarderClientApp ends.");
	}

}
