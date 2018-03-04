package edu.cuny.brooklyn.oop.nodefault;


public class ConstructorApp {
	public static void main(String[] args) {
		/* uncommenting this causes leads to a compilation error because
		 * when we provide a constructor, Java compiler ceases to provide
		 * the default constructor
		 */
		// Cat ginger = new Cat(); 
		
		Cat ginger = new Cat("ginger");
		ginger.pounce(new Cat("tuxedo"));
	}
}
