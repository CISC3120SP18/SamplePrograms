package edu.cuny.brooklyn.graphics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.stage.Stage;


public class AnimationShowCaseFXApp extends Application {
	private final static Logger LOGGER = LoggerFactory.getLogger(AnimationShowCaseFXApp.class);
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void init() {
		LOGGER.info("Launching the app ...");		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new MainAppView(new Stage()).showOn(primaryStage, "Animation Demo");
	}
	
	@Override
	public void stop() {
		LOGGER.info("Exiting the app...");
	}
}
