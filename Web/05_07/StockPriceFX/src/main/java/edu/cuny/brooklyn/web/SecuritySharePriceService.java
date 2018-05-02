package edu.cuny.brooklyn.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecuritySharePriceService {
	private final static Logger LOGGER = LoggerFactory.getLogger(SecuritySharePriceService.class);
	private final static String META_DATA_KEY = "Meta Data";
	private final static String WEEKLY_TIME_SERIES_KEY = "Weekly Time Series";
	private final static String CLOSE_PRICE_KEY = "4. close";
	
	private final static String USER_AGENT = "Mozilla/5.0";
	
	private final static String SERVICE_API_FMT 
		= "https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=%s&apikey=%s";
	
	private String apiKey;

	public SecuritySharePriceService(String apiKey) {
		this.apiKey = apiKey;
	}

	public SharePrice getSharePrice(String ticket, LocalDate startDate, LocalDate endDate) throws IOException {
		// set up api argument
		String apiWebResource = String.format(SERVICE_API_FMT, ticket, apiKey);
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
		SharePrice sharePrice = readSharePrice(conn);
		
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
	
	private HttpURLConnection sendHttpRequest(String apiWebResource) throws IOException {
		URL url = new URL(apiWebResource);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// the user agent must be set; otherwise, the ziptasticapi.com's hosting service denies access
		conn.setRequestProperty("User-Agent", USER_AGENT);
		// we use the GET method
		conn.setRequestMethod("GET");
		
		return conn;
	}
	
	private SharePrice readSharePrice(HttpURLConnection conn) throws IOException {
		// we need to parse the Json content. consider using one of the few popular APIs
		// such as, Gson, Genson, Jackson
		SharePrice sharePrice = null;
		try (InputStream in = conn.getInputStream(); JsonReader rdr = Json.createReader(in)) {
			JsonObject obj = rdr.readObject();
			sharePrice = parseJsonObject(obj);
		}
		return sharePrice;
	}
	
	private SharePrice parseJsonObject(JsonObject obj) {
		SharePrice sharePrice = new SharePrice();
		
		JsonObject metaData = obj.getJsonObject(META_DATA_KEY);
		if (metaData == null) {
			throw new IllegalArgumentException("Did not receive the Json object.");
		}
		JsonObject timeSeries = obj.getJsonObject(WEEKLY_TIME_SERIES_KEY);
		if (timeSeries == null) {
			throw new IllegalArgumentException("Did not receive the Json object.");
		}
		
		metaData.forEach((k, v) -> sharePrice.addMetaData(k, v.toString()));
		timeSeries.forEach((date, prices) -> {
			String s = prices.asJsonObject().getString(CLOSE_PRICE_KEY);
			double price;
			if (s == null || s.isEmpty()) {
				price = -1.;
			} else {
				price = Double.parseDouble(s);
			}
			sharePrice.addClosePrice(date, price);
		});
		
		return sharePrice;
	}
}
