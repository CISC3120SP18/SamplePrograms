package edu.cuny.brooklyn.web;

import java.net.URI;
import java.net.URISyntaxException;

public class ResourceIdentifierCLI {

	public static void main(String[] args) {
		String resource = "foo://johnsmith@example.com:8042/over/there?name=ferret&class=senior&major=cisc#gpa";
		printURIComponents(resource);
		System.out.println("--------------------");

		// "-" isn't allowed as a part of scheme, so ...
		resource = "-" + resource;
		printURIComponents(resource);
		System.out.println("--------------------");
		
		resource = "http://www.brooklyn.cuny.edu";
		printURIComponents(resource);
		System.out.println("--------------------");
		
		resource = "foo://johnsmith@example.com:8042/over/there?name=ferret&class=senior&major=cisc#gpa";
		printURIComponents(resource);
		checkOnIdentities(resource);
		System.out.println("--------------------");
		
		resource = "foo://johnsmith@example.com:8042/over/../over/there?name=ferret&class=senior&major=cisc#gpa";
		printURIComponents(resource);
		checkOnIdentities(resource);
		System.out.println("--------------------");
		
		resource = "file:///tmp/"; // authority is empty
		printURIComponents(resource);
		checkOnIdentities(resource);
		System.out.println("--------------------");
		
		resource = "http://java.sun.com:";
		printURIComponents(resource);
		checkOnIdentities(resource);
		System.out.println("--------------------");
		
		resource = "foo://johnsmith@example.com:8042/over/../over/././././there?name=ferret&class=senior&major=cisc#gpa";
		doNormalization(resource);
		System.out.println("--------------------");
		
		String u = "http://www.example.com/doc/class/grades";
		String v = "student/gpa";
		doResolution(u, v);
		System.out.println("--------------------");
		
		u = "http://www.example.com/doc/class/grades/";
		v = "student/gpa";
		doResolution(u, v);
		System.out.println("--------------------");
		
		u = "http://www.example.com/doc/class/grades/student";
		v = "student/gpa";
		doResolution(u, v);
		System.out.println("--------------------");
		
		u = "http://www.example.com/doc/class/grades/student/";
		v = "student/gpa";
		doResolution(u, v);
		System.out.println("--------------------");
		
		u = "http://www.example.com/doc/class/grades";
		v = "http://www.example.com/doc";
		doRelativization(u, v);
		System.out.println("--------------------");
		
		u = "http://www.example.com/doc/class/grades/";
		v = "http://www.example.com/doc/";
		doRelativization(u, v);
		System.out.println("--------------------");
		
		u = "http://www.example.com/doc/class/grades/student";
		v = "http://www.example.com/doc/";
		doRelativization(u, v);
		System.out.println("--------------------");
		
		u = "http://www.example.com/doc/class/grades/student";
		v = "http://www.example.com/doc";
		doRelativization(u, v);
		System.out.println("--------------------");
		
		resource = "http://www.example.com/doc/class/student/¡hola!";
		doToString(resource);
		System.out.println("--------------------");
		
		resource = "http://www.example.com/doc/class/student/你好";
		doToString(resource);
		System.out.println("--------------------");
	
		resource = "http://www.example.com/doc/class/student/مرحبا";
		doToString(resource);
		System.out.println("--------------------");
		
		resource = "http://www.example.com/doc/class/student/שלום";
		doToString(resource);
		System.out.println("--------------------");
		
		// how about new URI(uri.toASCIIString).equals(new URI(uri.toString()))?
	}
	

	public static void printURIComponents(String resource) {
		try {
			URI uri = new URI(resource);

			// print out various component of the URI
			// note the syntax of hierarchical URI
			// [scheme:][//authority][path][?query][#fragment]
			System.out.println("The scheme is " + uri.getScheme());
			System.out.println("The authority is " + uri.getAuthority());
			System.out.println("The path is " + uri.getPath());
			System.out.println("The query is " + uri.getQuery());
			System.out.println("The fragment is " + uri.getFragment());

			// note the syntax of the net authority
			// [user-info@]host[:port]
			System.out.println("The user-info is " + uri.getUserInfo());
			System.out.println("The host is " + uri.getHost());
			System.out.println("The port is " + uri.getPort());
		} catch (URISyntaxException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void checkOnIdentities(String resource) {
		try {
			URI u = new URI(resource);
			System.out.println("Checking on " + resource);

			System.out.println("u == v? " 
					+ new URI(u.getScheme(), 
								u.getSchemeSpecificPart(), 
								u.getFragment()).equals(u));

			// in all cases

			System.out.println("u == v? "
					+ new URI(u.getScheme(),
								u.getAuthority(), 
								u.getPath(), 
								u.getQuery(), 
								u.getFragment()).equals(u));

			// if u is hierarchical, and

			System.out.println("u == v? " 
					+ new URI(u.getScheme(), 
								u.getUserInfo(), 
								u.getHost(), 
								u.getPort(),
								u.getPath(), 
								u.getQuery(), 
								u.getFragment()).equals(u));

		} catch (URISyntaxException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void doNormalization(String resource) {
		try {
			URI uri = new URI(resource);
			System.out.println("Before normalization: " + uri.toString());
			
			URI uriNormalized = uri.normalize();
			System.out.println("After normalization: " + uriNormalized.toString());
		} catch (URISyntaxException e) {
			System.err.println(e.getMessage());
		}
	}

	
	public static void doResolution(String ru, String rv) {
		try {
			URI u = new URI(ru);
			URI v = new URI(rv);
			
			System.out.println("u: " + u.toString());
			System.out.println("v: " + v.toString());
			URI r = u.resolve(v);
			System.out.println("u.resolve(v): " + r.toString());
			r = v.resolve(u);
			System.out.println("v.resolve(u): " + r.toString());
		} catch (URISyntaxException e) {
			System.err.println(e.getMessage());
		}
	}
	public static void doRelativization(String ru, String rv) {
		try {
			URI u = new URI(ru);
			URI v = new URI(rv);
			
			System.out.println("u: " + u.toString());
			System.out.println("v: " + v.toString());
			URI r = u.relativize(v);
			System.out.println("u.relativize(v): " + r.toString());
			r = v.relativize(u);
			System.out.println("v.relativize(u): " + r.toString());
		} catch (URISyntaxException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void doToString(String resource) {
		try {
			URI uri = new URI(resource);
			
			System.out.println("uri.toString(): " + uri.toString());
			System.out.println("uri.toASCIIString(): " + uri.toASCIIString());
		} catch (URISyntaxException e) {
			System.err.println(e.getMessage());
		}
	}
}
