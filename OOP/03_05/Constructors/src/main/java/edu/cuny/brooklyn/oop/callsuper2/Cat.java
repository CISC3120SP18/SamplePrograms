package edu.cuny.brooklyn.oop.callsuper2;

public class Cat extends Feline {
	public Cat(String name) {
		super(name);
	}
	
	public void pounce(Cat other) {
		System.out.println("This cat called " + getName() + " is pouncing " + other.getName());
	}

	@Override
	public void makeNoise() {
		System.out.println("Mewo...");
	}
}
