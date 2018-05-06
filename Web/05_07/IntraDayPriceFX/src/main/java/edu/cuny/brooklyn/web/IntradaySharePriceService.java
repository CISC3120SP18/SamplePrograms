package edu.cuny.brooklyn.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.web.price.IntradaySharePrice;

public class IntradaySharePriceService {
	private final static Logger LOGGER = LoggerFactory.getLogger(IntradaySharePriceService.class);
	
	private final static String USER_AGENT = "Mozilla/5.0";
	
	private final static String SERVICE_API = "https://www.alphavantage.co/query";
	
	private String apiKey;

	public IntradaySharePriceService(String apiKey) {
		this.apiKey = apiKey;
	}

	public IntradaySharePrice getSharePrice(String ticket, int interval) throws IOException {
		// set up api argument
		String apiWebResource = SERVICE_API;
		LOGGER.info("Api request: " + apiWebResource);

		// send the request
		// https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&apikey=demo
		RequestParameterBuilder rpBuilder = new RequestParameterBuilder();
		rpBuilder.add("function", "TIME_SERIES_INTRADAY")
			.add("symbol", ticket)
			.add("interval", Integer.toString(interval) + "min")
			.add("apikey", apiKey);
		HttpURLConnection conn = sendHttpRequest(apiWebResource, rpBuilder.toURLQuery());


		// check the status
		int status = conn.getResponseCode();
		if (status != HttpURLConnection.HTTP_OK) {
			logErrorMessage(conn);
			throw new IllegalStateException("Server responses with " + status);
		}

		// get the  address
		IntradaySharePrice sharePrice = readSharePrice(conn);
		
		// disconnect to release resource
		conn.disconnect();
		
		LOGGER.info("Got the share price from the service.");
		return sharePrice;
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
		// the user agent must be set; otherwise, the ziptasticapi.com's hosting service denies access
		conn.setRequestProperty("User-Agent", USER_AGENT);
		// we use the GET method
		conn.setRequestMethod("GET"); // only get supported, should redo url
//		Alpha Vantage only accepts GET method, the following will use POST method, and will result in
//		{
//			    "detail": "Method \"POST\" not allowed."
//		}
//		// write out the parameters
//		conn.setDoOutput(true); // post method
//		try (OutputStream out = conn.getOutputStream()) { // post  method
//			out.write(query.getBytes());
//		}
		return conn;
	}
	
	private IntradaySharePrice readSharePrice(HttpURLConnection conn) throws IOException {
		// we need to parse the Json content. consider using one of the few popular APIs
		// such as, Gson, Genson, Jackson
		IntradaySharePrice intradaySharePrice = null;
		try (InputStream in = conn.getInputStream(); 
				JsonReader rdr = Json.createReader(in)) {
			JsonObject obj = rdr.readObject();
			LOGGER.debug("JsonObject received: " + obj.toString());

			intradaySharePrice = IntradaySharePrice.fromJsonObject(obj);
		}
		return intradaySharePrice;
	}
}
