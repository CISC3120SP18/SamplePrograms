/*
 * TODO: Complete the subscribe() method in this class.
 *       See the TODO's in the subscribe() method for more
 *       detailed guidelines. 
 */

package edu.cuny.brooklyn.net;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class UdpImageSubscriber {
	private final static Logger LOGGER = LoggerFactory.getLogger(UdpImageSubscriber.class);
	private final static int BUFFER_SIZE = 65000;
	private InetAddress groupAddress;
	private int localPort;

	private ObjectProperty<Image> imageProperty;

	public UdpImageSubscriber(InetAddress groupAddress, int localPort) {
		this.groupAddress = groupAddress;
		this.localPort = localPort;
		this.imageProperty = new SimpleObjectProperty<Image>();
	}

	public void subscribe() {
		/* TODO: subscribe to the image publisher's service that multicasts
		 *       a list of images. 
		 *       
		 *       Follow the steps below,
		 *       (1) create a MulticastSocket bound to the "localPort"
		 *       (2) join the group to subscribe the image service
		 *       (3) in an loop (e.g., loop for many iterations) do the following,
		 *           (a) receive a DatagramPacket from the MulticastSocket
		 *           (b) create an input stream whose source is the received 
		 *               packet (e.g.,f ByteArrayInputStream and DataInputStream)
		 *           (c) read the length of the image content from the stream
		 *           (d) with the length in the previous step, read the content
		 *               of the image to a byte array
		 *           (e) create an Image (JavaFX Image) from the bytes read
		 *           (f) set the imageProperty's value to the image constructed.
		 *       (4) leave the group when the loop ends
		 *       
		 *       Make sure all the resources are released. 
		 */
	}

	public ObjectProperty<Image> imageProperty() {
		return this.imageProperty;
	}
}
