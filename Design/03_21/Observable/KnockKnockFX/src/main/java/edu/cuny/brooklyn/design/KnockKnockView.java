package edu.cuny.brooklyn.design;

import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * TODO 2:
 * 		(1) add a RandomNumberObservable instance variable, and instantiate it in the constructor
 * 		(2) add a getRandomNumberObervable method
 * TODO 4:
 * 		(1) display the random number coming from the observable in both views (javabean & javahouse)
 * TODO 5:
 *		(1) when processInput, be sure to call randomNumberObservable.makeRandomNumber("RN");
 * TODO 6:
 * 		(1) revise the update method so that the random number will be displayed
 */
public class KnockKnockView implements Observer {
	private final static Logger LOGGER = LoggerFactory.getLogger(KnockKnockView.class);
	private String name;
	private Scene scene;
	private Label randomNumber;
	private Label textHistory;
	private TextField textInput;
	private InputObservable inputObservable;
	
	
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
		
		inputObservable = new InputObservable(name);
		textInput.setOnAction(e -> processInput(textInput.getText()));
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
		inputObservable.setInput(input);
		textInput.clear();
	}

	public Observable getInputMsgObservable() {
		return inputObservable;
	}
	
	@Override
	public void update(Observable obs, Object type) {
		String history = textHistory.getText();
		String obversableType = (String) type;
		if (obversableType.equals("JavaHouse")) { // javabean observes javahouse
			String msg = ((InputObservable) obs).getInput();
			LOGGER.debug("KnockKnockView " + name + " received a notification with message = " + msg);
			LOGGER.debug("JavaBean processing input");
			history = history + "\n" + "JavaHouse -> JavaBean: " + msg;
			textHistory.setText(history);
			if (msg.equals("Who is there?")) {
				processInput("Java Bean");
			} else if (msg.equals("Java Bean, who?")) {
				processInput("Java Bean in a JRE!");
			}
		} else if (obversableType.equals("JavaBean")) { // javahouse observes javabean
			String msg = ((InputObservable) obs).getInput();
			LOGGER.debug("KnockKnockView " + name + " received a notification with message = " + msg);
			LOGGER.debug("JavaHouse processing input");
			history = history + "\n" + "JavaBean -> JavaHouse: " + msg;
			textHistory.setText(history);
			if (msg.equals("Knock, knock")) {
				processInput("Who is there?");
			} else if (msg.equals("Java Bean")) {
				processInput("Java Bean, who?");
			}
		} 
	}
}
