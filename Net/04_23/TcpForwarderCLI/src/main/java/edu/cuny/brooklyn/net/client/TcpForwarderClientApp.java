package edu.cuny.brooklyn.net.client;

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

		/*
		 * TODO: create two threads to complete the application. One thread is to receive an input
		 * 		from the Standard Input and send the message to the server via a TCP connection, and 
		 * 		the other is to receive a message from	 the server, and displays it in the Standard
		 * 		output. 
		 * 
		 *  	To complete the TODO, follow the steps below,
		 *  	1. create an InetSocketAddress object from the two required command line arguments;
		 *  		(the InetSocketAddress is the remote endpoint of a TCP connection, i.e., the
		 *  		the server process the client (this process) is connects to)
		 *  	2. create a socket object
		 *  	3. connect to the server using the InetSocketAddress object created in Step 1
		 *  	4. create a Thread object from the InputMessageHandler class (that is a Runnable),
		 *  		and run/start the thread (this thread is to receive a messsage from the server,
		 *  		and the "Input" means to "input" from the server);
		 *  	5. create a Thread object from the OutputMessageHandler class (that is a Runnable),
		 *  		and run/start the thread (this thread is to receive an input from the user
		 *  		and send the input to the server, the "Output" means to "output" to the server.)
		 *  	6. set up a barrier to wait for the OutputMessageHandler thread to die/exit. 
		 */

		System.out.println("TcpForwarderClientApp ends.");
	}

}
