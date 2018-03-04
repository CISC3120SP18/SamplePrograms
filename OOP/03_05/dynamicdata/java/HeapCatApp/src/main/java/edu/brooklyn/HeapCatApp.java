package edu.brooklyn;

public class HeapCatApp {
	public static void main(String[] args) {
		Cat ginger = new Cat("ginger"), tuxedo = new Cat("tuxedo");
		ginger.tap(tuxedo);
	}
}
