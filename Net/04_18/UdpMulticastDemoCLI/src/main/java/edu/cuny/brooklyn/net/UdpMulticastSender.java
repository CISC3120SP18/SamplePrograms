package edu.cuny.brooklyn.net;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.util.Random;

public class UdpMulticastSender {
	// Use TEST-NET-1 for the group address
	private final static String MULTICAST_GROUP_1_ADDRESS = "224.0.0.0";
	private final static String MULTICAST_GROUP_2_ADDRESS = "224.0.0.1";
	private final static int MULTICAST_PORT = 60202;


	public static void main(String[] args) throws IOException {
		int[] multicastPort = {MULTICAST_PORT, MULTICAST_PORT};
		String[] multicastGroupAddress = {MULTICAST_GROUP_1_ADDRESS, MULTICAST_GROUP_2_ADDRESS};
		
		if (args.length != 0 && args.length != 4) {
			System.out.println("Usage: UdpMulticastSender" + System.getProperty("line.separator") +
					"or" + System.getProperty("line.separator") +
					"Usage: UdpMulticastSender MULTICAST_GROUP_1_ADDRESS MULTICAST_GROUP_1_PORT " +
					"MULTICAST_GROUP_2_ADDRESS MULTICAST_GROUP_2_PORT");
			System.exit(-1);
		}
		
		for (int i=0; args.length == 4 && i < args.length; i+=2) {
			multicastGroupAddress[i/2] = args[i];
			multicastPort[i/2] = Integer.parseInt(args[i+1]);
		}
		
		try (MulticastSocket socket = new MulticastSocket()) {
			socket.setReuseAddress(true);
			byte[] buf = generateDataBytes();
			DatagramPacket packet;
			for (int i=0; i<2; i++) {
				InetSocketAddress groupAddress = new InetSocketAddress(multicastGroupAddress[i], multicastPort[i]);
				packet = new DatagramPacket(buf, buf.length, groupAddress);
				socket.send(packet);
			}
		}
	}

	private static byte[] generateDataBytes() throws IOException {
		byte[] buf = null;
		Random rng = new Random();
		try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
				DataOutputStream dataOut = new DataOutputStream(byteOut)) {
			int numNumbers = rng.nextInt(10) + 1;
			System.out.println("Number of numbers to send: " + numNumbers);
			dataOut.writeInt(numNumbers);
			for (int i = 0; i < numNumbers; i++) {
				int num = rng.nextInt(100) + 1;
				dataOut.writeInt(num);
				System.out.println("Number[" + i + "] = " + num);
			}
			buf = byteOut.toByteArray();
		}
		return buf;
	}
}
