package edu.cuny.brooklyn.cisc3120.oop;

import java.util.Random;

public class IterationExamples {
	public static void main(String[] args) {
		IterationExamples examples = new IterationExamples();
		
		// Exercise: loops
		// for loop
		int sum = examples.sumUsingFor(0,  10,  1);
		System.out.println("sumUsingFor(0,  10,  1) ==> " + sum);
		
		// while loop
		sum = examples.sumUsingWhile(0,  10,  1);
		System.out.println("sumUsingWhile(0,  10,  1) ==> " + sum);	
		
		// do-while loop
		sum = examples.sumUsingDoWhile(0,  10,  1);
		System.out.println("sumUsingDoWhile(0,  10,  1) ==> " + sum);	
		
		// for loop
		sum = examples.sumUsingFor(10,  0,  1);
		System.out.println("sumUsingFor(10,  0,  1) ==> " + sum);
		
		// while loop
		sum = examples.sumUsingWhile(10,  0,  1);
		System.out.println("sumUsingWhile(10,  0,  1) ==> " + sum);	
		
		// do-while loop
		sum = examples.sumUsingDoWhile(10,  0,  1);
		System.out.println("sumUsingDoWhile(10,  0,  1) ==> " + sum);	
		
		// Exercise: break and continue statement
		System.out.print("examples.printMultipleOf7s() ==> ");
		examples.printMultipleOf7s();
		System.out.print("examples.print10sTill47() ==> ");
		examples.print10sTill47();
		
		// Exercise: ehanced for
		int[] numbers = examples.makeRandomIntegerArray(10);
		System.out.print("printArrayUsingEnhancedFor() ==> ");
		examples.printArrayUsingEnhancedFor(numbers);
		
		Dog[] dogs = examples.makeDogs(10);
		System.out.print("printDogsUsingEnhancedFor() ==> ");
		examples.printDogsUsingEnhancedFor(dogs);
		
	}
	
	int sumUsingFor(int begin, int end, int increment) {
		int sum = 0;
		for (int i=begin; i<=end; i+=increment) {
			sum += i;
		}
		return sum;
	}
	
	int sumUsingWhile(int begin, int end, int increment) {
		int sum = 0;
		int i = begin;
		while (i<=end) {
			sum += i;
			i ++;
		}
		return sum;
	}
	
	int sumUsingDoWhile(int begin, int end, int increment) {
		int sum = 0;
		if (begin > end) { // why do we need this? 
			return 0;
		}
		int i = begin;
		do {
			sum += i;
			i ++;
		} while(i<=end);
		return sum;
	}
	
	int[] makeRandomIntegerArray(int n) {
		Random rng = new Random();
		int[] numbers = new int[10];
		for (int i=0; i<n; i++) {
			numbers[i] = rng.nextInt(100);
		}	
		return numbers;
	}
	
	Dog[] makeDogs(int n) {
		Dog[] dogs = new Dog[n];
		for (int i=0; i<n; i++) {
			int id = i;
			String name = "Buddy" + i;
			dogs[i] = new Dog(id, name);
		}
		return dogs;
	}
	
	void printArrayUsingEnhancedFor(int[] numbers) {
		// in C++11 or newer, this is called the "ranged based for".
		for(int n: numbers) {
			System.out.print(n + " ");
		}
		System.out.println();
	}
	
	void printDogsUsingEnhancedFor(Dog[] dogs) {
		// in C++11 or newer, this is called the "ranged based for".
		for(Dog theDog: dogs) {
			System.out.print(theDog.toString() + " ");
		}
		System.out.println();
	}
	
	void printMultipleOf7s() {
		for (int i = 0; i < 100; i++) {
			if (i == 74) {
				// break out of the loop
				break; 
			}
			if (i % 7 != 0) {
				// skip the rest, start next iteration from the top
				continue; 
			}
			System.out.print(i + " ");
		}
		System.out.println();	
	}
	
	void print10sTill47() {
		int i = 0;
		while (true) {
			i++;		
			int j = i * 27;
			if (j == 1269) {
				// break out of the loop
				break; 
			}
			if (i % 10 != 0) {
				// skip the rest, start next iteration from the top
				continue; 
			}
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
