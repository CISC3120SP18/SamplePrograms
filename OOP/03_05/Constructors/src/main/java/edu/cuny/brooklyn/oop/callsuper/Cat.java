package edu.cuny.brooklyn.oop.callsuper;

public class Cat extends Animal {
	// this following would not compile. 
//	public Cat(String name) {
//		this.setName(name);
//	}
	
	public Cat(String name) {
		super(name);
	}
	
	public void pounce(Cat other) {
		System.out.println("This cat called " + getName() + " is pouncing " + other.getName());
	}
}
