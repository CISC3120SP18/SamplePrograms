package edu.cuny.brooklyn.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloWorldFx extends Application {

	@Override
	public void start(Stage primaryStage) {
		Button btn = new Button();
		btn.setText("Say 'Hello World' in the Console");
		
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Hello World!");
			}
		});
		
		// EventHandler is a functional interface, so we can,
//		btn.setOnAction(event -> System.out.println("Hello World!"));
		
		// To allow the Button responses to the ENTER key, we need to 
		// give the "focus" to the button. You can uncomment this, 
		// and use the ENTER key in addition to the mouse click to
		// trigger ActionEvent
//		btn.setDefaultButton(true);

		StackPane root = new StackPane();
		root.getChildren().add(btn);

		Scene scene = new Scene(root, 300, 250);

		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}