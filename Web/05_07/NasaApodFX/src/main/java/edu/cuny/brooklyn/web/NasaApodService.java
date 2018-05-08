package edu.cuny.brooklyn.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

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
		RequestParameterBuilder rpb = new RequestParameterBuilder();
		rpb.add("date", date)
			.add("hd",  highResolution)
			.add("api_key",  apiKey);
		
		HttpURLConnection conn = sendHttpRequest(API_END_POINT, rpb.toURLQuery());


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
		// we need to parse the Json content. consider using one of the few popular APIs
		// such as, Gson, Genson, Jackson
		NasaApod nasaApod = null;
		try (InputStream in = conn.getInputStream(); 
				JsonReader rdr = Json.createReader(in)) {
			JsonObject obj = rdr.readObject();
			LOGGER.debug("JsonObject received: " + obj.toString());

			nasaApod = NasaApod.fromJsonObject(obj);
		}
		return nasaApod;
	}
}
