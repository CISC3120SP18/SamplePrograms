package edu.cuny.brooklyn.oop.shape;

public class Rectangle extends Shape {
	private double width;
	private double length;
	
	public Rectangle(String name, double width, double length) {
		super(name);
		this.width = width;
		this.length = length;
	}
	
	
	// TODO: create an area method that computers the area 
	//       of the rectangle. The method in effect overrides
	//       parent class's area method
}
