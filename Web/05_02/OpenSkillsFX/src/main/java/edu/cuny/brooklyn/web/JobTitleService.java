/*
 * The exercise is the TODO's in the getJobTitleList method
 */
package edu.cuny.brooklyn.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.LinkedList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobTitleService {
	private final static Logger LOGGER = LoggerFactory.getLogger(JobTitleService.class);
	private final static String API_URL_FMT = "http://api.dataatwork.org/v1/jobs/autocomplete?contains=\"%s\"";
	private final static String SUGGESTED_JOB_TITLE_KEY = "suggestion";

	public List<JobTitle> getJobTitleList(String keyword) throws IOException {
		if (keyword == null || keyword.isEmpty()) {
			throw new IllegalArgumentException("Keyword is blank.");
		}
		
		/*
		 * This method is based on a public Web API whose documentation is 
		 * at 
		 * https://github.com/workforce-data-initiative/skills-api/wiki/API-Overview
		 * 
		 * This method use the "autocomplete" API when a user provides a key word or phrase, 
		 * the API returns a list of job titles that contains the keyword or the phrase if it
		 * finds any. 
		 */
		
		/*
		 * TODO: complete the method, following the steps below,
		 *       1. format an api URL with parameter is filled. 
		 *          Hint: you may use String.format() method. In addition
		 *          if you wish to allow a phrase, you must call the
		 *          URLEncoder class's encode method, i.e.i,  
		 *          URLEncoder.encode(keyword, StandardCharsets.UTF_8.name())
		 *       2. Send a request to the Web server, and obtain a connection
		 *       3. Check whether the HTTP response status code is OK, if 
		 *          it is OK, go on; otherwise, log the error message, and 
		 *          throw an IOException.
		 *       4. Read the response. Parsing the JSON object may be 
		 *          difficult. The readResponse method is provided, and
		 *          you need to call it. 
		 *
		 */

		return null;
	}

	private List<JobTitle> readResponse(HttpURLConnection conn) throws IOException {
		List<JobTitle> jobTitleList = new LinkedList<JobTitle>();
		try (InputStream in = conn.getInputStream(); JsonReader reader = Json.createReader(in)) {
			JsonArray jsonArray = reader.readArray();
			for (int i = 0; i < jsonArray.size(); i++) {
				JsonObject obj = (JsonObject) jsonArray.get(i);
				String title = obj.getString(SUGGESTED_JOB_TITLE_KEY);
				LOGGER.debug("Title sugggested: " + title);
				if (title != null && !title.isEmpty()) {
					jobTitleList.add(new JobTitle(title));
				}
			}
		}
		return jobTitleList;
	}
}
