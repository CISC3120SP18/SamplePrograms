package edu.cuny.brooklyn.cisc3120.oop;

import java.util.ArrayList;

public class DogsArrayListsApp {
	public static void main(String[] args) {
		DogsArrayListsApp dogsApp = new DogsArrayListsApp();
		
		ArrayList<Dog> dogs = new ArrayList<Dog>();
		dogs.add(new Dog(1, "Buddy"));
		dogs.add(new Dog(2, "Millie"));
		
		System.out.println("Print out dogs:");
		dogsApp.displayDogs(dogs);
		
		System.out.println("Make a shallow copy of dogs using ArrayList's constructor and print the copy out:");
		ArrayList<Dog> shallowCopyOfDogs = new ArrayList<Dog>(dogs);
		dogsApp.displayDogs(shallowCopyOfDogs);
		
		System.out.println("Change Buddy's name to \"Buddy Buddy\" in ArrayList dogs:");
		Dog buddy = dogs.get(0);
		buddy.setName("Buddy Buddy");
		System.out.println("Print out dogs:");
		dogsApp.displayDogs(dogs);
		System.out.println("Print out the shallow copy of dogs:");
		dogsApp.displayDogs(shallowCopyOfDogs);
		/* did you observe the effect of "shallow copy"? */ 
	}
	
	void displayDogs(ArrayList<Dog> dogs) {
		for(Dog dog: dogs) {
			System.out.println(dog.toString());
		}
	}
}
