package edu.cuny.brooklyn.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.json.JsonObject;

public class NasaApod {

	/* Example: 
	 * {
	 * 		"date": "2018-05-07",
	 * 		"explanation": "Why is there a large boulder near the center of Tycho's peak? Tycho crater on the Moon is one of the easiest features to see, visible even to the unaided eye (inset, lower right).  But at the center of Tycho (inset, upper left) is a something unusual -- a 120-meter boulder.  This boulder was imaged at very high resolution at sunrise, over the past decade, by the Moon-circling Lunar Reconnaissance Orbiter (LRO).  The leading origin hypothesis is that that the boulder was thrown during the tremendous collision that formed Tycho crater about 110 million years ago, and by chance came back down right near the center of the newly-formed central mountain. Over the next billion years meteor impacts and moonquakes should slowly degrade Tycho's center, likely causing the central boulder to tumble 2000 meters down to the crater floor and disintegrate.",
	 * 		"hdurl": "https://apod.nasa.gov/apod/image/1805/TychoBoulder2_LRO_960.jpg",
	 * 		"media_type": "image",
	 * 		"service_version": "v1",
	 * 		"title": "The Unusual Boulder at Tycho's Peak",
	 * 		"url": "https://apod.nasa.gov/apod/image/1805/TychoBoulder2_LRO_960.jpg"
	 * }
	 */
	
	private final static String DATE_KEY = "date";
	private final static String TITLE_KEY = "title";
	private final static String EXPLANATION_KEY = "explanation";
	private final static String MEDIA_TYPE_KEY = "media_type";
	private final static String URL_KEY = "url";
	private final static String HDURL_KEY = "hdurl";
	
	public final static DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	
	private LocalDate date;
	private String explanantion;
	private String title;
	private URI hdPictureURI;
	private URI mediaURI;
	private String mediaType;
	
	public NasaApod(LocalDate date, String title, String explanation, String mediaType, URI mediaURI,
			URI hdPictureURI) {
		this.date = date;
		this.explanantion = explanation;
		this.title = title;
		this.mediaType = mediaType;
		this.mediaURI = mediaURI;
		this.hdPictureURI = hdPictureURI;
	}

	public LocalDate getDate() {
		return date;
	}
	
	public String getDateAsString() {
		return date.format(DATETIME_FORMATTER);
	}

	public String getExplanantion() {
		return explanantion;
	}

	public String getTitle() {
		return title;
	}

	public URI getHdPictureURI() {
		return hdPictureURI;
	}

	public URI getMediaURI() {
		return mediaURI;
	}

	public String getMediaType() {
		return mediaType;
	}
	
	public static NasaApod fromJsonObject(JsonObject obj) throws URISyntaxException {
		String date = obj.getString(DATE_KEY);
		LocalDate localDate = LocalDate.parse(date, DATETIME_FORMATTER);
		String title = obj.getString(TITLE_KEY);
		String explanation = obj.getString(EXPLANATION_KEY);
		String mediaType = obj.getString(MEDIA_TYPE_KEY);
		URI mediaURI = new URI(obj.getString(URL_KEY));
		URI hdPictureURI = null;
		if (mediaType.equals("image")) {
			hdPictureURI = new URI(obj.getString(HDURL_KEY));
		}
		
		return new NasaApod(localDate, title, explanation, mediaType, mediaURI, hdPictureURI);
	}
	
	public static String formatDate(LocalDate date) {
		return date.format(DATETIME_FORMATTER);
	}

}
