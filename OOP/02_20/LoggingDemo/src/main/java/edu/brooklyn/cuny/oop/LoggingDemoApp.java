package edu.brooklyn.cuny.oop;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingDemoApp {
	private final static Logger LOGGER = LoggerFactory.getLogger(LoggingDemoApp.class);
	private Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		/*
		 * Consider a simple application that outputs the console for users.
		 * Print out to the console any diagnostic information can interrupt users. 
		 * One may redirect some logging messages to a file. 
		 * 
		 * In addition, having a logging file enables us to examine the program
		 * behavior at a later time even if when the application has shipped
		 * to the users. 
		 */
		LOGGER.info("LoggingDemoApp starts.");
		LoggingDemoApp app = new LoggingDemoApp();
		
		String line;
		do {
			System.out.print("Enter a line: ");
			line = app.scanner.nextLine();
			System.out.println("The number of words entered is " + line.split("\\s+").length);
			LOGGER.debug("User entered: \"" + line + "\"");
		} while (!line.equals("Goodbye"));
		System.out.println("Application exits.");
		LOGGER.info("LoggingDemoApp exits.");
	}
}
