package edu.cuny.brooklyn.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressService {
	private final static Logger LOGGER = LoggerFactory.getLogger(AddressService.class);
	
	private final static String USER_AGENT = "Mozilla/5.0";
	
	private final static String SERVICE_API_FMT = "http://ziptasticapi.com/%s";

	private final static String ZIPCODE_PATTERN = "\\d{5}";
	private Pattern pattern;

	public AddressService() {
		pattern = Pattern.compile(ZIPCODE_PATTERN);
		// if we wish to enable cookies
		// CookieHandler.setDefault( new CookieManager( null, CookiePolicy.ACCEPT_ALL ) );
	}

	public Address getAddress(String zipCode) throws IOException {
		// sanity check
		if (!isAcceptableZipCode(zipCode)) {
			throw new IllegalArgumentException("Zip code " + zipCode + " is not an acceptable zip code.");
		}
		
		// set up api argument
		String apiWebResource = String.format(SERVICE_API_FMT, zipCode);
		LOGGER.info("Api request: " + apiWebResource);

		// send the request
		HttpURLConnection conn = sendHttpRequest(apiWebResource);


		// check the status
		int status = conn.getResponseCode();
		if (status != HttpURLConnection.HTTP_OK) {
			logErrorMessage(conn);
			throw new IllegalStateException("Server responses with " + status);
		}

		// get the  address
		Address address = readAddress(conn);
		
		// disconnect to release resource
		conn.disconnect();
		
		return address;
	}

	private boolean isAcceptableZipCode(String zipCode) {
		if (zipCode == null || zipCode.isEmpty() || zipCode.length() != 5 || !pattern.matcher(zipCode).matches()) {
			return false;
		} else {
			return true;
		}
	}
	
	private void logErrorMessage(HttpURLConnection conn) throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				LOGGER.error("Line: " + line);
			}
		}
		
	}
	
	private HttpURLConnection sendHttpRequest(String apiWebResource) throws IOException {
		URL url = new URL(apiWebResource);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// the user agent must be set; otherwise, the ziptasticapi.com's hosting service denies access
		conn.setRequestProperty("User-Agent", USER_AGENT);
		// we use the GET method
		conn.setRequestMethod("GET");
		
		return conn;
	}
	
	private Address readAddress(HttpURLConnection conn) throws IOException {
		// we need to parse the Json content. consider using one of the few popular APIs
		// such as, Gson, Genson, Jackson
		String city = "";
		String country = "";
		String state = "";
		try (InputStream in = conn.getInputStream(); JsonReader rdr = Json.createReader(in)) {
			JsonObject obj = rdr.readObject();
			country = obj.getString("country");
			city = obj.getString("city");
			state = obj.getString("state");
		}
		return new Address(country, state, city);
	}
}
