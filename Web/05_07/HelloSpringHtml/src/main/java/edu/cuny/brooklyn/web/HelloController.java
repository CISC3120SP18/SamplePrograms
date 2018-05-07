package edu.cuny.brooklyn.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * REST stands for Representational State Transfer, a protocol that
 * governs how a client and server exchanges data over a stateless
 * protocol, namely HTTP, where "stateless" can be interpreted as
 * that the web server has no assumption that it remembers what it
 * does with a client. 
 * 
 * The response is prepared by the method decorated with 
 * RequestMapping, well, perhaps, we can call it a 
 * "request handler". 
 */
@RestController
public class HelloController {

    /*
     * HTTP is a request-response protocol. A client sends a request to the server,
     * and the server handles the request, and replies the client with a response. 
     * RequestMapping establishes the correspondence between the URL in the HTTP
     * request with the method or the class that handles the request and returns 
     * the response.
     */
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/hello")
    public String hello() {
    	/* In our first attempt, we return a HTML page, well, for human consumption. */
        return "<!DOCTYPE html> <!-- required by any XHTML -->\r\n"
                + "<html lang=\"en\"> <!-- this is a comment -->\r\n" 
                + "<head>\r\n"
                + "  <title>Hello from Spring</title><!-- required for HTML 5-->\r\n"
                + "  <meta charset=\"utf-8\" /> \r\n" 
                + "  <style type=\"text/css\">\r\n" 
                + "    h1 {color: red;}\r\n"
                + "  </style>\r\n" 
                + "</head>\r\n" 
                + "<body>\r\n" 
                + "<h1>Hello, I am Spring</h1>\r\n" + "\r\n"
                + "<p>Click to show an <a href=\"/HttpUrlConnectionApiDoc\">Api Doc</a>.</p>\r\n" 
                + "</body>\r\n"
                + "</html>\r\n";
    }

    @RequestMapping("/HttpUrlConnectionApiDoc")
    public String apiDoc() {
        return "<!DOCTYPE html> <!-- required by any XHTML -->\r\n"
                + "<html lang=\"en\"> <!-- this is a comment -->\r\n" 
                + "<head>\r\n"
                + "  <title>Example HTML 5: HttpURLConnection</title><!-- required for HTML 5-->\r\n"
                + "  <meta charset=\"utf-8\" /> \r\n" 
                + "  <style type=\"text/css\">\r\n" 
                + "    h1 {color: red;}\r\n"
                + "  </style>\r\n" 
                + "</head>\r\n" 
                + "<body>\r\n" 
                + "<h1>Class HttpURLConnection</h1>\r\n" 
                + "\r\n"
                + "<p>A URLConnection with support for HTTP-specific features. See the spec for details.</p>\r\n"
                + "</body>\r\n" 
                + "</html>\r\n";
    }

    @RequestMapping("/apiDoc")
    public String apiDoc(@RequestParam("name") String name) {
        String[] pages = { "<!DOCTYPE html> <!-- required by any XHTML -->\r\n"
                + "<html lang=\"en\"> <!-- this is a comment -->\r\n" 
                + "<head>\r\n"
                + "  <title>Example HTML 5: HttpURLConnection</title><!-- required for HTML 5-->\r\n"
                + "  <meta charset=\"utf-8\" /> \r\n" 
                + "  <style type=\"text/css\">\r\n" 
                + "    h1 {color: red;}\r\n"
                + "  </style>\r\n" 
                + "</head>\r\n" 
                + "<body>\r\n" 
                + "<h1>Class HttpURLConnection</h1>\r\n" 
                + "\r\n"
                + "<p>A URLConnection with support for HTTP-specific features. See the spec for details.</p>\r\n"
                + "</body>\r\n" 
                + "</html>\r\n"
                ,
                "<!DOCTYPE html> <!-- required by any XHTML -->\r\n"
                + "<html lang=\"en\"> <!-- this is a comment -->\r\n" 
                + "<head>\r\n"
                + "  <title>API DOC Not Available</title><!-- required for HTML 5-->\r\n"
                + "  <meta charset=\"utf-8\" /> \r\n" 
                + "  <style type=\"text/css\">\r\n"
                + "    h1 {color: red;}\r\n" + "  </style>\r\n" 
                + "</head>\r\n" 
                + "<body>\r\n"
                + "<h1>API Documentation Not Avaialble</h1>\r\n" 
                + "</body>\r\n" 
                + "</html>\r\n" 
                };
        switch (name) {
        case "HttpUrlConnection":
            return pages[0];
        default:
            return pages[1];
        }
    }
}
