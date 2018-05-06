package edu.cuny.brooklyn.web.price;

import java.time.LocalDateTime;

import javax.json.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SharePrice {
	private final static Logger LOGGER = LoggerFactory.getLogger(SharePrice.class);
	/*
	 * Example: 
	 * { "1. open": "94.9700", "2. high": "95.2200", "3. low": "94.9700",
	 * "4. close": "95.1600", "5. volume": "2522498" },
	 */
	
	private final static String OPEN_KEY = "1. open";
	private final static String HIGH_KEY = "2. high";
	private final static String LOW_KEY = "3. low";
	private final static String CLOSE_KEY = "4. close";
	private final static String VOLUME_KEY = "5. volume";
	
	/* should you be considering the BigDecimal class? */
	private double open;
	private double high;
	private double low;
	private double close;
	private long volume;

	public SharePrice(double open, double high, double low, double close, long volume) {
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}

	public double getOpen() {
		return open;
	}

	public double getHigh() {
		return high;
	}

	public double getLow() {
		return low;
	}

	public double getClose() {
		return close;
	}

	public long getVolume() {
		return volume;
	}

	public static SharePrice fromJsonObject(JsonObject obj) {
		return new SharePrice(
				Double.parseDouble(obj.getString(OPEN_KEY)),
				Double.parseDouble(obj.getString(HIGH_KEY)),
				Double.parseDouble(obj.getString(LOW_KEY)),
				Double.parseDouble(obj.getString(CLOSE_KEY)),
				Long.parseLong(obj.getString(VOLUME_KEY)));
	}
	
	public static void parsePriceSeries(JsonObject jsonTimeSeriesObj, IntradaySharePrice intradayPrice) {
		jsonTimeSeriesObj.forEach((dateTime, prices) -> {
			LocalDateTime localDateTime = LocalDateTime.parse(dateTime, IntradaySharePrice.DATETIME_FORMATTER);
			SharePrice sharePrice = SharePrice.fromJsonObject(prices.asJsonObject());
			LOGGER.debug("Price: " + dateTime + "(" + localDateTime.toString() + ")" + "," + sharePrice.getClose());
			intradayPrice.addSharePrice(localDateTime, sharePrice);
		});
	}
}
