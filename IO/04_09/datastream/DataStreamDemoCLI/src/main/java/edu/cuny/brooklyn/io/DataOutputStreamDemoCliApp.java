package edu.cuny.brooklyn.io;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * This program reads a list of integers from the command line, and write 
 * them to a binary file.
 */
public class DataOutputStreamDemoCliApp {
	private final static Logger LOGGER = LoggerFactory.getLogger(DataOutputStreamDemoCliApp.class);
	
	public static void main(String... args) {
		if (args.length < 2) {
			System.out.println("Usage: DataOutputStreamDemoCliApp <output_file_name> <int_1> <int_2> <...>");
			System.exit(1);
		}
		
		DataOutputStreamDemoCliApp app = new DataOutputStreamDemoCliApp();
		
		List<Long> numbers = app.stringArrayToNumberList(args, 1, new LinkedList<Long>());
		try {
			app.writeNumberListToFile(numbers, args[0]);
			System.out.println("Wrote numbers to " + args[0]);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open " + args[0]);
			LOGGER.error("Cannot open " + args[0], e);
		} catch (IOException e) {
			System.out.println("Cannot write to " + args[0]);
			LOGGER.error("Cannot write to " + args[0], e);
		}
	}
	
	private void writeNumberListToFile(List<Long> numbers, String filename) throws FileNotFoundException, IOException {
		try (DataOutputStream out = new DataOutputStream(new FileOutputStream(filename))) {
			out.writeInt(numbers.size());
			for (long n: numbers) {
				out.writeLong(n);
			}
		}
	}
	
	private List<Long> stringArrayToNumberList(String[] strings, int startIndex, List<Long> numbers) {
		if (strings == null || numbers == null) {
			throw new IllegalArgumentException("Arguments must not be null.");
		}
		
		for (int i=startIndex; i<strings.length; i++) {
			numbers.add(Long.parseLong(strings[i]));
		}
		
		return numbers;
	}
}
