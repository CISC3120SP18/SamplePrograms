package edu.cuny.brooklyn.design;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.stage.Stage;

public class ExpressionEvaluatorFXApp extends Application {
	private final static Logger LOGGER = LoggerFactory.getLogger(ExpressionEvaluatorFXApp.class);
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void init() {
		LOGGER.info("Launching the app ...");		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ExpressionEvaluatorView view = new ExpressionEvaluatorView();
		view.showOn(primaryStage);
	}
	
	@Override
	public void stop() {
		LOGGER.info("Exiting the app...");
	}
}
