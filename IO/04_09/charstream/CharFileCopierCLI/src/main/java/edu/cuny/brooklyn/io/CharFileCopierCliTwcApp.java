package edu.cuny.brooklyn.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharFileCopierCliTwcApp {
	private final static Logger LOGGER = LoggerFactory.getLogger(CharFileCopierCliTwcApp.class);

	public static void main(String[] args) {

		// System.out.println("Working Directory = " + System.getProperty("user.dir"));
		if (args.length < 2) {
			System.out.println("Usage: CharFileCopierCliTcApp <source_file> <destination_file>");
			System.exit(1);
		}

		String inputFilename = args[0];
		String outputFilename = args[1];
		
		LOGGER.debug("file.encoding = " + System.getProperty("file.encoding"));
		LOGGER.debug("Default Characterset: " + Charset.defaultCharset().name());
		
		try (FileReader reader = new FileReader(inputFilename);
			FileWriter writer = new FileWriter(outputFilename)) {

			LOGGER.debug("FileReader and FileWriter opened.");
			LOGGER.debug("FileReader's encoding: " + reader.getEncoding());
			LOGGER.debug("FileWriter's encoding: " + writer.getEncoding());
			
			long numCharsCopied = 0l;
			int c;
			while ((c = reader.read()) != -1) {
				writer.write(c);
				numCharsCopied ++;
			}
			System.out.println(numCharsCopied + " characters copied from " + inputFilename + " to " + outputFilename);
			LOGGER.debug(numCharsCopied + " characters copied from " + inputFilename + " to " + outputFilename);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open least one of the two files: " + inputFilename + " and " + outputFilename);
		} catch (IOException e) {
			LOGGER.error("Failed to copy file", e);
			System.out.println("Failed to copy file");
		} 
	}
}
