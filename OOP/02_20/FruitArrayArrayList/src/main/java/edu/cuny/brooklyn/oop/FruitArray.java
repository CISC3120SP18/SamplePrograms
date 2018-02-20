package edu.cuny.brooklyn.oop;

public class FruitArray 
{
	private String[] fruits;
	private int size;		// number of fruits stored
	private int capacity;	// maximum number of fruits allowed
	
	public FruitArray(int capacity) {
		fruits = new String[capacity];
		this.capacity = capacity;
		size = 0;
	}
	
	public boolean add(String fruitName) {
		if (size >= capacity) return false;
		
		fruits[size] = fruitName;
		size ++;
		
		return true;
	}
	
	public void delete(String fruitName) {
		// TODO: 1. implement this method.
		/* TODO: 2. you may need to consult Java API documentation for the String class. 
		 *          Write a comment in the code, the method of the String class you
		 *          look up and the URL to the documentation the method 
		 */
	}
	
	public void printAll() {
		for (int i=0; i<size; i++) {
			System.out.println("fruits[" + i + "] = \"" + fruits[i] + "\"");
		}
	}
}
