package com.yuan.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

public class FileAnalyzer {

	private static final String DIGITS = "0123456789";

	private String filename = null;
	private String charsetName = null;

	private int numberCount = 0;
	private BigDecimal numberSum = BigDecimal.ZERO;
	private boolean isFileAnalyzed = false;

	public FileAnalyzer(String filename, String charsetName) {
		this.filename = filename.trim();
		this.charsetName = charsetName.trim();
	}

	/**
	 * Analyze specified file, find all the numbers in this file, and calculate
	 * the count and sum of these numbers.
	 */
	public FileAnalyzer analyzeFile() {

		System.out.println("Start to analyze file " + filename);

		// Reset the analyzing result
		numberCount = 0;
		numberSum = BigDecimal.ZERO;
		isFileAnalyzed = false;

		// Make sure the file path is not empty
		if ((filename == null) || (filename.trim().equalsIgnoreCase(""))) {
			return this;
		}

		// The file should be an existing regular file
		File file = new File(filename);
		if (!file.exists() || file.isDirectory()) {
			System.err
					.println("Please check whether the input file is an existing regular file");
			return this;
		}

		// Using the BufferedReader to load the file stream
		BufferedReader b = null;
		try {
			b = new BufferedReader(new InputStreamReader(new FileInputStream(
					file), charsetName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err
					.println("File not exist: please check whether the input file is an existing regular file");
			return this;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.err
					.println("Unsupported charset: please make sure that you have specified the correct charset of this file");
			return this;
		}

		// analyze each lines
		try {
			String line;
			while ((line = b.readLine()) != null) {
				analyzeLine(line);
			}
		} catch (IOException e) {
			// Analyze fails due to readline fails,
			e.printStackTrace();
		}

		// close the file streams
		if (b != null) {
			try {
				b.close();
			} catch (IOException e) {
				// Close file stream fails, we don't need to handle this error
				e.printStackTrace();
			}
		}

		isFileAnalyzed = true;
		
		System.out.println("Analyze done!");

		return this;

	}

	/**
	 * Iterate all the columns in this line, and update the analyzing result if
	 * this column can be parsed as a number.
	 * 
	 * @param line
	 *            : the line which will be analyzed
	 */
	private void analyzeLine(String line) {

		// Skip the line which does not have numbers
		if (!isNumberExistsInLine(line)) {
			return;
		}

		String[] columns = line.split(" ");

		// skip empty line
		if (columns == null) {
			return;
		}

		/**
		 * Iterate all the columns in this line, Update the numberCount and the
		 * numberSum field if this column is an valid number
		 */
		for (String column : columns) {

			// Quick check whether this column is a number
			if (!isNumberExistsInLine(column)) {
				continue;
			}

			// Update numberSum and numberCount field for valid number type
			// column
			try {
				float floatColumn = Float.parseFloat(column);
				numberSum = numberSum.add(new BigDecimal(floatColumn));
				numberCount++;
			} catch (NumberFormatException formatExp) {
				// ignore the column which is not a number
			}
		}

	}

	/**
	 * Pretty print analyzed Result
	 */
	public void prettyPrint() {
		if (isFileAnalyzed) {
			System.out.println(String.format(
					"%d numbers found, total amount is %s", numberCount,
					numberSum.toString()));
		} else {
			System.err.println("The file is not analyzed successfully!");
		}

	}

	/**
	 * Quick Check if there are some numbers exists in the line
	 * 
	 * @param str
	 * @return
	 */
	private boolean isNumberExistsInLine(String str) {

		if ((str == null) || (str.trim().length() == 0)) {
			return false;
		}

		for (char c : str.toCharArray()) {
			if (DIGITS.indexOf(c) >= 0) {
				return true;
			}
		}

		return false;
	}

}
