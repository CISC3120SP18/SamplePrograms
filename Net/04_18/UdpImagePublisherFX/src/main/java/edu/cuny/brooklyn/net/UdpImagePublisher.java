package edu.cuny.brooklyn.net;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UdpImagePublisher {
	private final static Logger LOGGER = LoggerFactory.getLogger(UdpImagePublisher.class);
	/* 
	 * We avoid using the concept URL for now. Arguably, it is a better design to use a list of 
	 * URLs that have been vetted, and the images may be load from elsewhere other than files.
	 * We shall reexamine the design when we start discussing Web applications. 
	 */
	private List<String> imageFilenameList;
	private SocketAddress bindSocketAddress;
	private SocketAddress groupSocketAddress;
	private int imageDelay;
	

	public UdpImagePublisher(List<String> imageFilenameList, int imageDelay, SocketAddress bindSocketAddress, SocketAddress groupSocketAddress) {
		this.imageFilenameList = imageFilenameList;
		this.imageDelay = imageDelay;
		this.bindSocketAddress = bindSocketAddress;
		this.groupSocketAddress = groupSocketAddress;
	}

	public void publish() throws UnknownHostException, SocketException {
		// a multicast sender can use either a DatagramSocket object or a MulticastSocket object
		try (DatagramSocket socket = new DatagramSocket(bindSocketAddress)) {
			// send the list of images repeatedly forever
			while (true) {
				for (String imageFn : imageFilenameList) {
					castOneImage(socket, imageFn, groupSocketAddress);
					sleep(imageDelay);
				}
			}
		}
	}
	
	private void castOneImage(DatagramSocket socket, String imageFn, SocketAddress groupSocketAddress) {
		/* 
		 * This design does not consider I/O performance. how do we improve the performance?
		 * For instance: 
		 * 	is reading a byte at a time an efficient operation?
		 * 	can we figure out the size of the image, and read it at once?
		 */
		byte[] imageBytes = null;
		try (InputStream in = getClass().getClassLoader().getResourceAsStream(imageFn);
				ByteArrayOutputStream byteOut = new ByteArrayOutputStream()) {
			if (in == null) {
				throw new FileNotFoundException("Cannot open image file " + imageFn);
			}
			int b;
			while ((b = in.read()) != -1) {
				byteOut.write(b);
			}
			imageBytes = byteOut.toByteArray();
			
			byteOut.reset();
			ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.SIZE / 8);
			byteBuffer.putInt(imageBytes.length);
			byteOut.write(byteBuffer.array());
			byteOut.write(imageBytes);
			imageBytes = byteOut.toByteArray();
			/*
			 * This can cause error, when the image is too big. why? how would you fix it?
			 * It requires some "protocol" design effort, which we won't address in this
			 * example.
			 */
			DatagramPacket packet = new DatagramPacket(imageBytes, imageBytes.length, groupSocketAddress);
			socket.send(packet);
		} catch (IOException e) {
			// if there is an error for this image, we soldier on hoping other images may be good
			LOGGER.warn("I/O error: " + e.getMessage(), e);
		}
	}
	
	private void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			LOGGER.warn("Interruped while sleep: " + e.getMessage(), e);
		}
	}
}
