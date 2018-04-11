package edu.cuny.brooklyn.net;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileDownloaderServerTc {
	private final static Logger LOGGER = LoggerFactory.getLogger(FileDownloaderServerTc.class);
	
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
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		InputStream in = null;
		OutputStream out = null;
		try {
			InetAddress localAddress = InetAddress.getByName(address);
			serverSocket = new ServerSocket(port, BACKLOG, localAddress);
			/*
			 * we have no means to terminate the app to do proper clean up. the following
			 * reduces the chance of the
			 * "java.net.BindException: Address already in use: JVM_Bind" error when we
			 * terminated the application from the OS or the IDE
			 */
			serverSocket.setReuseAddress(true);
			System.out.format("Server is listen to port %s at host %s%n", serverSocket.getLocalPort(),
					serverSocket.getInetAddress());
			socket = null;
			while ((socket = serverSocket.accept()) != null) {
				socket.setReuseAddress(true);
				/*
				 * the socket returned by accept() represents a connection at the server and the
				 * connection is for I/O, after the acceptance of the connection from the
				 * client. the local end point is set by the platform, and the remote end point
				 * is the end point from which the client was connected from. We now print out
				 * the two end points of the connection.
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
				in = new FileInputStream("files/demo1/file1.txt");
				out = socket.getOutputStream();
				int c;
				while ((c = in.read()) != -1) {
					out.write((byte) c);
				}
			}
			/* close streams: why do we also close them in the finally block? is it necessary? */
			if (out != null) {
				out.close();
				out = null;
			}
			if (in != null) {
				in.close();
				in = null;
			}
		} catch (IOException e) {
			LOGGER.error("File I/O or network I/O error.", e);
			System.out.println("File I/O or network I/O error.");
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					LOGGER.error("Failed to close the server socket.", e);
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					LOGGER.error("Failed to close the socket.", e);
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					LOGGER.error("Failed to close the output stream.", e);
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					LOGGER.error("Failed to close the input stream.", e);
				}
			}
		}

	}

}
