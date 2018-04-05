package edu.cuny.brooklyn.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ScannerDemoCliApp {
	public static void main(String[] args) {
		try {
			ScannerDemoCliApp demo = new ScannerDemoCliApp();
			System.out.println("Use default delimiter to scan files/demoinput.txt");
			demo.scanFile("files/demoinput.txt", "");
			System.out.println("-------------------------------------------------");
			System.out.println("Use delimiter ([\\p{javaWhitespace},',\",\\,]+) to scan files/demoinput.txt");
			demo.scanFile("files/demoinput.txt", "([\\p{javaWhitespace},',\",\\,]+)");
		} catch (FileNotFoundException e) {
			System.err.println("Cannot find the file files/demoinput.txt");
		}
	}

	void scanFile(String filename, String delimiterPattern) throws FileNotFoundException {
		List<Integer> intList = new LinkedList<Integer>();
		List<Double> doubleList = new LinkedList<Double>();
		List<String> wordList = new LinkedList<String>();
		// List<String> otherList = new LinkedList<String>();
		try (Scanner scanner = new Scanner(new FileInputStream(filename))) {
			if (delimiterPattern != null && !delimiterPattern.isEmpty()) {
				scanner.useDelimiter(delimiterPattern);
			}
			while (scanner.hasNext()) {
				if (scanner.hasNextInt()) {
					intList.add(scanner.nextInt());
				} else if (scanner.hasNextDouble()) {
					doubleList.add(scanner.nextDouble());
				} else if (scanner.hasNext(Pattern.compile("[\\w]+"))) {
					wordList.add(scanner.next(Pattern.compile("[\\w]+")));
				} else {
					scanner.next(); // important: cannot leave scanner.next() out!
					// otherList.add(scanner.next());
				}
			}
		}

		System.out.println("Found integers:");
		intList.forEach((i) -> System.out.format("\t%3d%n", i));

		System.out.println("Found doubles:");
		doubleList.forEach((d) -> System.out.format("\t%3.1f%n", d));

		System.out.println("Found words:");
		wordList.forEach((w) -> System.out.format("\t%s%n", w));

		// System.out.println("Left overs:");
		// otherList.forEach((w) -> System.out.format("\t%s%n", w));
	}
}
