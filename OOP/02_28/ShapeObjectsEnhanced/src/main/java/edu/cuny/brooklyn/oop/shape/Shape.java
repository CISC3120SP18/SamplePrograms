package edu.cuny.brooklyn.oop.shape;

import java.util.Objects;

public abstract class Shape {
	protected String name;
	
	public Shape(String name) {
		this.name = name;
	}
	
	// TODO 1: provide a declaration of abstract method 
	//         area() that returns the area of the shape
	//         and the area is a double value
	
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
		// System.out.println("In Shape.equals(Object): " + this.name + "<-->" + ((Shape)other).name);
		/**
		 * Since there is a type hierarchy, what we mean by that two objects are
		 * equal is getting more complex. The following implementation basically
		 * states that two shapes are equal when the following conditions are met,
		 * (1) both objects (this and the other) must be a shape object of some sort, 
		 *     i.e., a Shape object or an object of any descendant of the Shape class.
		 * (2) both shapes must have identical name, i.e., the values of the names
		 *     must be identical without ignoring case.
		 */
		if (other instanceof Shape) {
			// return this.name == null ? other.name == null : this.name.equals(other.name);
			// or equivalently, more easily to read:
			if (this.name == null) {
				return ((Shape) other).name == null;
			} else {
				return this.name.equals(((Shape) other).name);
			}
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
		return Objects.hash(name);
	}

	public String getName() {
		return name;
	}
}
