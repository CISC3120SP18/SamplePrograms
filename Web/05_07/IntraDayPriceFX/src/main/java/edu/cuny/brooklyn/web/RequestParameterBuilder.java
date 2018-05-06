package edu.cuny.brooklyn.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class RequestParameterBuilder {
	private Map<String, String> parameterMap;
	
	public RequestParameterBuilder() {
		// LinkedHashMap keeps the entries in initial insertion order
		parameterMap = new LinkedHashMap<String, String>();
	}
	
	public RequestParameterBuilder add(String name, String value) throws UnsupportedEncodingException  {
		String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8.name());
		String encodedValue = URLEncoder.encode(value, StandardCharsets.UTF_8.name());
		parameterMap.put(encodedName, encodedValue);
		return this;
	}
	
	public String toURLQuery() {
		StringBuilder sb = new StringBuilder();
		parameterMap.forEach((name, value) -> {
			sb.append(name).append("=").append(value).append("&");
		});
		String query = sb.toString();
		// get rid of last "&"
		return query.length() > 0 ? query.substring(0, query.length() - 1) : query;
	}
	
	public String toString() {
		return toURLQuery();
	}
}
