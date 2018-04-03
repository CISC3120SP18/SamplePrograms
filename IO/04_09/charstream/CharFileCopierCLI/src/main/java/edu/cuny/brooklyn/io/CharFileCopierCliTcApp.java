package edu.cuny.brooklyn.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharFileCopierCliTcApp {
	private final static Logger LOGGER = LoggerFactory.getLogger(CharFileCopierCliTcApp.class);

	public static void main(String[] args) {

		FileReader reader = null;
		FileWriter writer = null;

		// System.out.println("Working Directory = " + System.getProperty("user.dir"));
		if (args.length < 2) {
			System.out.println("Usage: CharFileCopierCliTcApp <source_file> <destination_file>");
			System.exit(1);
		}

		String inputFilename = args[0];
		String outputFilename = args[1];
		
		LOGGER.debug("file.encoding = " + System.getProperty("file.encoding"));
		LOGGER.debug("Default Characterset: " + Charset.defaultCharset().name());
		
		try {
			// e.g., StandardCharsets.UTF_16LE
			reader = new FileReader(inputFilename);

			// e.g., StandardCharsets.UTF_16LE
			writer = new FileWriter(outputFilename);

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
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					LOGGER.error("Failed to close file", e);
					System.err.println("Failed to close the file input stream.");
				}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					LOGGER.error("Failed to close file", e);
					System.err.println("Failed to close the file output stream.");
				}
			}
			LOGGER.debug("Input and output streams closed.");
		}
	}
}
