package edu.cuny.brooklyn.oop.animal;

public class Cat extends Feline {

	public Cat(String name) {
		super(name);
	}

	@Override
	public void makeNoise() {
		System.out.println("Meowing");
	}

}
