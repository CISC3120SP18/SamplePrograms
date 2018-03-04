package edu.cuny.brooklyn.oop.defaultconstructor;


public class ConstructorApp {
	public static void main(String[] args) {
		Cat ginger = new Cat(); // default constructor
		
		ginger.pounce(new Cat());
	}
}
