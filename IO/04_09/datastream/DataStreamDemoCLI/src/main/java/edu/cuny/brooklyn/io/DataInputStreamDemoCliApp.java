package edu.cuny.brooklyn.io;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataInputStreamDemoCliApp {
	private final static Logger LOGGER = LoggerFactory.getLogger(DataInputStreamDemoCliApp.class);
	
	public static void main(String... args) {
		if (args.length < 1) {
			System.out.println("Usage: DataInputStreamDemoCliApp <input_file_name>");
			System.exit(1);
		}
		
		DataInputStreamDemoCliApp app = new DataInputStreamDemoCliApp();
		
		try {
			List<Long> numbers = app.readNumbersFromFile(args[0], new LinkedList<Long>());
			System.out.println("Read " + numbers.size() + " numbers from " + args[0] + ". The numbers are");
			app.printNumbers(numbers);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open " + args[0]);
			LOGGER.error("Cannot open " + args[0], e);
		} catch (IOException e) {
			System.out.println("Cannot read from " + args[0]);
			LOGGER.error("Cannot read from " + args[0], e);
		}
	}
	
	private List<Long> readNumbersFromFile(String filename, List<Long> numbers) throws FileNotFoundException, IOException {
		if (filename == null || filename.isEmpty() || numbers == null) {
			throw new IllegalArgumentException("Arguments must not be null or empty.");
		}
		
		try (DataInputStream in = new DataInputStream(new FileInputStream(filename))) {
			int numNumbers = in.readInt();
			for (int i=0; i<numNumbers; i++) {
				numbers.add(in.readLong());
			}
		}
		
		return numbers;
	}
	
	private void printNumbers(List<Long> numbers) {
		numbers.forEach(n -> System.out.println(n));
	}
}
