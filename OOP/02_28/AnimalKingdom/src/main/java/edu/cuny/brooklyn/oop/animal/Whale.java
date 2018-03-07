package edu.cuny.brooklyn.oop.animal;

public class Whale extends Animal {

	public Whale(String name) {
		super(name);
	}
	
	@Override
	public void makeNoise() {
		System.out.println("Whistling");
	}

}
