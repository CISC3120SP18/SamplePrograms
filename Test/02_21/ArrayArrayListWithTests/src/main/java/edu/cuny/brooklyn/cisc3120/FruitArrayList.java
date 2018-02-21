package edu.cuny.brooklyn.cisc3120;

import java.util.ArrayList;

/*
 * Wrapper for ArrayList 
 */
public class FruitArrayList {
	ArrayList<String> fruits;
	
	public FruitArrayList() {
		fruits = new ArrayList<String>();
	}
	
	
	public void add(String fruitName) {
		fruits.add(fruitName);
	}
	
	public void delete(String fruitName) {
		// TODO: 1. implement this method.
		/* TODO: 2. you may need to consult Java API documentation for the ArrayList class. 
		 *          Write a comment in the code, the method of the ArrayList class you
		 *          look up and the URL to the documentation the method 
		 */
	}
	
	public void printAll() {
		for (int i=0; i<fruits.size(); i++) {
			System.out.println("fruits[" + i + "] = \"" + fruits.get(i) + "\"");
		}
	}
}
