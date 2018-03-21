package edu.cuny.brooklyn.design.view;

import edu.cuny.brooklyn.design.grade.TranscriptService;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ShowGPAView {
	protected TranscriptService transcriptService;
	private GridPane pane; // root of the scene graph
	private Scene scene;
	private Label[] gradeValues;
	
	protected ShowGPAView() {
		pane = new GridPane();
		
		pane.setHgap(ViewSettings.GRID_GAP);
		pane.setVgap(ViewSettings.GRID_GAP);
		pane.setPadding(ViewSettings.PADDING);
		gradeValues = new Label[4];
		for (int i=0; i<gradeValues.length; i++) {
			gradeValues[i] = new Label();
		}
		pane.add(new Label("# of Courses"), 2, 2);
		pane.add(gradeValues[0], 3, 2);
		pane.add(new Label("# of Credits"), 2, 3);
		pane.add(gradeValues[1], 3, 3);
		pane.add(new Label("Grade Points Total"), 2, 4);
		pane.add(gradeValues[2], 3, 4);
		pane.add(new Label("GPA"), 2, 5);
		pane.add(gradeValues[3], 3, 5);

		scene = new Scene(pane, ViewSettings.SCENE_WIDTH, ViewSettings.SCENE_HEIGHT);
	}

	public void showOn(Stage stage) {
		validateTranscriptServiceState();
		transcriptService.updateGPA(); // what if we forget to call this? 
		updateView();
		stage.setScene(scene);
		stage.setTitle("GradesTracker: showing GPA");
		stage.show();
	}
	
	private void validateTranscriptServiceState() {
		if (transcriptService == null) {
			throw new IllegalArgumentException("TranscriptService must not be null.");
		}
	}
	
	private void updateView() {
		gradeValues[0].setText(Integer.toString(transcriptService.getNumberOfCourses()));
		gradeValues[1].setText(Double.toString(transcriptService.getTotalCredits()));
		gradeValues[2].setText(Double.toString(transcriptService.getTotalGradePoints()));
		gradeValues[3].setText(Double.toString(transcriptService.getGPA()));
	}
}
