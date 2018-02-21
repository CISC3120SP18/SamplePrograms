package edu.cuny.brooklyn.oop.animal;

public class Dove extends Animal {

	public Dove(String name) {
		super(name);
	}

	@Override
	public void makeNoise() {
		System.out.println("Cooing");
	}
}
