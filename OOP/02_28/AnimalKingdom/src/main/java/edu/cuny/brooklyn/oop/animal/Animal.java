package edu.cuny.brooklyn.oop.animal;

public abstract class Animal {
	private String name;
	
	Animal(String name) {
		this.name = name;
	}
	
	public abstract void makeNoise();
	
	public void sleep() {
		System.out.println("Sleep");
	}
	
	public String getName() {
		return name;
	}
}
