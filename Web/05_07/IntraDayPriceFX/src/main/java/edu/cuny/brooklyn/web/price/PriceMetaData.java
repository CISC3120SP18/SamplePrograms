package edu.cuny.brooklyn.web.price;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.TimeZone;

import javax.json.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PriceMetaData {
	private final static Logger LOGGER = LoggerFactory.getLogger(PriceMetaData.class);
	/*
	 * Example: 
	 * "1. Information": "Intraday (30min) prices and volumes", 
	 * "2. Symbol": "MSFT",
	 * "3. Last Refreshed": "2018-05-04 16:00:00", 
	 * "4. Interval": "30min",
	 * "5. Output Size": "Compact", 
	 * "6. Time Zone": "US/Eastern"
	 */
	public final static String INFO_KEY = "1. Information";
	public final static String SYMBOL_KEY = "2. Symbol";
	public final static String LAST_REFRESHED_KEY = "3. Last Refreshed";
	public final static String INTERVAL_KEY = "4. Interval";
	public final static String OUTPUT_SIZE_KEY = "5. Output Size";
	public final static String TIMEZONE_KEY = "6. Time Zone";
	
	public final static NumberFormat INTERVAL_FORMAT = new DecimalFormat("00min");
	
	private String information;
	private String symbol;
	private LocalDateTime lastRefereshed;
	private TimeZone timeZone;
	private int interval;
	
	public PriceMetaData(String information, String symbol, LocalDateTime lastRefreshed, TimeZone timeZone, int interval) {
		this.information = information;
		this.symbol = symbol;
		this.lastRefereshed = lastRefreshed;
		this.timeZone = timeZone;
		this.interval = interval;
	}

	public String getInformation() {
		return information;
	}

	public String getSymbol() {
		return symbol;
	}

	public LocalDateTime getLastRefereshed() {
		return lastRefereshed;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public int getInterval() {
		return interval;
	}
	

	public static PriceMetaData fromJsonObject(JsonObject jsonMetaDataObj) {
		String date = jsonMetaDataObj.getString(PriceMetaData.LAST_REFRESHED_KEY);
		String tz = jsonMetaDataObj.getString(PriceMetaData.TIMEZONE_KEY);
		TimeZone timeZone = TimeZone.getTimeZone(tz);
		String it = jsonMetaDataObj.getString(PriceMetaData.INTERVAL_KEY);
		Number interval;
		try {
			interval = PriceMetaData.INTERVAL_FORMAT.parse(it);
		} catch (ParseException e) {
			LOGGER.error("Cannot parse " + it, e);
			interval = -1;
		}
		
		return new PriceMetaData(
				jsonMetaDataObj.getString(PriceMetaData.INFO_KEY),
				jsonMetaDataObj.getString(PriceMetaData.SYMBOL_KEY),
				date == null ? null : LocalDateTime.parse(date, IntradaySharePrice.DATETIME_FORMATTER),
				timeZone, 
				interval.intValue());
	}
}
