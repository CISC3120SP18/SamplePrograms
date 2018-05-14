package edu.cuny.brooklyn.web;

import static org.junit.Assert.assertTrue;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import edu.cuny.brooklyn.web.service.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GpaMongoWebService.class)
public class GpaMongoWebServiceTests {
	private final static Logger LOGGER = LoggerFactory.getLogger(GpaMongoWebServiceTests.class);

	@LocalServerPort
	private int port;

	private TestRestTemplate restTemplate = new TestRestTemplate();

	private HttpHeaders headers = new HttpHeaders();

	@Test
	public void testAddStudent() throws KeyManagementException, NoSuchAlgorithmException {
		sslCheckingOff();

		String expected = "\"errorCode\":404,\"httpStatus\":\"NOT_FOUND\",\"message\":\"Student hash(1537) already exists\"";

		
		Student student = new Student("01", "Will", 3.14);

		HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/gpa/addstudent"), HttpMethod.POST,
				entity, String.class);
		
		LOGGER.info("Response body: " + response.getBody());
		
		assertTrue(response.getBody().contains(expected));
	}

	@Test
	public void testRetrieveStudentGpaByStudentId()
			throws JSONException, KeyManagementException, NoSuchAlgorithmException {
		sslCheckingOff();

		String expected = "{\"sid\":\"01\",\"gpa\":3.0}";
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/gpa/viewgpa/01"), HttpMethod.GET,
				entity, String.class);

		LOGGER.info("Response body: " + response.getBody());

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	private String createURLWithPort(String uri) {
		return "https://localhost:" + port + uri;
	}
	
	private void sslCheckingOff() throws KeyManagementException, NoSuchAlgorithmException {
		SSLUtil.setDefaultHostVerifier();
		SSLUtil.turnOffSslChecking();
	}
}
