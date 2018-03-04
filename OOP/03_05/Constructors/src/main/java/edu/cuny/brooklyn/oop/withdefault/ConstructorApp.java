package edu.cuny.brooklyn.oop.withdefault;


public class ConstructorApp {
	public static void main(String[] args) {
		Cat ginger = new Cat("ginger");
		ginger.pounce(new Cat()); // a default constructor is provided by the programmer
	}
}
