package edu.cuny.brooklyn.oop.nodefault;

public class Cat {
	private String name;

	public Cat(String name) {
		this.name = name;
	}
	
	public void pounce(Cat other) {
		System.out.println("This cat called " + name + " is pouncing " + other.getName());
	}
	
	public String getName() {
		return name;
	}
}
