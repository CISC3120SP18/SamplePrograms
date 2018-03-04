package edu.cuny.brooklyn.oop.subclass;


public class ConstructorApp {
	public static void main(String[] args) {
		Cat ginger = new Cat(); // default constructor
		
		ginger.pounce(new Cat());
	}
}
