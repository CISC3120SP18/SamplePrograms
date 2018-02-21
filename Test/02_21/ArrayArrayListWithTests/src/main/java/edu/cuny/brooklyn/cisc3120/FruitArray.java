package edu.cuny.brooklyn.cisc3120;

//import java.util.Arrays;

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
	

	// ---------------------------------------------
	// added a few methods to support unit tests
	public FruitArray(String[] fruits) {
		this.fruits = fruits;
		this.capacity = fruits.length;
		this.size = fruits.length;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public int getSize() {
		return size;
	}
	
	public String[] getFruitsAsArray() {
		return fruits;
	}
	// ---------------------------------------------
	
	
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
		
//		int pos = 0;
//		while (pos < size && !fruits[pos].equalsIgnoreCase(fruitName)) {
//			pos ++;
//		}
//		
//		if (pos >= size) return;
//		
//		for (int i=pos; i<size-1; i++) {
//			fruits[i] = fruits[i+1];
//		}
//		fruits[size-1] = null;
//		size --;
		
		
		int pos = 0;
		while (pos < size && !fruits[pos].equalsIgnoreCase(fruitName)) {
			pos ++;
		}
		
		if (pos >= size) return;
		System.arraycopy(fruits, pos+1, fruits, pos, size-(pos+1));
		fruits[size-1] = null;
		size --;	
			
		
//		//Find the index of the String
//		int elementPos = 0;
//		for(int i=0; i<size; i++) {
//			if(fruits[i] == fruitName)
//				elementPos = i;
//		}
//		//Create a temporary array
//		String [] fruitsTemp = new String[capacity];
//		//Copy up to the element
//		System.arraycopy(fruits, 0, fruitsTemp, 0, elementPos );
//		//Copy rest, skipping the element
//		System.arraycopy(fruits, elementPos+1, fruitsTemp, elementPos, size-elementPos-1);
//		
//		//Decrement size because an element was removed
//		size--;
//		
//		//"Empty out the array"
//		Arrays.fill(fruits, null);
//		//Copy it back
//		System.arraycopy(fruitsTemp, 0, fruits, 0, size);		
	}
	
	public void printAll() {
		for (int i=0; i<size; i++) {
			System.out.println("fruits[" + i + "] = \"" + fruits[i] + "\"");
		}
	}
}
