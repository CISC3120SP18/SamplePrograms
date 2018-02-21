package edu.cuny.brooklyn.oop.animal;

public class Dove extends Animal implements BirdMotion{

	public Dove(String name) {
		super(name);
	}

	@Override
	public void makeNoise() {
		System.out.println("Cooing");
	}

	@Override
	public void fly(Direction direction, double speed, double distance) {
		System.out.print("I am flying to direction " + direction.toString() + " for " + distance + " feet at " + speed
				+ " miles/hour. ");
		System.out.println("Wings flapping...");
	}
}
