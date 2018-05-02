package edu.cuny.brooklyn.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpRequestResponseCLI {
	private final static Logger LOGGER = LoggerFactory.getLogger(HttpRequestResponseCLI.class);
	private final static int BUFFER_SIZE = 1024;
	
	public static void main(String[] args) {
		String webResource = "http://www.brooklyn.cuny.edu/web/home.php";
		if (args.length >= 1) {
			webResource = args[0];
		}
		
		HttpRequestResponseCLI app = new HttpRequestResponseCLI();
		InputStream in = null;
		OutputStream out = null;
		try {
			URI uri = new URI(webResource);
			LOGGER.info("URI is " + uri.toString());
			URI resourceURI = app.getResourceName(uri);
			LOGGER.info("Resource in the URI is " + resourceURI);
			try (Socket socket = app.connect(uri)) {
				out = socket.getOutputStream();
				String request = app.makeRequest(resourceURI.toString(), uri.getHost());
				LOGGER.info("Request is \n" + request);
				app.sendRequest(out, request);
				LOGGER.info("Request send.");
				
				in = socket.getInputStream();
				String response = app.readResponse(in);
				LOGGER.info("Received a response.");
				LOGGER.info("The response is\n" + response);
			} 
		} catch (URISyntaxException e) {
			System.err.println(webResource + " is not a valid URI.");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
		}
		
		
	}
	
	private String makeRequest(String webResource, String host) {
		// construct a HTTP/1.1 request in its simplest form using the GET method
		String request = 
				"GET " + "/" + webResource + " HTTP/1.1\r\n" +
				"User-Agent: Java Web Client\r\n" +
				"Host: " + host + "\r\n" + 
				"Accept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2\r\n" +
				"Connection: keep-alive\r\n" + 
				"\r\n" + 
				"\r\n";
		return request;
	}
	
	private URI getResourceName(URI uri) throws URISyntaxException {
		URI uriBase = new URI(uri.getScheme(), uri.getAuthority(), "", "", "");
		return uriBase.relativize(uri);
	}
	
	private Socket connect(URI uri) throws IOException {
		if (!uri.getScheme().equals("http")) {
			throw new IllegalArgumentException("The protocol must be http.");
		}
		int servicePort = 80;
		if (uri.getPort() != -1) {
			servicePort = uri.getPort();
		}
		SocketAddress address = new InetSocketAddress(InetAddress.getByName(uri.getHost()), servicePort);
		Socket socket = new Socket();
		socket.connect(address);
		return socket;
	}
	
	private void sendRequest(OutputStream out, String request) throws IOException {
		out.write(request.getBytes());
	}
	
	private String readResponse(InputStream in) throws IOException {
		byte[] bytes = new byte[BUFFER_SIZE];
		StringBuilder sb = new StringBuilder();
		int bytesRead = 0;
		while ((bytesRead = in.read(bytes)) != -1) {
			String s = new String(bytes, 0, bytesRead, StandardCharsets.UTF_8);
			sb.append(s);
		}
		return sb.toString();
	}
}
