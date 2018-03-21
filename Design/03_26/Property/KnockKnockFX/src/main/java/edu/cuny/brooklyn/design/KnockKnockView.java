package edu.cuny.brooklyn.design;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KnockKnockView {
	private final static Logger LOGGER = LoggerFactory.getLogger(KnockKnockView.class);
	private String name;
	private Scene scene;
	private Label randomNumber;
	private Label textHistory;
	private TextField textInput;
	private InputMessage inputMessage;
	
	
	public KnockKnockView(String name) {
		this.name = name;
		
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(20., 20., 20., 20.));
		randomNumber = new Label("Random Number:");
		textHistory = new Label();
		textHistory.setAlignment(Pos.TOP_LEFT);
		textHistory.setPrefSize(800., 600.);
		textInput = new TextField();
		vbox.getChildren().addAll(randomNumber, textHistory, textInput);
		scene = new Scene(vbox);
		
		inputMessage = new InputMessage(name);
		textInput.setOnAction(e -> processInput(textInput.getText()));
	}
	
	
	public InputMessage getInputMessage() {
		return inputMessage;
	}
	
	public String getName() {
		return name;
	}

	public void showOn(Stage stage) {
		stage.setScene(scene);
		stage.setTitle("This is " + name);
		stage.show();
	}
	
	private void processInput(String input) {
		if (input.isEmpty()) return;
		inputMessage.setInput(input);
		textInput.clear();
		inputMessage.setInput("");
	}

	public void update(InputMessage inputMessage) {
		if (inputMessage.getInput().isEmpty())
			return;
		String history = textHistory.getText();
		String msg = inputMessage.getInput();
		String name = inputMessage.getName();

		if (name.equals("JavaHouse")) { // javabean observes javahouse
			LOGGER.debug("KnockKnockView " + name + " received a notification with message = " + msg);
			LOGGER.debug("JavaBean processing input");
			history = history + "\n" + "JavaHouse -> JavaBean: " + msg;
			textHistory.setText(history);
			if (msg.equals("Who is there?")) {
				processInput("Java Bean");
			} else if (msg.equals("Java Bean, who?")) {
				processInput("Java Bean in a JRE!");
			}
		} else if (name.equals("JavaBean")) { // javahouse observes javabean
			LOGGER.debug("KnockKnockView " + name + " received a notification with message = " + msg);
			LOGGER.debug("JavaHouse processing input");
			history = history + "\n" + "JavaBean -> JavaHouse: " + msg;
			textHistory.setText(history);
			if (msg.equals("Knock, knock")) {
				LOGGER.debug(getName() + " processInput for " + name);
				processInput("Who is there?");
			} else if (msg.equals("Java Bean")) {
				processInput("Java Bean, who?");
			}
		}
	}
}
