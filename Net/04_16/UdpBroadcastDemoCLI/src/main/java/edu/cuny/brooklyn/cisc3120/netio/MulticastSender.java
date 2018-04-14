package edu.cuny.brooklyn.cisc3120.netio;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;

public class MulticastSender {
	private final static int BUFFER_SIZE = 1024;
	// Use TEST-NET-1 for the group address
	private final static String MULTICAST_GROUP_ADDRESS_1 = "224.0.0.0";
	private final static String MULTICAST_GROUP_ADDRESS_2 = "224.0.0.1";
	private final static int MULTICAST_SENDING_PORT = 60202;

	private final static int SERVER_PORT = 60201;
	private final static String SERVER_BIND_ADDRESS = "0.0.0.0";

	public static void main(String[] args) throws IOException {
		SocketAddress address = new InetSocketAddress(InetAddress.getByName(SERVER_BIND_ADDRESS), SERVER_PORT);
		try (DatagramSocket socket = new DatagramSocket(address)) {

			byte buf[] = new byte[BUFFER_SIZE];
			InetAddress group = InetAddress.getByName(MULTICAST_GROUP_ADDRESS_1);
			DatagramPacket packet = new DatagramPacket(buf, buf.length, group, MULTICAST_SENDING_PORT);
			packet.setData(String.format("Group %s, Howdy?", MULTICAST_GROUP_ADDRESS_1).getBytes());
			socket.send(packet);

			group = InetAddress.getByName(MULTICAST_GROUP_ADDRESS_2);
			packet = new DatagramPacket(buf, buf.length, group, MULTICAST_SENDING_PORT);
			packet.setData(String.format("Group %s, Howdy?", MULTICAST_GROUP_ADDRESS_2).getBytes());
			socket.send(packet);
		}
	}
}
