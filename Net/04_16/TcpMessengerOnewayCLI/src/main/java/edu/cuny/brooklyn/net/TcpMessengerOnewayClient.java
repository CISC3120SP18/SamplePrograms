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
		System.out.println("Client stats.");
		try (Socket socket = new Socket()) {
			SocketAddress serverSocketAddress = new InetSocketAddress(InetAddress.getByName(SERVER_ADDRESS),
					SERVER_PORT);
			socket.setReuseAddress(true);
			socket.connect(serverSocketAddress);
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
				String msg;
				System.out.println("Client waiting for msg");
				while ((msg = reader.readLine()) != null) {
					System.out.println("Server: " + msg);
					if (msg.equals("Bye bye")) {
						break;
					}
				}
			}
		}
	}
}
