package edu.cuny.brooklyn.cisc3120.oop;

import java.util.Arrays;
import java.util.Random;

public class NumberArraysApp {
	public static void main(String[] args) {
		NumberArraysApp app = new NumberArraysApp();
		
		// Exercise 1: create and iterate a 1-d array
		app.createAndPrint1dArray();
		
		// Exercise 2: create and iterate a 3-d array
		app.createAndPrint3dArray();
		
		// Exercise 3: create and iterate a 3-d array of arrays
		app.createAndPrint3dArrayOfArrays();
		
		// Exercise 4: copy array of primitive type
		app.copyPrimitiveArrayUsingIteration();
		
		// Exercise 5: copy array of primitive type
		app.copyPrimitiveArrayUsingCopyOf();
		
		// Exercise 6: copy array of primitive type
		app.copyPrimtiveArrayUsingArrayCopy();
		
		// Exercise 7: copy array of primitive type
		app.copyPrimitiveArrayUsingClone();
		
		// Exercise 8: mark not to be used (deletion)
		app.useOverProvisionedArray();
		
		// Exercise 9: sequential search via iteration
		app.doSequentialSearch();
		
		// Exercise 10: do a binary search
		app.doBinarySearch();
	}

	void createAndPrint1dArray() {
		Random rng = new Random();
		
		int[] numbers = new int[rng.nextInt(20)];
		for (int i=0; i<numbers.length; i++) {
			numbers[i] = rng.nextInt(100);
		}
		
		System.out.println("Display 1-d numbers array whose length is " + numbers.length + ":");
		for (int i=0; i<numbers.length; i++) {
			System.out.println("numbers[" + i + "] = " + numbers[i]);
		}
		
		System.out.println("Display the numbers array again using the enhanced for loop:");
		for (int n: numbers) {
			System.out.println(n);
		}
	}
	
	void createAndPrint3dArray() {
		Random rng = new Random();
		
		double[][][] numbers = new double[rng.nextInt(5)][rng.nextInt(5)][rng.nextInt(5)];
		for (int i=0; i<numbers.length; i++) {
			for (int j=0; j<numbers[i].length; j++) {
				for (int k=0; k<numbers[i][j].length; k++) {
					numbers[i][j][k] = rng.nextDouble();
				}
			}
		}
		
		System.out.println("Display 3-d numbers array whose dimension is " 
				+ numbers.length + " x " 
				+ (numbers.length==0?0:numbers[0].length) + " x " 
				+ (numbers.length==0 || numbers[0].length==0?0:numbers[0][0].length) + ":");
		for (int i=0; i<numbers.length; i++) {
			for (int j=0; j<numbers[i].length; j++) {
				for (int k=0; k<numbers[i][j].length; k++) {
					System.out.println("numbers[" + i + "][" + j + "][" + k + "] = " + numbers[i][j][k]);
				}
			}
		}

		System.out.println("Display the numbers array again using the enhanced for loop:");
		for (double[][] d2: numbers) {
			for (double[] d1: d2) {
				for (double d: d1) {
					System.out.println(d);
				}
			}
		}
	}	
	


	void createAndPrint3dArrayOfArrays() {
		Random rng = new Random();
		
		long[][][] numbers = new long[rng.nextInt(5)][][];
		for (int i=0; i<numbers.length; i++) {
			numbers[i] = new long[rng.nextInt(5)][];
			for (int j=0; j<numbers[i].length; j++) {
				numbers[i][j] = new long[rng.nextInt(5)];
				for (int k=0; k<numbers[i][j].length; k++) {
					numbers[i][j][k] = rng.nextLong();
				}
			}
		}
		
		System.out.println("Display 3-d numbers array of arrays:");
		for (int i=0; i<numbers.length; i++) {
			for (int j=0; j<numbers[i].length; j++) {
				for (int k=0; k<numbers[i][j].length; k++) {
					System.out.println("numbers[" + i + "][" + j + "][" + k + "] = " + numbers[i][j][k]);
				}
			}
		}

		System.out.println("Display the numbers array of arrays again using the enhanced for loop:");
		for (long[][] d2: numbers) {
			for (long[] d1: d2) {
				for (long d: d1) {
					System.out.println(d);
				}
			}
		}
	}
	

	void copyPrimitiveArrayUsingIteration() {
		Random rng = new Random();
		
		int[][] numbers = new int[rng.nextInt(5)][rng.nextInt(5)];
		for (int i=0; i<numbers.length; i++) {
			for (int j=0; j<numbers[i].length; j++) {
				numbers[i][j] = rng.nextInt(100);
			}
		}
		
		System.out.println("Make a copy of the 2-d numbers array.");
		int[][] copyOfNumbers = copy2dArray(numbers);
		
		System.out.println("Display the original the 2-d numbers array:");
		for (int i=0; i<numbers.length; i++) {
			for (int j=0; j<numbers[i].length; j++) {
				System.out.println("numbers[" + i + "][" + j + "] = " +  numbers[i][j]);
			}
		}
		
		System.out.println("Display a copy of the 2-d numbers array:");
		for (int i=0; i<copyOfNumbers.length; i++) {
			for (int j=0; j<copyOfNumbers[i].length; j++) {
				System.out.println("copyOfNumbers[" + i + "][" + j + "] = " + copyOfNumbers[i][j]);
			}
		}
	}
	
	int[][] copy2dArray(int[][] numbers) {
		int[][] copyOfNumbers = new int[numbers.length][];
		for (int i=0; i<numbers.length; i++) {
			copyOfNumbers[i] = new int[numbers[i].length];
			for (int j=0; j<numbers[i].length; j++) {
				copyOfNumbers[i][j] = numbers[i][j];
			}
		}
		return copyOfNumbers;
	}


	void copyPrimitiveArrayUsingCopyOf() {
		Random rng = new Random();
		
		int[][] numbers = new int[rng.nextInt(5)][rng.nextInt(5)];
		for (int i=0; i<numbers.length; i++) {
			for (int j=0; j<numbers[i].length; j++) {
				numbers[i][j] = rng.nextInt(100);
			}
		}
		
		System.out.println("Make a copy of the 2-d numbers array using Arrays.copyOf().");
		int[][] copyOfNumbers =	Arrays.copyOf(numbers, numbers.length);
		
		System.out.println("Display the original the 2-d numbers array:");
		for (int i=0; i<numbers.length; i++) {
			for (int j=0; j<numbers[i].length; j++) {
				System.out.println("numbers[" + i + "][" + j + "] = " +  numbers[i][j]);
			}
		}
		
		System.out.println("Display a copy of the 2-d numbers array:");
		for (int i=0; i<copyOfNumbers.length; i++) {
			for (int j=0; j<copyOfNumbers[i].length; j++) {
				System.out.println("copyOfNumbers[" + i + "][" + j + "] = " + copyOfNumbers[i][j]);
			}
		}
	}


	void copyPrimtiveArrayUsingArrayCopy() {
		Random rng = new Random();
		
		int[][] numbers = new int[rng.nextInt(5)][rng.nextInt(5)];
		for (int i=0; i<numbers.length; i++) {
			for (int j=0; j<numbers[i].length; j++) {
				numbers[i][j] = rng.nextInt(100);
			}
		}
		
		System.out.println("Make a copy of the 2-d numbers array using Systems.arrayCopy().");
		int[][] copyOfNumbers = new int[numbers.length][];
		System.arraycopy(numbers,  0,  copyOfNumbers,  0, numbers.length);
		
		System.out.println("Display the original the 2-d numbers array:");
		for (int i=0; i<numbers.length; i++) {
			for (int j=0; j<numbers[i].length; j++) {
				System.out.println("numbers[" + i + "][" + j + "] = " +  numbers[i][j]);
			}
		}
		
		System.out.println("Display a copy of the 2-d numbers array:");
		for (int i=0; i<copyOfNumbers.length; i++) {
			for (int j=0; j<copyOfNumbers[i].length; j++) {
				System.out.println("copyOfNumbers[" + i + "][" + j + "] = " + copyOfNumbers[i][j]);
			}
		}
	}
	


	void copyPrimitiveArrayUsingClone() {
		Random rng = new Random();
		
		int[][] numbers = new int[rng.nextInt(5)][rng.nextInt(5)];
		for (int i=0; i<numbers.length; i++) {
			for (int j=0; j<numbers[i].length; j++) {
				numbers[i][j] = rng.nextInt(100);
			}
		}
		
		System.out.println("Make a copy of the 2-d numbers array using clone().");
		int[][] copyOfNumbers = (int[][])numbers.clone();
		
		System.out.println("Display the original the 2-d numbers array:");
		for (int i=0; i<numbers.length; i++) {
			for (int j=0; j<numbers[i].length; j++) {
				System.out.println("numbers[" + i + "][" + j + "] = " +  numbers[i][j]);
			}
		}
		
		System.out.println("Display a copy of the 2-d numbers array:");
		for (int i=0; i<copyOfNumbers.length; i++) {
			for (int j=0; j<copyOfNumbers[i].length; j++) {
				System.out.println("copyOfNumbers[" + i + "][" + j + "] = " + copyOfNumbers[i][j]);
			}
		}
	}


	void useOverProvisionedArray() {
		int[] numbers = new int[10];
		int elementsUsed = 0;
		
		Random rng = new Random();
		for (int i=0; i<5; i++) {
			numbers[i] = rng.nextInt(100);
		}
		elementsUsed = 5;
		
		System.out.println("Display used elements in an over-provisioned array:");
		for (int i=0; i<elementsUsed; i++) {
			System.out.println("numbers[" + i + "] = " + numbers[i]);
		}
		
	}
	
	void doSequentialSearch() {
		Random rng = new Random();
		
		System.out.println("The numbers array to search on:");
		int[] numbers = new int[rng.nextInt(50)];
		for (int i=0; i<numbers.length; i++) {
			numbers[i] = rng.nextInt(10);
			System.out.println("numbers[" + i + "] = " + numbers[i]);
		}
		
		int pos = searchSequentially(numbers, 6);
		if (pos >= 0) {
			System.out.println("Found 6 at position " + pos);
		} else {
			System.out.println("The numbers array has no 6.");
		}
	}
	
	int searchSequentially(int[] numbers, int target) {
		int index = -1;
		for (int i=0; i<numbers.length; i++) {
			if (numbers[i] == target) {
				index = i;
				break;
			}
		}
		return index;
	}

	void doBinarySearch() {
		Random rng = new Random();
		
		System.out.println("The numbers array to search on:");
		int[] numbers = new int[rng.nextInt(50)];
		for (int i=0; i<numbers.length; i++) {
			numbers[i] = rng.nextInt(10);
			System.out.println("numbers[" + i + "] = " + numbers[i]);
		}
		
		Arrays.sort(numbers);
		System.out.println("The numbers array has been sorted:");
		for (int i=0; i<numbers.length; i++) {
			System.out.println("numbers[" + i + "] = " + numbers[i]);
		}
		
		
		int pos = Arrays.binarySearch(numbers, 6);
		if (pos >= 0) {
			System.out.println("Found 6 at position " + pos);
		} else {
			System.out.println("The numbers array has no 6.");
		}
	} 
	
}
