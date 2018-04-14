package edu.cuny.brooklyn.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UdpConnKnockKnock2ndCLI {
	private final static Logger LOGGER = LoggerFactory.getLogger(UdpConnKnockKnock2ndCLI.class);

	private final static int REMOTE_PORT = 60101;
	private final static String REMOTE_ADDRESS = "127.0.0.1";
	private final static int LOCAL_PORT = 60102;
	private final static String LOCAL_ADDRESS = "127.0.0.1";
	private final static int BUFFER_LENGTH = 1024;

	public static void main(String[] args) {
		LOGGER.info("G: guest starts.");

		String localAddress = LOCAL_ADDRESS;
		int localPort = LOCAL_PORT;
		String remoteAddress = REMOTE_ADDRESS;
		int remotePort = REMOTE_PORT;
		if (args.length > 0) {
			localAddress = args[0];
		}
		if (args.length > 1) {
			localPort = Integer.parseInt(args[1]);
		}
		if (args.length > 2) {
			remoteAddress = args[2];
		}
		if (args.length > 3) {
			remotePort = Integer.parseInt(args[3]);
		}
		

		SocketAddress localSocketAddress = null;
		SocketAddress remoteSocketAddress = null;		
		try {
			localSocketAddress = new InetSocketAddress(InetAddress.getByName(localAddress), localPort);
			remoteSocketAddress = new InetSocketAddress(InetAddress.getByName(remoteAddress), remotePort);			
		} catch (UnknownHostException e) {
			System.out.println("G: client cannot resolve local address " + remoteAddress);
			LOGGER.error("Cannot resolve local address " + remoteAddress, e);
			System.exit(-1);
		}

		try (DatagramSocket socket = new DatagramSocket(localSocketAddress)) {
			socket.setReuseAddress(true);
			socket.connect(remoteSocketAddress);
			
			/*
			 * UDP is not connection-oriented. By default, the remote end point
			 * of the socket is not set. In this case, the socket can receive
			 * packets from any remote end points. We print out the both end
			 * points of the UDP socket. 
			 * 
			 * Note that DatagramSocket has a connect() method. The semantics
			 * of the connect is different from TCP socket's connection 
			 * method. The connect method here is merely to set the remote
			 * end point of the socket to a designated one. Once the remote
			 * end point is set, the  socket can only receive from or
			 * write to the designated remote end point other than any where
			 * else. Because of this semantics. one may call connect() 
			 * at both hosts of the communication. 
			 */
			socket.connect(remoteSocketAddress);
			LOGGER.debug("G: Local address before receive: " + socket.getLocalAddress());
			LOGGER.debug("G: Local UDP port number before receive: " + socket.getLocalPort());
			LOGGER.debug("G: Remote address before receive: " + socket.getInetAddress());
			LOGGER.debug("G: Remote UDP port number before receive: " + socket.getPort());
			
			byte buf[] = new byte[BUFFER_LENGTH];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);

			packet.setData("Knock, knock.".getBytes());
			socket.send(packet);
			System.out.println("G >>>> H:  " + new String(packet.getData()));

			/*
			 * you can resue the packet object, but make sure the receiving buffer is
			 * set/reset; other wise received data may get truncated due to length set from
			 * previous operation
			 */
			packet.setData(buf);
			socket.receive(packet);
			System.out.println("G <<<< H: " + new String(packet.getData()));

			packet.setData("Anna Partridge.".getBytes());
			socket.send(packet);
			System.out.println("G >>>> H:  " + new String(packet.getData()));

			/*
			 * you can resue the packet object, but make sure the receiving buffer is
			 * set/reset; other wise received data may get truncated due to length set from
			 * previous operation
			 */
			packet.setData(buf);
			socket.receive(packet);
			System.out.println("G <<<< H: " + new String(packet.getData()));
			
			packet.setData("Anna Partridge in a pear tree!".getBytes());
			socket.send(packet);
			System.out.println("G >>>> H:  " + new String(packet.getData()));
		} catch (SocketException e) {
			System.out.println("G: socket error.");
			LOGGER.error("G: socket error.", e);
		} catch (IOException e) {
			System.out.println("G: I/O error.");
			LOGGER.error("G: I/O error.", e);
		}
		LOGGER.info("G: guest ends.");
	}
}
