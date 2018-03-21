package edu.cuny.brooklyn.design;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * TODO 1: 
 *		(1) extends RandomNumberObservable from Observable (java.util.Observable)
 *		(2) add a makeRandomNumber(String) method where it generates a random number and notifies
 *			this object's observers 
 */
public class RandomNumberObservable {
	private final static Logger LOGGER = LoggerFactory.getLogger(RandomNumberObservable.class);
	private String name;
	// private Random rng;
	private int number;
	
	public RandomNumberObservable(String name) {
		this.name = name;
		// rng = new Random();
	}
	
	public String getName() {
		return name;
	}
	
	public void makeRandomNumber(String ext) {
		LOGGER.debug("RandomNumberObservable " + name + " making random number.");		
	}
	
	public int getNumber() {
		return number;
	}
}
