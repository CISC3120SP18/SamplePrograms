package edu.cuny.brooklyn.net;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpMessengerOnewayServer {
	private final static int SERVER_LISTENING_PORT = 62110;
	private final static int SERVER_BACKLOG = 5;
	private final static String SERVER_BIND_ADDRESS = "0.0.0.0";

	public static void main(String[] args) throws IOException {
		System.out.println("Server stats.");
		InetAddress serverBindAddress = InetAddress.getByName(SERVER_BIND_ADDRESS);
		try (ServerSocket listeningSocket = new ServerSocket(SERVER_LISTENING_PORT, SERVER_BACKLOG,
				serverBindAddress)) {
			listeningSocket.setReuseAddress(true);
			try (Socket socketCommWithClient = listeningSocket.accept();
					PrintWriter writer = new PrintWriter(new OutputStreamWriter(socketCommWithClient.getOutputStream()));
					Scanner scanner = new Scanner(System.in);) {
				socketCommWithClient.setReuseAddress(true);
				writer.println("Hello, client. I am the server.");
				writer.flush();
				System.out.println("Server: " + "Hello, client. I am the server.");
				String msg;
				System.out.print("Enter Message ('Bye bye' to exit): ");
				while ((msg = scanner.nextLine()) != null) {
					writer.println(msg);
					writer.flush();
					System.out.print("Enter Message ('Bye bye' to exit): ");
				}
			}
		}
	}
}
