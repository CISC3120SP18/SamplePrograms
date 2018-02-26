package edu.cuny.brooklyn.oop.shape;

public class Shape {
	protected String name;
	
	public Shape(String name) {
		this.name = name;
	}
	
	public double area() {
		System.out.println("This method is not supposed to be called.");
		return 0;
	}

	public String getName() {
		return name;
	}
}
