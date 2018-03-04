package edu.cuny.brooklyn.oop.superclass;

public class Cat extends Animal {
	public Cat(String name) {
		this.setName(name);
	}
	
	public void pounce(Cat other) {
		System.out.println("This cat called " + getName() + " is pouncing " + other.getName());
	}
}
