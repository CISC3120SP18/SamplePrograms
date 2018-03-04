package edu.cuny.brooklyn.oop.callsuper2;


public class ConstructorApp {
	public static void main(String[] args) {
		Cat ginger = new Cat("ginger"); // default constructor
		
		ginger.pounce(new Cat("tuxedo"));
		
		Panther brave = new Panther("brave");
		System.out.println(brave.getName() + " is making friend with " + ginger.getName());
	}
}
