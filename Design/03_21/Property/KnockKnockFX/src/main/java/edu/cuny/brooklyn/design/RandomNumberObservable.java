package edu.cuny.brooklyn.design;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class RandomNumberObservable {
	private final static Logger LOGGER = LoggerFactory.getLogger(RandomNumberObservable.class);
	private String name;
	// private Random rng;
	private IntegerProperty number;
	
	public RandomNumberObservable(String name) {
		this.name = name;
		this.number = new SimpleIntegerProperty();
		// rng = new Random();
	}
	
	public String getName() {
		return name;
	}
	
	/* ---------------------------------------------------------------------------- */
	/* generally, you ought to provide these methods to conform with the convention */
	public int getNumber() {
		return number.get();
	}
	
	public void setNumber(int value) {
		this.number.set(value);
	}
	
	public IntegerProperty numberProperty() {
		return number;
	}
	/* ---------------------------------------------------------------------------- */
	
	public void makeRandomNumber(String ext) {
		LOGGER.debug("RandomNumberObservable " + name + " making random number.");		
	}

}
