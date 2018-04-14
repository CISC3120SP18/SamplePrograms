package edu.cuny.brooklyn.net;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UdpBroadcastReceiver {
	private final static Logger LOGGER = LoggerFactory.getLogger(UdpBroadcastReceiver.class);
	private final static int PORT = 60202;
	private final static int BUFFER_SIZE = 1024;

	public static void main(String[] args) {
		int port = PORT;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}

		try (DatagramSocket socket = new DatagramSocket(port)) {
			socket.setReuseAddress(true);
			byte[] buf = new byte[BUFFER_SIZE];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);

			int[] numbers = getNumbers(packet.getData());
			printNumbers(numbers);
		} catch (SocketException e) {
			System.out.println("Socket error.");
			LOGGER.error("Socket error.", e);
		} catch (IOException e) {
			System.out.println("I/O error.");
			LOGGER.error("I/O error.", e);
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
