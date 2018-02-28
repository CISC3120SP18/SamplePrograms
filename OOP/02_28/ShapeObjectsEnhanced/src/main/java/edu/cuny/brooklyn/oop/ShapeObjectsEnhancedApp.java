package edu.cuny.brooklyn.oop;

import java.util.ArrayList;
import java.util.Random;

import edu.cuny.brooklyn.oop.shape.Circle;
import edu.cuny.brooklyn.oop.shape.Rectangle;
import edu.cuny.brooklyn.oop.shape.Shape;

public class ShapeObjectsEnhancedApp {
	public static void main(String[] args) {
		ShapeObjectsEnhancedApp app = new ShapeObjectsEnhancedApp();
		
		ArrayList<Shape> shapeList = app.makeRandomShapes(10);
		System.out.println("List of randomly generated shapes:");
		for (Shape s : shapeList) {
			System.out.println(String.format("%-20s: %d",  s.getName(), s.getName().length()));
		}
		System.out.println("-------------------------------------------------------");
		System.out.println();
		
		// TODO 6: Create a separate class (top-level class) in the package of
		//         edu.cuny.brooklyn.oop, call the class, ShapeNameComparator.
		//         The comparator is to compare the string value of the names
		//         of two shapes, and is used to sort shapes in alphabet order
		//         based on their names. 
		//          Once done, you should uncomment the following block of code
		//         test your code and to observe the sorted shapes. 
//		shapeList.sort(new ShapeNameComparator());
//		System.out.println("\nList of shapes sorted by name:");
//		for (Shape s : shapeList) {
//			System.out.println(s.getName());
//		}
		
		
		// TODO 7: Create a local class in this scope, call the class, 
		//         ShapeNameLengthComparator. The comparator is to compare the
		//         length of the name of two shapes, and is used to sort shapes
		//         in ***descending order*** based on the length of their names.
		//         Once done, you should uncomment the following block of code
		//         test your code and to observe the sorted shapes.
//		shapeList.sort(new ShapeNameLengthComparator());
//		System.out.println("\nList of shapes sorted by length of name in descending order:");
//		for (Shape s : shapeList) {
//			System.out.println(s.getName() + ": " + s.getName().length());
//		}	
		
		
		// TODO 8: Create an anonymous Comparator class in the following 
		//         few lines of code. The comparator is to compare the areas
		//         of two shapes, and is used to sort shapes in 
		//         ***ascending order*** based on the areas of the shapes. 
		// 
		//         You should uncomment and complete the following block of code
		//         test your code and to observe the sorted shapes.
//		shapeList.sort(/* do something */);
//		System.out.println("\nList of shapes sorted by length of area:");
//		for (Shape s : shapeList) {
//			System.out.println(s.getName() + ": " + s.area());
//		}
		
		
		// TODO 9: Create a Comparator using a Lambda expression. The 
		//         comparator is to compare the positions after each is moved to 
		//         a random position within a square of the unit length. The
		//         comparator is to sort shapes in based on the distance of 
		//         their position to the center of the unit square, which is
		//         at (0.5, 0.5). 
		//         You should uncomment and complete the following block of code
		//         test your code and to observe the sorted shapes.
		//          Add the getters or some additional methods when you see fit. 
		// Random rng = new Random();
//		for (Shape s : shapeList) {
//			s.moveTo(rng.nextDouble(), rng.nextDouble());
//			System.out.println(s.getName() + ": moved to (" + s.getXPos() + ", " + s.getYPos() + ")");
//		}
//		shapeList.sort(/* do something */);
//		System.out.println("\nList of shapes by distance to the center of the unit square:");
//		for (Shape s : shapeList) {
//			System.out.println(s.getName() + "'s distance to the center: " /* complete this part */);
//		}
}

	public ArrayList<Shape> makeRandomShapes(int numOfShapes) {
		ArrayList<Shape> shapeList = new ArrayList<Shape>();

		Random rng = new Random();
		// shapes of random types
		for (int i = 0; i < numOfShapes; i++) {
			int rn = rng.nextInt(2);
			if (rn == 0) {
				double radius = rng.nextDouble() * 100;
				String name = "Circle_" + i + "_" + makeRandomDigitString(rng, 5);
				Circle circle = new Circle(name, radius);
				shapeList.add(circle);
			} else {
				double width = rng.nextDouble() * 100;
				double length = rng.nextDouble() * 100;
				String name = "Rectangle_" + i + "_" + makeRandomDigitString(rng, 5);
				Rectangle rectangle = new Rectangle(name, width, length);
				shapeList.add(rectangle);
			}
		}

		return shapeList;
	}
	
	private String makeRandomDigitString(Random rng, int maxLength) {
		int length = rng.nextInt(maxLength) + 1;
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<length; i++) {
			sb.append(rng.nextInt(10));
		}
		return sb.toString();
	}

}
