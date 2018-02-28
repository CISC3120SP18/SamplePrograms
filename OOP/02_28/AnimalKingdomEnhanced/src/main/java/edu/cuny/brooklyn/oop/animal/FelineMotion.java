package edu.cuny.brooklyn.oop.animal;

public interface FelineMotion {
	public void walk(Direction direction, double speed, double distance);
	public void pounce(Animal prey);
}
