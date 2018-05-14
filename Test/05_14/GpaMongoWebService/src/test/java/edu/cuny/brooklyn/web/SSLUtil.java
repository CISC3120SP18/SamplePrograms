package edu.cuny.brooklyn.web;


import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public final class SSLUtil {

	private static final TrustManager[] UNQUESTIONING_TRUST_MANAGER = new TrustManager[] { new X509TrustManager() {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkClientTrusted(X509Certificate[] certs, String authType) {
		}

		public void checkServerTrusted(X509Certificate[] certs, String authType) {
		}
	} };

	public static void turnOffSslChecking() throws NoSuchAlgorithmException, KeyManagementException {
		final SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, UNQUESTIONING_TRUST_MANAGER, null);
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	}

	public static void turnOnSslChecking() throws KeyManagementException, NoSuchAlgorithmException {
		SSLContext.getInstance("SSL").init(null, null, null);
	}
	
	public static void setDefaultHostVerifier() {
		final HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
		final HostnameVerifier localhostAcceptedHostnameVerifier = new HostnameVerifier() {

			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
				if (hostname.equals("localhost")) {
					return true;
				}
				return defaultHostnameVerifier.verify(hostname, sslSession);
			}
		};

		HttpsURLConnection.setDefaultHostnameVerifier(localhostAcceptedHostnameVerifier);
	}

	private SSLUtil() {
		throw new UnsupportedOperationException("Do not instantiate libraries.");
	}
}