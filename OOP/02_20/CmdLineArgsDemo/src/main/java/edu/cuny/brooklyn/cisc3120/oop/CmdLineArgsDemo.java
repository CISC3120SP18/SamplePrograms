package edu.cuny.brooklyn.cisc3120.oop;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CmdLineArgsDemo {
	public static void main(String[] args) {
		// Step 1. create the command line parser
		CommandLineParser parser = new DefaultParser();

		// Step 2. create the Options
		Options options = new Options();
		// In Step 2, we can add options that do not have arguments, as
		// in "CmdLineArgsDemo -h" or "CmdLineArgsDemo --help". These
		// options are boolean options, meaning with it or without it.
		// The following shows that the default (without the option
		// being present in the command line) is false.
		options.addOption("h", "help", false, "print this message");
		options.addOption("a", "all", false, "do not hide entries starting with .");
		options.addOption("A", "almost-all", false, "do not list implied . and ..");
		options.addOption("b", "escape", false, "print octal escapes for nongraphic " + "characters");
		options.addOption("B", "ignore-backups", false, "do not list implied entried " + "ending with ~");
		options.addOption("c", false,
				"with -lt: sort by, and show, ctime (time of last " + "modification of file status information) with "
						+ "-l:show ctime and sort by name otherwise: sort " + "by ctime");
		options.addOption("C", false, "list entries by columns");
		// In Step 2, we can also add options that requires an argument, as
		// in "CmdLineArgsDemo --block-size 100". The following three examples
		// show three cases, one with long option only, and one with both, and
		// one with short one only.
		options.addOption(
				Option.builder().longOpt("block-size").desc("use SIZE-byte blocks").hasArg().argName("SIZE").build());
		options.addOption(
				Option.builder("s").longOpt("small").desc("emphasize small files whose size is less a threshold")
						.hasArg().argName("SIZE THRESHOLD").build());
		options.addOption(Option.builder("u").desc("emphasize files beloning to a given user").hasArg()
				.argName("USER ID").build());

		try {
			// parse the command line arguments
			CommandLine line = parser.parse(options, args);

			if (line.hasOption("help")) {
				// automatically generate the help statement
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("CmdLineArgsDemo", options);
			} else if (line.hasOption("block-size")) {
				// validate that block-size has been set
				// if it is set, print the value of block-size
				System.out.println(
						"The value of command line argument \"block-size\" is " + line.getOptionValue("block-size"));
			} else {
				System.out.println("block-size or help is not a command line argument in this run.");
			}
		} catch (ParseException exp) {
			System.out.println("Unexpected exception:" + exp.getMessage());
		}
	}
}
