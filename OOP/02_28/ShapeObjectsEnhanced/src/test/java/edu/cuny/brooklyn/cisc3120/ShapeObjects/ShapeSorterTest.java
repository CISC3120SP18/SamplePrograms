package edu.cuny.brooklyn.cisc3120.ShapeObjects;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.cuny.brooklyn.oop.shape.Circle;
import edu.cuny.brooklyn.oop.shape.Rectangle;
import edu.cuny.brooklyn.oop.shape.Shape;

public class ShapeSorterTest {
	
	@Test
	public void testShapeSorterByName() {
		ArrayList<Shape> shapeList = new ArrayList<Shape>();
		shapeList.add(new Circle("C123456789", 10.));
		shapeList.add(new Circle("C12345678", 8.));
		shapeList.add(new Circle("C1234567890", 11.));
		shapeList.add(new Rectangle("C12", 10., 80.));
		
		ArrayList<Shape> expectedShapeList = new ArrayList<Shape>();
		expectedShapeList.add(new Rectangle("C12", 10., 80.));
		expectedShapeList.add(new Circle("C12345678", 8.));
		expectedShapeList.add(new Circle("C123456789", 10.));
		expectedShapeList.add(new Circle("C1234567890", 11.));
		
		// TODO 10: Complete a test case to use one of your
		//          Comparators
		shapeList.sort((lhs, rhs) -> {return lhs.getName().length() - rhs.getName().length();});
		assertEquals(expectedShapeList, shapeList);
	}
}
   