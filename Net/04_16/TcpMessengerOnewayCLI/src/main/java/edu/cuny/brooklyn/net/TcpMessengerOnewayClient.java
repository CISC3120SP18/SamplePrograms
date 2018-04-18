package edu.cuny.brooklyn.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class TcpMessengerOnewayClient {
	public final static String SERVER_ADDRESS = "127.0.0.1";
	public final static int SERVER_PORT = 62110;

	public static void main(String[] args) throws IOException {
		System.out.println("Client starts.");
		try (Socket socket = new Socket()) {
			SocketAddress serverSocketAddress = new InetSocketAddress(InetAddress.getByName(SERVER_ADDRESS),
					SERVER_PORT);
			socket.setReuseAddress(true);
			// TODO: 
			//	1. connect to the server, get the socket
			//	2. create a reader from the socket
			//	3. repeatedly read lines of messages from the reader
			//	4. print out the message received
			//	5. if the message is "Bye bye", exit the loop and the application
		}
	}
}
