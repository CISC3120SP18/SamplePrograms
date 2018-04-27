package edu.cuny.brooklyn.net.server;

import java.io.IOException;
import java.net.InetAddress;
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

		try {
			InetAddress inetAddress = InetAddress.getByName(args[0]);
			int port = Integer.parseInt(args[1]);

			Listener listener = new Listener(inetAddress, port, 5);
			LOGGER.info("The listener is instantiated.");			

			Thread th = new Thread(listener);
			th.setDaemon(true);
			th.start();
			LOGGER.info("The listener is listenting to " + inetAddress.getHostAddress() + ":" + port);
			
			th.join();
			LOGGER.info("The listener exited.");
		} catch (UnknownHostException e) {
			LOGGER.error(args[0] + " is an unknown host.");
		} catch (IOException e) {
			LOGGER.error("Failed to create the listener: " + e.getMessage(), e);
		} catch (InterruptedException e) {
			LOGGER.error("Listener was interrupted: " + e.getMessage());
		}

		LOGGER.info("TcpForwarderServerApp exits.");
	}

}
