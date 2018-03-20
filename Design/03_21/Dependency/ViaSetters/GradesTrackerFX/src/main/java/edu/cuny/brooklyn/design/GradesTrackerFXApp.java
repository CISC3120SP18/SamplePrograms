package edu.cuny.brooklyn.design;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.design.grade.TranscriptService;
import edu.cuny.brooklyn.design.view.EnterStudentView;
import javafx.application.Application;
import javafx.stage.Stage;

/*
 * TODO:
 *		The application has three different views. However, when the application switches from
 *		one view to another, it does not display the student's name even after the user has
 *		entered it. 
 *		
 *		Revise the application so that the Student's name is displayed on the top of the view,
 *		for which, you need to,
 *		(1) add a Label to the top of the AddCourseView and the ShowGPAView
 *		(2) add necessary dependencies via the Setter method (if needed)
 *		(3) display student's name on the top of the two views. 
 */

public class GradesTrackerFXApp extends Application {
	private final static Logger LOGGER = LoggerFactory.getLogger(GradesTrackerFXApp.class);
	
	private TranscriptService transcriptService;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() {
		LOGGER.info("Launching the app ...");
		transcriptService = new TranscriptService();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		EnterStudentView enterStudentView = new EnterStudentView(primaryStage);
		enterStudentView.setTranscriptService(transcriptService);
		enterStudentView.showOn(primaryStage);
	}

	@Override
	public void stop() {
		LOGGER.info("Exiting the app...");
	}
}
