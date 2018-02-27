package edu.cuny.brooklyn.oop.shape;

import java.util.Objects;

// TODO 4: The Circle class  must also implements the Positioner interface 
//         that is to position the Shape's center to a coordinate system. 
//         To simulate position a Shape, we may introduce two double instance
//         variables xPos and yPos, and set the values of the variables to
//         the argument passed from Positioner's moveTo method.
public class Circle extends Shape {
	private double radius;
	
	public Circle(String name, double radius) {
		super(name);
		this.radius = radius;
	}
	
	/**
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!! IMPORTATN !!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * In Java, generally, when overriding the equals method, we must also
	 * override the hashCode method. The equals method is defined in the
	 * java.lang.Object class, the parent class of all Java classes. The
	 * Java API documentation states,
	 * "
	 *   Note that it is generally necessary to override the hashCode method
	 *   whenever this method is overridden, so as to maintain the general 
	 *   contract for the hashCode method, which states that equal objects 
	 *   must have equal hash codes.
	 * "
	 * See for details at, 
	 * https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#equals-java.lang.Object-
	 * and
	 * https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#hashCode--
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Circle) {
			return super.equals(other) && Double.compare(this.radius, ((Circle)other).radius) == 0;
		} else {
			return false;
		}
	}
	
	/**
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!! IMPORTATN !!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * The hashCode should be overridden based on the semantics of the
	 * equals method. 
	 * 
	 * Since Java 1.7, we can conveniently using the java.util.Objects class
	 * to generate a hash code. 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.radius);
	}
	
	// TODO 2: implement the area() method that computers the area 
	//         of the circle. The method provides an implementation
	//         to the Shape class's abstract area() method. 
}
