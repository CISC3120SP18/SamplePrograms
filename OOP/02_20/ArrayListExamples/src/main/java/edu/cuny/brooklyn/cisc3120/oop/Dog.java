package edu.cuny.brooklyn.cisc3120.oop;

import java.util.Objects;

public class Dog {
	private long id;
	private String name;
	
	Dog(long id, String name) {
		this.id = id;
		this.name = name;
	}
	

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "Dog[id=" + id + ",name=" + name + "]";
	}
	
	// The following provides two "equals" and a "hashCode" methods. The second equals method and
	// the hashCodde method are about more advanced topic some of which we will discuss in a later
	// lesson when we discuss interfaces and inheritance. It is because this is the proper way to
	// test equivalence in Java. 
	
	// @Override // uncomment this will yield compilation error. more in the future
	public boolean equals(Dog otherDog) {
		if (otherDog != null && id == otherDog.id && name.equals(otherDog.name)) {
			return true;
		} else {
			return false;
		}
		// or just one statement
		// return otherDog.id != null && id == otherDog.id && name.equals(otherDog.name);
	}
	
	@Override
	public boolean equals(Object other) {
		/* if the "other" variable is null, it is not equal because if you can ever get here
		 * the "this" variable is not null 
		 */
		if (other == null) {
			return false;
		}
		
		/* if the "other" variable and the "this" variable are equal, both reference to 
		 * the same object, so they are equal
		 */
		if (other == this) {
			return true;
		}
		
		/* The "other" object may not be a Dog at all. If it is not. 
		 * The following is try to test whether Java compiler and Java runtime accepts
		 *    Dog dog = other;
		 * i.e., whether other is a compatible type to Dog, which is related to inheritance
		 * Semantically, we try to test whether the object "other" references to is a "Dog" 
		 * object some sort. 
		 * 
		 * The statement can also be written as 
		 * 
		 * if (!Dog.class.isInstance(other))
		 * 
		 * However, it is subtly different from 
		 * 
		 * if (other instanceof Dog)
		 * 
		 * although many would use "instanceof". All these are generally checked at run-time.
		 * The difference is that "instanceof" is being checked against a class whose type is
		 * known at the compilation time while  the type being checked in the case of the other
		 * two are known only at runtime.
		 */
		if (!Dog.class.isAssignableFrom(other.getClass())) {
			return false;
		}
		
		/* the "other" variable references to a Dog of ***some sort*** */
		Dog otherDog = (Dog) other;
		if (id == otherDog.id && name.equals(otherDog.name)) {
			return true;
		}
		
		return false;
	}	
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

}
