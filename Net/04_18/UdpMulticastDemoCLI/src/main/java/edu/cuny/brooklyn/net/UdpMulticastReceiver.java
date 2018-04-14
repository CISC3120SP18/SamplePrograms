package edu.cuny.brooklyn.net;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UdpMulticastReceiver {
	private final static Logger LOGGER = LoggerFactory.getLogger(UdpMulticastReceiver.class);
	
	private final static String MULTICAST_GROUP_ADDRESS = "224.0.0.0";
	private final static int MULTICAST_PORT = 60202;
	private static int BUFFER_SIZE = 1024;


	public static void main(String[] args) {
		int multicastPort = MULTICAST_PORT;
		String multicastGroupAddress = MULTICAST_GROUP_ADDRESS;
		
		if (args.length != 0 && args.length != 2) {
			System.out.println("Usage: UdpMulticastReceiver" + System.getProperty("line.separator") +
					"or" + System.getProperty("line.separator") +
					"Usage: UdpMulticastReceiver MULTICAST_GROUP_ADDRESS MULTICAST_GROUP_PORT ");
			System.exit(-1);
		}
		
		if (args.length == 2) {
			multicastGroupAddress = args[0];
			multicastPort = Integer.parseInt(args[1]);
		}

		try (MulticastSocket socket = new MulticastSocket(multicastPort)) {
			socket.setReuseAddress(true);
			InetAddress group = InetAddress.getByName(multicastGroupAddress);
			socket.joinGroup(group);

			byte[] buf = new byte[BUFFER_SIZE];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);

			int[] numbers = getNumbers(packet.getData());
			printNumbers(numbers);
			
			socket.leaveGroup(group);
		} catch (UnknownHostException e) {
			System.out.println("Uknown host " + multicastGroupAddress);
			LOGGER.error("Unknown host " + multicastGroupAddress, e);
		} catch (IOException e) {
			System.out.println("I/O error");
			LOGGER.error("I/O error", e);
		}
	}
	
	
	private static int[] getNumbers(byte[] buf) throws IOException {
		int[] numbers = null;
		try (DataInputStream dataIn = new DataInputStream(new ByteArrayInputStream(buf))) {
			int numNumbers = dataIn.readInt();
			numbers = new int[numNumbers];
			for (int i=0; i<numNumbers; i++) {
				numbers[i] = dataIn.readInt();
			}
		}
		return numbers;
	}
	
	private static void printNumbers(int[] numbers) {
		System.out.println("Received " + numbers.length + " numbers:");
		for (int i=0; i<numbers.length; i++) {
			System.out.println("Number[" + i + "] = " + numbers[i]);
		}
	}
}
