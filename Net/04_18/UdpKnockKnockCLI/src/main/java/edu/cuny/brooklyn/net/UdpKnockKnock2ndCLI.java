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

public class UdpKnockKnock2ndCLI {
	private final static Logger LOGGER = LoggerFactory.getLogger(UdpKnockKnock2ndCLI.class);

	private final static int REMOTE_PORT = 60101;
	private final static String REMOTE_ADDRESS = "127.0.0.1";
	private final static int BUFFER_LENGTH = 1024;

	public static void main(String[] args) {
		LOGGER.info("G: guest starts.");

		String remoteAddress = REMOTE_ADDRESS;
		int remotePort = REMOTE_PORT;
		if (args.length > 0) {
			remoteAddress = args[0];
		}
		if (args.length > 1) {
			remotePort = Integer.parseInt(args[1]);
		}
		

		SocketAddress address = null;
		try {
			address = new InetSocketAddress(InetAddress.getByName(remoteAddress), remotePort);
		} catch (UnknownHostException e) {
			System.out.println("G: client cannot resolve local address " + remoteAddress);
			LOGGER.error("Cannot resolve local address " + remoteAddress, e);
			System.exit(-1);
		}

		try (DatagramSocket socket = new DatagramSocket()) {
			socket.setReuseAddress(true);
			byte buf[] = new byte[BUFFER_LENGTH];
			DatagramPacket packet = new DatagramPacket(buf, buf.length, address);

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
