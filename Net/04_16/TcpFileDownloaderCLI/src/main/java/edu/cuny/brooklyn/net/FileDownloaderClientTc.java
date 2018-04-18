package edu.cuny.brooklyn.net;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileDownloaderClientTc {
	private final static Logger LOGGER = LoggerFactory.getLogger(FileDownloaderClientTc.class);
	
	private static final String SERVER_ADDRESS = "127.0.0.1";
	private static final int SERVER_PORT = 60101;

	public static void main(String[] args) {
		String serverAddress = SERVER_ADDRESS;
		int serverPort = SERVER_PORT;
		
		if (args.length > 0) {
			serverAddress = args[0];
		}
		
		if (args.length > 1) {
			serverPort = Integer.parseInt(args[1]);
		}
		
		Socket socket = new Socket();
		/*
		 * create a socket, representing a connection at the client, we now
		 * print out the two end points of the connection.
		 */
		System.out.println("Before successful connection to the server: "
				+ "The IP address of the local end point of the connection: " + socket.getLocalAddress());
		System.out.println("Before successful connection to the server: "
				+ "The TCP port number of the local end point of the connection: " + socket.getLocalPort());
		System.out.println("Before successful connection to the server: "
				+ "The IP address of the remote end point of the connection: " + socket.getInetAddress());
		System.out.println("Before successful connection to the server: "
				+ "The TCP port number of the remote end point of the connection: " + socket.getPort());
		InputStream in = null;
		OutputStream out = null;
		try {
			SocketAddress serverEndPoint = new InetSocketAddress(InetAddress.getByName(serverAddress), serverPort);
			socket.connect(serverEndPoint);

			/*
			 * the socket representing a connection at the client, after the successful
			 * connection to the server, the local end point is set by the platform,
			 * and the remote end point is the end point to which the client connected.
			 * We now print out the two end points of the connection.
			 */
			System.out.println("After successful connection to the server: "
					+ "The IP address of the local end point of the connection: " + socket.getLocalAddress());
			System.out.println("After successful connection to the server: "
					+ "The TCP port number of the local end point of the connection: " + socket.getLocalPort());

			System.out.println("After successful connection to the server: "
					+ "The IP address of the remote end point of the connection: " + socket.getInetAddress());
			System.out.println("After successful connection to the server: "
					+ "The TCP port number of the remote end point of the connection: " + socket.getPort());

			/* the socket can be either the source or the destination of an I/O stream */
			in = socket.getInputStream();
			out = new FileOutputStream("files/demo1/file1dup.txt");
			int c;
			while ((c = in.read()) != -1) {
				out.write((byte) c);
			}
			if (out != null) { 
				out.close();
				out = null;
			}
			System.out.println("File is downloaded to files/demo1/file1dup.txt");
		} catch (IOException e) {
			LOGGER.error("File I/O or network I/O error.", e);
			System.out.println("File I/O or network I/O error.");
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					LOGGER.error("failed to close socket.", e);
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					LOGGER.error("I/O error.", e);
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					LOGGER.error("I/O error.", e);
				}
			}
		}
	}
}
