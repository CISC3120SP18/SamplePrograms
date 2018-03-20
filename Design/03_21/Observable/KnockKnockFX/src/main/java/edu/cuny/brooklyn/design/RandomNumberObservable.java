package edu.cuny.brooklyn.design;

import java.util.Random;

public class RandomNumberObservable {
	private String name;
	private Random rng;
	
	public RandomNumberObservable(String name) {
		this.name = name;
		rng = new Random();
	}
	
	public String getName() {
		return name;
	}
	
	public int getRandomNumber() {
		return rng.nextInt();
	}
}
