package edu.cuny.brooklyn.net.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpForwarderServerApp {
	private final static Logger LOGGER = LoggerFactory.getLogger(TcpForwarderServerApp.class);

	public static void main(String[] args) {
		LOGGER.info("TcpForwarderServerApp starts.");

		if (args.length < 2) {
			System.out.println("Usage: TcpForwarderServerApp <bind_ip> <bind_port>");
			System.exit(-1);
		}

		Listener listener = null;
		try {
			int port = Integer.parseInt(args[1]);
			SocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getByName(args[0]), port);


			listener = new Listener(inetSocketAddress);
			LOGGER.info("The listener is instantiated.");			

			listener.open();
			LOGGER.info("The listener is listenting to " + inetSocketAddress.toString());
			
			LOGGER.info("The listener is looping.");
			listener.run();
			
			LOGGER.info("The listener exited.");
		} catch (UnknownHostException e) {
			LOGGER.error(args[0] + " is an unknown host.");
		} catch (IOException e) {
			LOGGER.error("Failed to create the listener: " + e.getMessage(), e);
			if (listener != null) {
				listener.stop();
			}
		} 

		LOGGER.info("TcpForwarderServerApp exits.");
	}

}
