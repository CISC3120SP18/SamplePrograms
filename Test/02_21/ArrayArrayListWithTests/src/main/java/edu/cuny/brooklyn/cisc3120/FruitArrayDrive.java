package edu.cuny.brooklyn.cisc3120;

public class FruitArrayDrive {
	public static void main(String[] args) {
		/* TODO: you are required to implement the 'delete' method in the
		 *       FruitArray class. The output of this program upon your
		 *       completion of your program must be as follows,
		 *       
				Showing fruits in the array:
				fruits[0] = "Apple"
				fruits[1] = "Banana"
				fruits[2] = "Mango"
				Showing fruits after deleting 'banana' 
				fruits[0] = "Apple"
				fruits[1] = "Mango"
				--------------------------------
				Showing fruits in the array:
				fruits[0] = "Apple"
				fruits[1] = "Banana"
				fruits[2] = "Mango"
				Showing fruits after deleting 'Mango' 
				fruits[0] = "Apple"
				fruits[1] = "Banana"
				--------------------------------
		 */
		
		// Test Case 1
		testDeleteMiddle();
		// Tests Case 2
		testDeleteLast();
	}
	
	private static void testDeleteMiddle() {
		FruitArray fruits = new FruitArray(3);
		
		fruits.add("Apple");
		fruits.add("Banana");
		fruits.add("Mango");
		
		System.out.println("Showing fruits in the array:");
		fruits.printAll();
		/* note that lower-case 'b' in 'banana' is intentional. */ 
		System.out.println("Showing fruits after deleting 'banana' ");
		fruits.delete("banana");
		fruits.printAll();
		System.out.println("--------------------------------");			
	}
	
	private static void testDeleteLast() {
		FruitArray fruits = new FruitArray(3);
		
		fruits.add("Apple");
		fruits.add("Banana");
		fruits.add("Mango");
		
		System.out.println("Showing fruits in the array:");
		fruits.printAll();
		System.out.println("Showing fruits after deleting 'Mango' ");
		fruits.delete("Mango");
		fruits.printAll();
		System.out.println("--------------------------------");			
	}	

}
