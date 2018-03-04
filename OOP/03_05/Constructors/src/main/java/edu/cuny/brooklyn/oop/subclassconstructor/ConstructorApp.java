package edu.cuny.brooklyn.oop.subclassconstructor;


public class ConstructorApp {
	public static void main(String[] args) {
		Cat ginger = new Cat("ginger"); // default constructor
		
		ginger.pounce(new Cat("tuxedo"));
	}
}
