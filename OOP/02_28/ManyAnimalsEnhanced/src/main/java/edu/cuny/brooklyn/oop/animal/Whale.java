package edu.cuny.brooklyn.oop.animal;

public class Whale extends Animal implements WhaleMotion {

	protected Whale(String name) {
		super(name);
	}

	@Override
	public void makeNoise() {
		System.out.println("Whistling");
	}

	@Override
	public void swim(Direction direction, double speed, double distance) {
		System.out.print("I am swimming to direction " + direction.toString() + " for " + distance + " feet at " + speed
				+ " miles/hour. ");		
		System.out.println("Fins moving ...");		
	}

}
