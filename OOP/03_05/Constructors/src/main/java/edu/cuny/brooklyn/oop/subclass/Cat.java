package edu.cuny.brooklyn.oop.subclass;

public class Cat extends Animal {
	public void pounce(Cat other) {
		System.out.println("This cat called " + getName() + " is pouncing " + other.getName());
	}
}
