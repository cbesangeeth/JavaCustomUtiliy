/**
 * 
 */
package com.sangeeth.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sangeeth
 *
 */
public class FileSearch {

	// Output directory
	private static String outputDirectory = "C:/Users/Elcot/Desktop/destination";

	// Search string
	private static String searchString = "browser";

	public static void main(String[] args) {
		// Parent Directory
		String parentDirectory = "C:/Users/Elcot/Desktop/Gitter-master/Gitter-master/node_modules/@angular/animations";

		// File object
		File myDirectory = new File(parentDirectory);
		Map<String, String> myMap = new HashMap<String, String>();
		// Map<String, String> myFinalMap = new HashMap<String, String>();

		// File Look up
		if (myDirectory.isDirectory()) {
			File arr[] = myDirectory.listFiles();

			System.out.println("**********************************************");
			System.out.println("Files from main directory : " + myDirectory);
			System.out.println("**********************************************");

			// Calling this method to deeper search
			deeperSearch(arr, 0, 0, myMap);
		}

		// File Search and insert it into DB
		/*
		 * if (myMap.size() > 0) { // FileDAO myDAO = new FileDAO(); for
		 * (Map.Entry<String, String> entry : myMap.entrySet()) { if
		 * (entry.getValue().contains(searchString)) {
		 * System.out.println(entry.getValue());
		 * 
		 * -------------IMPORTANT----------------------------------- To insert
		 * it into the DB
		 * -------------IMPORTANT-----------------------------------
		 * 
		 * // myDAO.insertIntoDB(entry.getKey(), entry.getValue()); } }
		 * System.out.println("**********************************************");
		 * System.out.println("End of Search and insertion");
		 * System.out.println("**********************************************");
		 * }
		 */
	}

	/**
	 * @param arr
	 */
	private static void deeperSearch(File[] arr, int index, int level, Map aMap) {
		if (index == arr.length)
			return;
		/*
		 * -------------IMPORTANT----------------------------------- uncomment
		 * this for printing in console only
		 * -------------IMPORTANT-----------------------------------
		 */
		/*
		 * for (int i = 0; i < level; i++) { System.out.print("\t"); }
		 */

		if (arr[index].isDirectory()) {
			/*
			 * -------------IMPORTANT-----------------------------------
			 * uncomment this for printing in console only
			 * -------------IMPORTANT-----------------------------------
			 */
			// System.out.println("[" + arr[index].getName() + "]");

			// Recursive call
			deeperSearch(arr[index].listFiles(), 0, level + 1, aMap);
		} else if (arr[index].isFile()) {

			if (arr[index].getName().contains(searchString)) {
				aMap.put(arr[index].getAbsoluteFile(), arr[index].getName());
				try {
					// Copies file from src to target folder
					Files.copy(arr[index].getAbsoluteFile().toPath(),
							new File(outputDirectory + "/" + arr[index].getName()).toPath(),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
			/*
			 * -------------IMPORTANT-----------------------------------
			 * uncomment this for printing in console only
			 * -------------IMPORTANT-----------------------------------
			 */
			// System.out.println(arr[index].getAbsoluteFile());
		}
		deeperSearch(arr, ++index, level, aMap);
	}

}
