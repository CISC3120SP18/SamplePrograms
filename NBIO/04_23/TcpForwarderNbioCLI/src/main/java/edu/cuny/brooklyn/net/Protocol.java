package edu.cuny.brooklyn.net;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Protocol {
	private final static String BYE_BYE_MSG = "-1,-1,Bye bye";
	private final static char LINE_SEPARATOR = '\n';
	private final static char LINE_CR = '\r';
	private final static Charset CHARSET = StandardCharsets.UTF_8;
	
	public static Charset getCharset() {
		return CHARSET;
	}
	
	public static char getLineBreak() {
		return LINE_SEPARATOR;
	}
	
	public static boolean isClosingRequest(String msg) {
		return msg != null && msg.indexOf(Protocol.BYE_BYE_MSG) == 0;
	}
	
	public static boolean isLineBreak(char c) {
		return c == LINE_SEPARATOR;
	}
	
	public static boolean shouldDrop(char c) {
		return c == LINE_CR;
	}
	
	public static String buildMessage(String msgParts) {
		return msgParts + LINE_SEPARATOR;
	}
	
}
