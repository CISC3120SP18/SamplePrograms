package edu.cuny.brooklyn.design;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InputMessage {
	// private final static Logger LOGGER = LoggerFactory.getLogger(InputMessage.class);
	private String name;
	private StringProperty input;
	
	public InputMessage(String name) {
		this.name = name;
		this.input = new SimpleStringProperty();
	}
	
	/* ---------------------------------------------------------------------------- */
	/* generally, you ought to provide these methods to conform with the convention */
	public String getInput() {
		return input.get();
	}
	
	public void setInput(String msg) {
		input.set(msg);
	}
	
	public StringProperty inputProperty() {
		return input;
	}
	/* ---------------------------------------------------------------------------- */
	
	public String getName() {
		return name;
	}
}
