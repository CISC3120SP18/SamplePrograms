package edu.cuny.brooklyn.design;

import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputObservable extends Observable {
	private final static Logger LOGGER = LoggerFactory.getLogger(InputObservable.class);
	private String name;
	private String input;
	
	public InputObservable(String name) {
		this.name = name;
	}

	public void setInput(String input) {
		LOGGER.debug("InputObservable " + name + " is setting input.");
		this.input = input;
		this.setChanged();
		this.notifyObservers(name);
	}
	
	public String getInput() {
		return input;
	}
}
