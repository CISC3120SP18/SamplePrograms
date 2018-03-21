package edu.cuny.brooklyn.design;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.stage.Stage;

public class KnockKnockFXApp extends Application {
	private final static Logger LOGGER = LoggerFactory.getLogger(KnockKnockFXApp.class);
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void init() {
		LOGGER.info("Launching the app ...");		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		KnockKnockView javaBeanView = new KnockKnockView("JavaBean");
		KnockKnockView javaHouseView = new KnockKnockView("JavaHouse");

		javaBeanView.getInputMessage().inputProperty()
				.addListener(o -> javaHouseView.update(javaBeanView.getInputMessage()));
		javaHouseView.getInputMessage().inputProperty()
				.addListener(o -> javaBeanView.update(javaHouseView.getInputMessage()));

		Stage stage = new Stage();
		javaHouseView.showOn(stage);
		javaBeanView.showOn(primaryStage);
	}

	@Override
	public void stop() {
		LOGGER.info("Exiting the app...");
	}
}
