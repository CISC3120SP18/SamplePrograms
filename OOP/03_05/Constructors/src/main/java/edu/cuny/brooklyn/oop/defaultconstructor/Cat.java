package edu.cuny.brooklyn.oop.defaultconstructor;

public class Cat {
	private String name;

	public void pounce(Cat other) {
		System.out.println("This cat called " + name + " is pouncing " + other.getName());
	}
	
	public String getName() {
		return name;
	}
}
