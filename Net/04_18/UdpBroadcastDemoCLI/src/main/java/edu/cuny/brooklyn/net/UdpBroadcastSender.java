package edu.cuny.brooklyn.net;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UdpBroadcastSender {
	private final static Logger LOGGER = LoggerFactory.getLogger(UdpBroadcastSender.class);
	
	private final static String BROADCAST_ADDRESS = "255.255.255.255";
	private final static int BROADCAST_PORT = 60202;


	public static void main(String[] args) {
		String broadcastAddress = BROADCAST_ADDRESS;
		int broadcastPort = BROADCAST_PORT;
		if (args.length > 0) {
			broadcastAddress = args[0];
		}
		if (args.length > 1) {
			broadcastPort = Integer.parseInt(args[1]);
		}
		try (DatagramSocket socket = new DatagramSocket()) {
			socket.setReuseAddress(true);
			socket.setBroadcast(true);
			
			SocketAddress toAddress = new InetSocketAddress(InetAddress.getByName(broadcastAddress), broadcastPort);

			byte buf[] = generateDataBytes();
			DatagramPacket packet = new DatagramPacket(buf, buf.length, toAddress);
			socket.send(packet);
			System.out.println("Packet sent.");
		} catch (SocketException e) {
			System.out.println("Socket error.");
			LOGGER.error("Socket error.", e);
		} catch (IOException e) {
			System.out.println("I/O error.");
			LOGGER.error("I/O error.", e);
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
			for (int i=0; i<numNumbers; i++) {
				int num = rng.nextInt(100) + 1;
				dataOut.writeInt(num);
				System.out.println("Number[" + i + "] = " + num);
			}
			buf = byteOut.toByteArray();
		}
		return buf;
	}
}
