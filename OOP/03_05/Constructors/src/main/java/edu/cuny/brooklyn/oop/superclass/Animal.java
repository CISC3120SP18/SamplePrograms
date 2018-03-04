package edu.cuny.brooklyn.oop.superclass;

public abstract class Animal {
	private String name;

	public Animal() {
		System.out.println("Called Animal's Animal().");
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
