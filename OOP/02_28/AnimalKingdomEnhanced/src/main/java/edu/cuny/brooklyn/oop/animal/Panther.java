package edu.cuny.brooklyn.oop.animal;

public class Panther extends Feline {
	
	public Panther(String name) {
		super(name);
	}

	@Override
	public void makeNoise() {
		System.out.println("Roaring");
	}

}
