package edu.cuny.brooklyn.design;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.design.grade.SimpleTranscriptService;
import edu.cuny.brooklyn.design.view.SimpleEnterStudentView;
import javafx.application.Application;
import javafx.stage.Stage;

public class GradesTrackerFXApp extends Application {
	private final static Logger LOGGER = LoggerFactory.getLogger(GradesTrackerFXApp.class);
	
	private SimpleTranscriptService transcriptService;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() {
		LOGGER.info("Launching the app ...");
		transcriptService = new SimpleTranscriptService();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		SimpleEnterStudentView enterStudentView = new SimpleEnterStudentView(primaryStage);
		enterStudentView.setService(transcriptService);
		enterStudentView.showOn(primaryStage);
	}

	@Override
	public void stop() {
		LOGGER.info("Exiting the app...");
	}
}
