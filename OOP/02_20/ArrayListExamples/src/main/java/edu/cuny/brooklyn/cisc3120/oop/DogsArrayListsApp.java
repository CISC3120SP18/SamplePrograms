package edu.cuny.brooklyn.cisc3120.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DogsArrayListsApp {
	public static void main(String[] args) {
		DogsArrayListsApp dogsApp = new DogsArrayListsApp();
		
		// Exercise 1c. observe how an ArrayList is created
		ArrayList<Dog> dogs = new ArrayList<Dog>();
		dogs.add(new Dog(1, "Buddy"));
		dogs.add(new Dog(2, "Millie"));
		
		// Exercise 2. iterate through the ArrayList
		System.out.println("Print out dogs:");
		dogsApp.displayDogs(dogs);
		
		// Exercise 3. Making shallow copy via the ArrayLlist's constructor
		System.out.println("Make a shallow copy of dogs using ArrayList's constructor and print the copy out:");
		ArrayList<Dog> shallowCopyOfDogs = new ArrayList<Dog>(dogs);
		dogsApp.displayDogs(shallowCopyOfDogs);
		
		// Exercise 4. Verify that you indeed only get a shallow copy
		System.out.println("Change Buddy's name to \"Buddy Buddy\" in ArrayList dogs:");
		Dog buddy = dogs.get(0);
		buddy.setName("Buddy Buddy");
		System.out.println("Print out dogs:");
		dogsApp.displayDogs(dogs);
		System.out.println("Print out the shallow copy of dogs:");
		dogsApp.displayDogs(shallowCopyOfDogs);
		/* did you observe the effect of "shallow copy"? */ 
		
		// Exercise 5. You may also obtain a shallow copy via the clone() method although not recommended		@SuppressWarnings("unchecked")
		@SuppressWarnings("unchecked")
		ArrayList<Dog> anotherShallowCopyOfDogs = (ArrayList<Dog>) dogs.clone();
		System.out.println("Print out the another shallow copy of dogs obtained via clone:");
		dogsApp.displayDogs(anotherShallowCopyOfDogs);
		
		// Exercise 6. Make a deep copy of ArrayList
		ArrayList<Dog> deepCopyOfDogs = dogsApp.makeDeepCopy(dogs);
		Dog millie = deepCopyOfDogs.get(1);
		millie.setName("Millie Millie");
		System.out.println("Print out dogs:");
		dogsApp.displayDogs(dogs);
		System.out.println("Print out the deep copy of Dogs:");
		dogsApp.displayDogs(deepCopyOfDogs);
		
		// Exercise 8. Sequential search in ArrayList
		Dog missingDog = dogsApp.searchDog(dogs,  new Dog(3, "Max"));
		if (missingDog == null) {
			System.out.println("Dog Max is not in the pound.");
		} else {
			System.out.println("Found " + missingDog.toString());
		}
		
		missingDog = dogsApp.searchDog(dogs,  new Dog(2, "Millie"));
		if (missingDog == null) {
			System.out.println("Dog Max is not in the pound.");
		} else {
			System.out.println("Found " + missingDog.toString());
		}
		
		// Exercise 9. Search using Collections and sort using ArrayList.sort()
		dogsApp.searchUsingCollections(dogs);
		
		// Exercise 10: Sort using Collections
		dogsApp.sortViaCollections(dogs);
	}


	void displayDogs(ArrayList<Dog> dogs) {
		for(Dog dog: dogs) {
			System.out.println(dog.toString());
		}
	}
	
	// Exercise 6. Make a deep copy of ArrayList
	// Exercise 7b: add elements in ArrayList
	ArrayList<Dog> makeDeepCopy(ArrayList<Dog> dogs) {
		ArrayList<Dog> copyOfDogs = new ArrayList<Dog>();
		for (Dog dog: dogs) {
			copyOfDogs.add(Dog.newInstance(dog));
		}
		return copyOfDogs;
	}
	
	Dog searchDog(ArrayList<Dog> dogs, Dog target) {
		/* what indexOf really does?
		 * returns the lowest index i 
		 *    such that (o==null ? get(i)==null : o.equals(get(i))), 
		 *    or -1 if there is no such index
		 * but which equals method in the Dog class
		 */
		int dogIndex = dogs.indexOf(target);
		if ( dogIndex == -1) {
			return null;
		} else {
			return dogs.get(dogIndex);
		}
	}
	
	// Exercise 9. Search using Collections and sort using ArrayList.sort()
	void searchUsingCollections(ArrayList<Dog> dogs) {
		// add a few dogs to make it more interesting
		dogs.add(new Dog(3, "Max"));
		dogs.add(new Dog(4, "Ollie"));
		// build a comparator used in both sorting and searching
		Comparator<Dog> dogComparator = new Comparator<Dog>() {
			@Override
			public int compare(Dog lhs, Dog rhs) {
				return lhs.getName().compareTo(rhs.getName());
			}
			
		};
		// sort
		dogs.sort(dogComparator);
		System.out.println("Dogs sorted by their names:");
		displayDogs(dogs);
		// search
		int dogIndex = Collections.binarySearch(dogs, new Dog(3, "Max"), dogComparator);
		if (dogIndex >= 0) {
			System.out.println("Found " + dogs.get(dogIndex) + " by searching its name.");
		} else {
			System.out.println("Dog Max is not found.");
		}
	}
	
	// Exercise 10: Sort using Collections
	void sortViaCollections(ArrayList<Dog> dogs) {
		// build a comparator used in both sorting and searching
		// to make it more interesting, to sort in the descending order
		// using dog's name
		Comparator<Dog> dogComparator = new Comparator<Dog>() {
			@Override
			public int compare(Dog lhs, Dog rhs) {
				return - lhs.getName().compareTo(rhs.getName());
			}
			
		};
		Collections.sort(dogs, dogComparator);
		System.out.println("After sort, should be in the descending order by name:");
		displayDogs(dogs);
	}
}
