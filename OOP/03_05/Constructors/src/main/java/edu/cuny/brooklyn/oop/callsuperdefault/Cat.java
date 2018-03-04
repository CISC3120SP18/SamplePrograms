package edu.cuny.brooklyn.oop.callsuperdefault;

public class Cat extends Animal {
	// this following would not compile. 
	public Cat(String name) {
		System.out.println("Calling Cat's Cat(String name).");
		this.setName(name);
		System.out.println("After calling Cat/Animal's setName(String name).");
	}
	
	public void pounce(Cat other) {
		System.out.println("This cat called " + getName() + " is pouncing " + other.getName());
	}
}
