package edu.cuny.brooklyn.cisc3120.oop;

import java.util.ArrayList;
import java.util.Random;

public class NumbersArrayListsApp {
	public static void main(String[] args) {
		NumbersArrayListsApp numbersApp = new NumbersArrayListsApp();
		System.out.println("Calling playWithArrayListWithRawType():");
		numbersApp.playWithArrayListWithRawType();
		
		System.out.println("Calling playWithArrayList():");
		numbersApp.playWithArrayList();
	}
	
	
	/* In the following methods, we suppressed one warning, 
	 * 	- ArrayList is a raw type. References to generic type ArrayList<E> should be parameterized
	 * which is a bad practice, and should be avoided. See playWithArrayList for an example where
	 * we use "generics" to avoid this warning
	 */
	@SuppressWarnings("rawtypes")
	void playWithArrayListWithRawType() {
		ArrayList numbers = makeRandomArrayListWithRawType();
		printArrayListWithRawTypeUsingIndex(numbers);
	}
	
	
	/* In the following methods, we suppressed two warnings, 
	 * 	- ArrayList is a raw type. References to generic type ArrayList<E> should be parameterized
	 *  - Type safety: The method add(Object) belongs to the raw type ArrayList. 
	 *    References to generic type ArrayList<E> should be parameterized
	 * which is a bad practice, and should be avoided. See makeRandomArrayList for an example where
	 * we use "generics" to avoid this warning
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	ArrayList makeRandomArrayListWithRawType() {
		Random rng = new Random();
		ArrayList numbers = new ArrayList();		
		
		int length = rng.nextInt(10);
		for (int i=0; i<length; i++) {
			numbers.add(rng.nextInt(100));
		}
		return numbers;
	}
	
	
	/* In the following methods, we suppressed one warning, 
	 * 	- ArrayList is a raw type. References to generic type ArrayList<E> should be parameterized
	 * which is a bad practice, and should be avoided. See printArrayList for an example where
	 * we use "generics" to avoid this warning
	 */	
	@SuppressWarnings("rawtypes")
	void printArrayListWithRawTypeUsingIndex(ArrayList numbers) {
		System.out.println("ArrayList numbers has " + numbers.size() + " elements");
		for (int i=0; i<numbers.size(); i ++ ) {
			System.out.println("numbers[" + i + "] = " + numbers.get(i));
		}
	}
	

	void playWithArrayList() {
		ArrayList<Integer> numbers = makeRandomArrayList();
		printArrayListUsingIndex(numbers);
		
		System.out.println("Remove elements whose indices are odd");
		removeElementsWithOddIndex(numbers);
		printArrayListUsingIndex(numbers);
		
		System.out.println("Make a shallow copy via ArrayList's constructor:");
		ArrayList<Integer> numbersCopy =  new ArrayList<Integer>(numbers);
		printArrayListUsingIndex(numbersCopy);
		
		System.out.println("Print the numbers using the ehanced for:");
		printArrayListUsingEnhancedFor(numbers);
	}
	
	
	ArrayList<Integer> makeRandomArrayList() {
		Random rng = new Random();
		ArrayList<Integer> numbers = new ArrayList<Integer>();		
		
		int length = rng.nextInt(20);
		for (int i=0; i<length; i++) {
			numbers.add(rng.nextInt(100));
		}
		return numbers;
	}
	
	
	void printArrayListUsingIndex(ArrayList<Integer> numbers) {
		System.out.println("ArrayList numbers has " + numbers.size() + " elements");
		for (int i=0; i<numbers.size(); i ++ ) {
			System.out.println("numbers[" + i + "] = " + numbers.get(i));
		}
	}
	
	void printArrayListUsingEnhancedFor(ArrayList<Integer> numbers) {
		for(Integer elem: numbers) {
			System.out.println("Elem: " + elem);
		}
	}
	
	void removeElementsWithOddIndex(ArrayList<Integer> numbers) {
		int numRemoved = 0;
		for (int i=numbers.size()-1; i>=0; i--) {
			if (i % 2 != 0) {
				System.out.println("Removing element " + i + " whose value is " + numbers.get(i));
				numbers.remove(i);
				numRemoved ++;
			} 
		}
		
		/*
		 * Question: Can we replace the for loop as follows, i.e., 
		 * remove from the head (or the front) first? Why?
		 */
//		for (int i=0; i<numbers.size(); i++) {
//			if (i % 2 != 0) {
//				System.out.println("Removing element " + i + " whose value is " + numbers.get(i));
//				numbers.remove(i);
//				numRemoved ++;
//			} 
//		}
		 
		
		System.out.println("In total, " + numRemoved + " elements are removed.");
	}
}
