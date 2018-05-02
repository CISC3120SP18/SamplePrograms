package edu.cuny.brooklyn.web;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class SharePrice {
	private Map<String, String> metaDataMap;
	private Map<String, Double> closePriceMap;
	
	public SharePrice() {
		metaDataMap = new HashMap<String, String>();
		closePriceMap = new HashMap<String, Double>();
	}
	
	public void addMetaData(String key, String value) {
		metaDataMap.put(key, value);
	}
	
	public void addClosePrice(String date, double price) {
		closePriceMap.put(date, price);
	}
	
	
	public void forEachClosePrice(BiConsumer<String, Double> handler) {
		closePriceMap.forEach(handler);
	}
}
