package edu.cuny.brooklyn.io;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/*
 * Write integer and double values to binary and text files
 * for comparison
 */
public class FormattedOutputDemoCliApp {
	public static void main(String[] args) {
		Random rng = new Random();

		List<Integer> intList = new LinkedList<Integer>();
		for (int i = 0; i < 16; i++) {
			intList.add(rng.nextInt());
		}

		List<Double> doubleList = new LinkedList<Double>();
		for (int i = 0; i < 16; i++) {
			doubleList.add(rng.nextDouble()*1000000);
		}

		// System.out is a PrintStream, and we can use the format method
		System.out.format("The JVM's default locale is %s.%n", Locale.getDefault().toString());
		System.out.format("The JVM's file.encoding property is %s.%n", System.getProperty("file.encoding"));
		System.out.format("The JVM's default Characterset is %s.%n", Charset.defaultCharset().name());
		System.out.format("However, we will use UTF-8 regardless the default charset is for portability.%n");

		FormattedOutputDemoCliApp demo = new FormattedOutputDemoCliApp();
		try {
			// You may try different locale, e.g., Locale.FRANCE, Locale.GERMAN etc
			demo.writeToTextFile("files/intlist.txt", Locale.getDefault(), StandardCharsets.UTF_8, intList);
//			demo.writeToTextFile("files/intlist.txt", Locale.FRANCE, StandardCharsets.UTF_8, intList);
//			demo.writeToTextFile("files/intlist.txt", Locale.GERMAN, StandardCharsets.UTF_8, intList);			
			System.out.println("wrote files/intlist.txt.");
		} catch (FileNotFoundException e) {
			System.err.println("Cannot create files/intlist.txt for writing");
		}

		try {
			demo.writeToTextFile("files/doublelist.txt", Locale.getDefault(), StandardCharsets.UTF_8, doubleList);
			System.out.println("wrote files/doublelist.txt.");
		} catch (FileNotFoundException e) {
			System.err.println("Cannot create files/doublelist.txt for writing");
		}

		try {
			demo.writeToBinaryFile("files/intlist.bin", intList);
			System.out.println("wrote files/intlist.bin.");
		} catch (FileNotFoundException e) {
			System.err.println("Cannot create files/intlist.bin for writing");
		} catch (IOException e) {
			System.err.println("Cannot write to files/intlist.bin for writing");
		}

		try {
			demo.writeToBinaryFile("files/doublelist.bin", doubleList);
			System.out.println("wrote files/doublelist.bin.");
		} catch (FileNotFoundException e) {
			System.err.println("Cannot create files/doublelist.bin for writing");
		} catch (IOException e) {
			System.err.println("Cannot write to files/doublelist.bin for writing");
		}
	}

	void writeToTextFile(String filename, Locale locale, Charset charset, List<? extends Number> numberList)
			throws FileNotFoundException {
		try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename), charset))) {
			NumberFormat formatter = NumberFormat.getInstance(locale);
			numberList.forEach(n -> {
//				 String fmt = n instanceof Integer ? "%d%n" : "%f%n";
//				 writer.format(locale, fmt, n);
				writer.println(formatter.format(n));
			});
		}
	}

	void writeToBinaryFile(String filename, List<? extends Number> numberList)
			throws FileNotFoundException, IOException {
		try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {
			for (Number n : numberList) {
				if (n instanceof Integer) {
					out.writeInt((Integer) n.intValue());
				} else {
					out.writeDouble((Double) n.doubleValue());
				}
			}
		}
	}
}
