package edu.cuny.brooklyn.net;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileDownloaderServerTwr {
	private final static Logger LOGGER = LoggerFactory.getLogger(FileDownloaderServerTwr.class);

	private final static int PORT = 60101;
	private final static int BACKLOG = 5;
	private final static String ADDRESS = "127.0.0.1";

	public static void main(String[] args) {
		String address = ADDRESS;
		int port = PORT;
		
		if (args.length > 0) {
			address = args[0];
		}
		
		if (args.length > 1) {
			port = Integer.parseInt(args[1]);
		}
		
		InetAddress localAddress = null;
		try {
			localAddress = InetAddress.getByName(address);
		} catch (UnknownHostException e) {
			System.out.format("Cannot resolve %s", address);
			LOGGER.error("Failed to resolve " + address, e);
			System.exit(-1);
		}

		try (ServerSocket serverSocket = new ServerSocket(port, BACKLOG, localAddress)) {
			/*
			 * we have no means to terminate the app to do proper clean up. the following
			 * reduces the chance of the
			 * "java.net.BindException: Address already in use: JVM_Bind" error when we
			 * terminated the application from the OS or the IDE
			 */
			serverSocket.setReuseAddress(true);
			System.out.format("Server is listen to port %s at host %s%n", serverSocket.getLocalPort(),
					serverSocket.getInetAddress());
			boolean connectionSuccess = false;
			do {
				try (Socket socket = serverSocket.accept()) {
					if (socket != null) {
						socket.setReuseAddress(true);
						connectionSuccess = true;

						/*
						 * the socket returned by accept() represents a connection at the server and the
						 * connection is for I/O, after the acceptance of the connection from the
						 * client. the local end point is set by the platform, and the remote end point
						 * is the end point from which the client was connected from. We now print out
						 * the two end points of the connection.
						 */
						System.out.println("After successful connection to the server: "
								+ "The IP address of the local end point of the connection: "
								+ socket.getLocalAddress());
						System.out.println("After successful connection to the server: "
								+ "The TCP port number of the local end point of the connection: "
								+ socket.getLocalPort());

						System.out.println("After successful connection to the server: "
								+ "The IP address of the remote end point of the connection: "
								+ socket.getInetAddress());
						System.out.println("After successful connection to the server: "
								+ "The TCP port number of the remote end point of the connection: " + socket.getPort());

						/* the socket can be either the source or the destination of an I/O stream */
						try (InputStream in = new FileInputStream("files/demo1/file1.txt");
								OutputStream out = socket.getOutputStream()) {
							int c;
							while ((c = in.read()) != -1) {
								out.write((byte) c);
							}
						}
					} else {
						connectionSuccess = false;
					}
				}
			} while (connectionSuccess);
		} catch (IOException e) {
			System.out.println("Input/Ouput error");
			LOGGER.error("Input/Output error", e);
		}
	}

}
