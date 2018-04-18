/*
 * A very simple application that multicasts a list of images to groups of subscribers.
 * - We assign a UDP port to each group (which makes the design simpler)
 * - We haven't use any concurrency yet in this design, and the application in effect 
 *   only multicast to only one group. We shall come back to this when we discuss
 *   Java concurrency.
 * - Images musts be small enough so that each can fit in a single UDP datagram. To 
 *   multicast images larger than a single UDP datagram can carry, we will have to 
 *   design a protocol and the supporting data structures, which we don't wish to 
 *   focus on in this application. 
 */
package edu.cuny.brooklyn.net;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.zip.DataFormatException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UdpImagePublisherCLI  {
	private final static Logger LOGGER = LoggerFactory.getLogger(UdpImagePublisherCLI.class);

	private final static String IMAGE_LIST_FN_KEY = "ImageListFile";
	private final static String GROUP_LIST_FN_KEY = "MulticastGroupList";
	private final static String BIND_ADDRESS_KEY = "BindAddress";
	private final static String BIND_PORT_KEY = "BindPort";
	private final static String IMAGE_DELAY_KEY = "DelayBetweenImageInSeconds";
	
	private String imageListFilename;
	private String groupListFilename;
	private InetSocketAddress bindInetSocketAddress;
	private int imageDelay;
	
	public static void main(String[] args) {
		try {
			UdpImagePublisherCLI publisherApp = new UdpImagePublisherCLI();
			if (!publisherApp.loadConfiguration()) {
				System.err.println("Failed to load application configuration. Exit ...");
				return;
			}

			List<String> imageFilenameList = publisherApp.loadImageFilenameList();
			if (imageFilenameList == null) {
				System.err.println("Failed to populate the list of image filenames");
				return;
			}

			List<InetSocketAddress> groupAddressList = publisherApp.loadGroupAddressList();
			if (groupAddressList == null) {
				System.err.println("Failed to populate the list of group addresses");
				return;
			}

			System.out.format("UdpImagePublisherCLI is publishing %d images to %s at UDP port %d.%n",
					imageFilenameList.size(), groupAddressList.get(0).getAddress(), groupAddressList.get(0).getPort());
			publisherApp.publish(imageFilenameList, groupAddressList);
		} catch (UnknownHostException e) {
			System.err.println("Unknown host");
			LOGGER.error("Unknown host: " + e.getMessage(), e);
		} catch (SocketException e) {
			System.err.println("Socket exception");
			LOGGER.error("Socket exception: " + e.getMessage(), e);
		} catch (DataFormatException e) {
			System.err.println("Wrong data file format");
			LOGGER.error("Wrong data file format" + e.getMessage(), e);
		}

	}
	
	private void publish(List<String> imageFilenameList, List<InetSocketAddress> groupInetSocketAddressList) throws UnknownHostException, SocketException {
		/* Publish to multiple groups is yet to be implemented. how would we design this?  
		 * - we have made the design consideration each group would have its own UDP port number.
		 * - we will publish to each group in its own thread.but it may not be an idea to Thread in
		 *   the simplest way. we would probably want to terminate, cancel, restart a publisher. 
		 */
		if (groupInetSocketAddressList.size() > 1) {
			throw new IllegalArgumentException("Support to multicast to multiple groups hasn't been implemented");
		}
		for (InetSocketAddress groupInetSocketAddress: groupInetSocketAddressList) {
			UdpImagePublisher publisher = new UdpImagePublisher(imageFilenameList, imageDelay, bindInetSocketAddress, groupInetSocketAddress);
			publisher.publish();
		}
	}

	private boolean loadConfiguration() {
		boolean success = false;
		String propertiesFilename = getClass().getSimpleName() + ".properties";
		Properties properties = new Properties();
		
		/* 
		 * Whenever we use a file, we can either use a relative path or an absolute path. 
		 * To make an application portable, a relative path is more appropriate (why?).
		 * The question becomes, "to which path is the relative path is relative"?
		 * 
		 * One choice is to use the location of the class that you are loading. We use one
		 * of the two methods, Class.getResource and Class.getResourceAsStream. You can
		 * figure out where the class is in the file system, can't you?
		 * 
		 * Another choice is to use the location of the ClassLoader that you (implicitly)
		 * calling to load your class. We use or one of two methods, Classloader.getResource
		 * and Classloader.getResourceAsStream. Since the class loader is responsible for
		 * loading all of your classes, it is considered that it is at the directory
		 * under which all of your packages can be found. 
		 * 
		 * The following code uses the ClassLoader to load a configuration file. To 
		 * understand where the working directory of the class loader is, examine the deployment
		 * directory in your Maven project, that is to use the file explorer in your operating
		 * system to navigate to the target/classes directory under your project.  
		 *  
		 */
		try (InputStream in = getClass().getClassLoader().getResourceAsStream(propertiesFilename)) {
			if (in == null) {
				throw new FileNotFoundException("Cannot find or open resource " + propertiesFilename);
			}
			properties.load(in);
			
			imageListFilename = properties.getProperty(IMAGE_LIST_FN_KEY);
			if (imageListFilename == null) {
				throw new DataFormatException("Key " + IMAGE_LIST_FN_KEY + " not found in resource " + propertiesFilename);
			}
			groupListFilename = properties.getProperty(GROUP_LIST_FN_KEY);
			if (groupListFilename == null) {
				throw new DataFormatException("Key " + GROUP_LIST_FN_KEY + " not found in resource " + propertiesFilename);
			}
			String bindAddress = properties.getProperty(BIND_ADDRESS_KEY);
			if (groupListFilename == null) {
				throw new DataFormatException("Key " + BIND_ADDRESS_KEY + " not found in resource " + propertiesFilename);
			}
			String bindPortText = properties.getProperty(BIND_PORT_KEY);
			if (groupListFilename == null) {
				throw new DataFormatException("Key " + BIND_PORT_KEY + " not found in resource " + propertiesFilename);
			}
			bindInetSocketAddress = new InetSocketAddress(bindAddress, Integer.parseInt(bindPortText));
			String imageDelayText = properties.getProperty(IMAGE_DELAY_KEY);
			if (imageDelayText == null) {
				throw new DataFormatException("Key " + IMAGE_DELAY_KEY + " not found in resource " + propertiesFilename);
			}
			imageDelay = Integer.parseInt(imageDelayText);
			success = true;
		} catch (FileNotFoundException e) {   // These should never happen in a correctly deployed 
			LOGGER.error(e.getMessage(), e);  // application. Therefore, we simply log the error in 
		} catch (IOException e) {             // the log file for debugging purpose.
			LOGGER.error("I/O error on resource " + propertiesFilename, e);
		} catch (DataFormatException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return success;
	}
	
	private List<String> loadImageFilenameList() {
		return loadListFromFile(imageListFilename);
	}
	
	
	private List<InetSocketAddress> loadGroupAddressList() throws DataFormatException {
		List<InetSocketAddress> groupList = new LinkedList<InetSocketAddress>();
		List<String> lineList = loadListFromFile(groupListFilename);
		for (String line: lineList) {
			String[] parts = line.split(",");
			if (parts.length != 2) {
				throw new DataFormatException(groupListFilename + " is not a valid group address list CSV file.");
			}
			InetSocketAddress address = new InetSocketAddress(parts[0], Integer.parseInt(parts[1]));
			groupList.add(address);
		}
		return groupList;
	}
	
	private List<String> loadListFromFile(String listFilename) {
		List<String> filenameList = null;
		try (InputStream in = getClass().getClassLoader().getResourceAsStream(listFilename)) {
			if (in == null) {
				throw new FileNotFoundException("Cannot find or open resource " + listFilename);
			}
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
				filenameList = new LinkedList<String>();
				String fnLine;
				while ((fnLine = reader.readLine()) != null) {
					filenameList.add(fnLine.trim());
				}
			} 
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.warn("Failed to read line (counting from 1) " + (filenameList != null ? filenameList.size() + 1 : 0), e);
		}
		return filenameList;
	}
}
