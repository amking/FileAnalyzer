package com.yuan.app;

import java.nio.charset.Charset;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {

		/**
		 * Create Command line options
		 */
		Options options = new Options();
		options.addOption("f", true, "file name");
		options.addOption("e", true, "file encoding");
		options.addOption("h", false, "print help message");

		/**
		 * Parse the input arguments, and generate compiled Commandline object
		 */
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e1) {
			cmd = null;
		}

		// Print help message
		if ((cmd == null) || cmd.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(" [Options] ", options);
			return;
		}

		/**
		 * Get input filename option
		 */
		String filename = cmd.getOptionValue("f");
		if (filename == null) {
			System.err.println("Please specify the file path using -f option");
			return;
		}

		/**
		 * Get file encoding option
		 */
		String charsetName = cmd.getOptionValue("-e");
		charsetName = (charsetName != null) ? charsetName : "UTF-8";
		try {
			if (!Charset.isSupported(charsetName)) {
				System.err.println("File encoding not supported!");
				return;
			}
		} catch (Exception err) {
			System.err.println("File encoding not supported!");
			return;
		}

		// Analyze specified file, print the analyzed result
		// Result: number count + number sum
		FileAnalyzer fileAnalyzer = new FileAnalyzer(filename, charsetName);
		fileAnalyzer.analyzeFile().prettyPrint();

	}

}
