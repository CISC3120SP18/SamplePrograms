package edu.cuny.brooklyn.oop.callsuperdefault;

public abstract class Animal {
	private String name;

	public Animal() {
		System.out.println("Calling Animal's Animal() method.");
		this.name = "No Name";
	}
	
	public Animal(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
