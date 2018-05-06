package edu.cuny.brooklyn.web.price;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;

import javax.json.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntradaySharePrice {
	private final static Logger LOGGER = LoggerFactory.getLogger(IntradaySharePrice.class);
	/*
	 * Example: 
	 * "Time Series (1min)": { 
	 * 	"2018-05-04 16:00:00": 
	 * 		{ 
	 * 			"1. open": "94.9700",
	 * 			"2. high": "95.2200", 
	 * 			"3. low": "94.9700", 
	 * 			"4. close": "95.1600",
	 * 			"5. volume": "2522498" }, 
	 * 	"2018-05-04 15:59:00": { 
	 * 			"1. open": "94.9950",
	 * 			"2. high": "95.0100", 
	 * 			"3. low": "94.9400", 
	 * 			"4. close": "94.9700",
	 * 			"5. volume": "166628" },
	 * 			...
	 * }
	 */
	
	public final static DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss");
	private final static String META_DATA_KEY = "Meta Data";
	private final static String TIME_SERIES_KEY_FMT = "Time Series (%dmin)";	
	
	
	private PriceMetaData metaData;
	private Map<LocalDateTime, SharePrice> priceSeries;
	
	public IntradaySharePrice(PriceMetaData metaData) {
		this.metaData = metaData;
		priceSeries = new TreeMap<LocalDateTime, SharePrice>((date1, date2) -> date1.compareTo(date2));
	}

	public void addSharePrice(LocalDateTime dateTime, SharePrice price) {
		priceSeries.put(dateTime,  price);
	}
	
	public PriceMetaData getMetaData() {
		return metaData;
	}
	
	public static IntradaySharePrice fromJsonObject(JsonObject obj) {
			JsonObject jsonMetaDataObj = obj.getJsonObject(META_DATA_KEY);
			if (jsonMetaDataObj == null) {
				throw new IllegalArgumentException("Did not receive the Json object.");
			}
			PriceMetaData metaData = PriceMetaData.fromJsonObject(jsonMetaDataObj);

			String timeSeriesKey = String.format(TIME_SERIES_KEY_FMT, metaData.getInterval());
			LOGGER.info("Time Series Key formed is " + timeSeriesKey);
			
			JsonObject jsonTimeSeriesObj = obj.getJsonObject(timeSeriesKey);
			if (jsonTimeSeriesObj == null) {
				throw new IllegalArgumentException("Did not receive the Json object.");
			}
			
			IntradaySharePrice intradaySharePrice = new IntradaySharePrice(metaData);
			SharePrice.parsePriceSeries(jsonTimeSeriesObj, intradaySharePrice);
			
			return intradaySharePrice;
	}

	public void forEach(BiConsumer<LocalDateTime, SharePrice> consumer) {
		priceSeries.forEach(consumer);
	}
}
