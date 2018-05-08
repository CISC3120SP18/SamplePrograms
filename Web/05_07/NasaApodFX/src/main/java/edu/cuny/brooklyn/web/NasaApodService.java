// TODO 1: Complete this class, look for the TODO's below. 

package edu.cuny.brooklyn.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NasaApodService {
	private final static Logger LOGGER = LoggerFactory.getLogger(NasaApodService.class);
	/*
	 * Query Parameters
	 * Parameter Type        Default 	Description
	 * date      YYYY-MM-DD  today      The date of the APOD image to retrieve
	 * hd        bool        False      Retrieve the URL for the high resolution image
	 * api_key   string      DEMO_KEY   api.nasa.gov key for expanded usage (to obtain at https://api.nasa.gov/index.html#apply-for-an-api-key)
	 * 
	 * Example query
	 * https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY 
	 */
	private final static String API_END_POINT = "https://api.nasa.gov/planetary/apod";
	
	private String apiKey;
	
	public NasaApodService(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public NasaApod getApod(String date, boolean highResolution) throws IOException, URISyntaxException {
		// TODO 1(a): use the RequestParameterBuilder to build the query String, 
		//            i.e. the query variable should references the value of the
		//            query string. 
		String query = "";
		
		HttpURLConnection conn = sendHttpRequest(API_END_POINT, query);


		// check the status
		int status = conn.getResponseCode();
		if (status != HttpURLConnection.HTTP_OK) {
			logErrorMessage(conn);
			throw new IllegalStateException("Server responses with " + status);
		}

		// get the object
		NasaApod nasaApod = readNasaApod(conn);
		
		// disconnect to release resource
		conn.disconnect();
		
		LOGGER.info("Got the NASA APOD from the service.");
		return nasaApod;
	}
	
	private void logErrorMessage(HttpURLConnection conn) throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				LOGGER.error("Line: " + line);
			}
		}
		
	}
	
	private HttpURLConnection sendHttpRequest(String apiWebResource, String query) throws IOException {
		URL url = new URL(apiWebResource + "?" + query);

		LOGGER.info("url: " + url.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET"); 
		return conn;
	}
	
	private NasaApod readNasaApod(HttpURLConnection conn) throws IOException, URISyntaxException {
		// TODO 1(b): construct a NasaApod object from returned JSON object/array
		NasaApod nasaApod = null;
		
		return nasaApod;
	}
}
