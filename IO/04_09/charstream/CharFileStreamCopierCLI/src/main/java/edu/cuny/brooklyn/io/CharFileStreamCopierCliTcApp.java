package edu.cuny.brooklyn.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharFileStreamCopierCliTcApp {
	private final static Logger LOGGER = LoggerFactory.getLogger(CharFileStreamCopierCliTcApp.class);

	public static void main(String[] args) {

		InputStreamReader reader = null;
		OutputStreamWriter writer = null;

		// System.out.println("Working Directory = " + System.getProperty("user.dir"));
		if (args.length < 4) {
			System.out.println("Usage: CharFileCopierCliTcApp " + "<source_file> <source_character_encoding> "
					+ "<destination_file> <destination_character_encoding>");
			System.exit(1);
		}

		String inputFilename = args[0];
		String srcCharEncoding = args[1];
		String outputFilename = args[2];
		String dstCharEncoding = args[3];
		if (!Charset.isSupported(args[1])) {
			LOGGER.error("Charset " + args[1] + " is not supported.");
			System.err.println("Charset " + args[1] + " is not supported.");
			System.exit(1);
		}
		if (!Charset.isSupported(args[3])) {
			LOGGER.error("Charset " + args[3] + " is not supported.");
			System.err.println("Charset " + args[3] + " is not supported.");
			System.exit(1);
		}

		LOGGER.debug("file.encoding = " + System.getProperty("file.encoding"));
		LOGGER.debug("Default Characterset: " + Charset.defaultCharset().name());

		try {
			// e.g., StandardCharsets.UTF_16LE
			reader = new InputStreamReader(new FileInputStream(inputFilename), srcCharEncoding);

			// e.g., StandardCharsets.UTF_16LE
			writer = new OutputStreamWriter(new FileOutputStream(outputFilename), dstCharEncoding);

			LOGGER.debug("InputStreamReader and OutputStreamWriter opened.");
			LOGGER.debug("InputStreamReader's encoding: " + reader.getEncoding());
			LOGGER.debug("OutputStreamWriter's encoding: " + writer.getEncoding());
			
			long numCharsCopied = 0l;
			int c;
			while ((c = reader.read()) != -1) {
				LOGGER.debug(String.format("Read %08x", c));
				
				if (isReplacementCharacter(c)) {
					throw new IllegalStateException("The InputStreamReader does not appear to be correctly encoded in " + reader.getEncoding());
				}
				writer.write(c);
				numCharsCopied++;
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
	
	private static boolean isReplacementCharacter(int c) {
		return c == 0xfffd;
	}
}
