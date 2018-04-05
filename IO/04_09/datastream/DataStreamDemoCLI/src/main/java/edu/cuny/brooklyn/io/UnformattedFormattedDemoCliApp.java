package edu.cuny.brooklyn.io;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class UnformattedFormattedDemoCliApp {
	public static void main(String[] args) {
		Random rng = new Random();

		List<Integer> intList = new LinkedList<Integer>();
		for (int i = 0; i < 16; i++) {
			intList.add(rng.nextInt());
		}

		List<Double> doubleList = new LinkedList<Double>();
		for (int i = 0; i < 16; i++) {
			doubleList.add(rng.nextDouble());
		}

		// what do we do with the locale?
		System.out.format("The JVM's default locale is %s.%n", Locale.getDefault().toString());

		UnformattedFormattedDemoCliApp demo = new UnformattedFormattedDemoCliApp();
		try {
			demo.writeToTextFile("files/intlist.txt", Locale.getDefault(), intList);
			System.out.println("wrote files/intlist.txt.");
		} catch (FileNotFoundException e) {
			System.err.println("Cannot create files/intlist.txt for writing");
		}

		try {
			demo.writeToTextFile("files/doublelist.txt", Locale.getDefault(), doubleList);
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

	void writeToTextFile(String filename, Locale locale, List<? extends Number> numberList)
			throws FileNotFoundException {
		try (PrintWriter writer = new PrintWriter(new FileOutputStream(filename))) {
			numberList.forEach(n -> {
				String fmt = n instanceof Integer ? "%d\n" : "%f\n";
				writer.format(locale, fmt, n);
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
