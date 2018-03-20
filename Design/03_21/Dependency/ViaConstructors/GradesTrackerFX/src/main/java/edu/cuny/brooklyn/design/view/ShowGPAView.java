package edu.cuny.brooklyn.design.view;

import edu.cuny.brooklyn.design.grade.TranscriptService;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ShowGPAView {
	private GridPane pane; // root of the scene graph
	private Scene scene; 
	
	public ShowGPAView(TranscriptService transcriptService) {
		pane = new GridPane();
		
		pane.setHgap(ViewSettings.GRID_GAP);
		pane.setVgap(ViewSettings.GRID_GAP);
		pane.setPadding(ViewSettings.PADDING);
		
		transcriptService.updateGPA(); // what if we forget to call this? 
		
		pane.add(new Label("# of Courses"), 2, 2);
		pane.add(new Label(Integer.toString(transcriptService.getNumberOfCourses())), 3, 2);
		pane.add(new Label("# of Credits"), 2, 3);
		pane.add(new Label(Double.toString(transcriptService.getTotalCredits())), 3, 3);
		pane.add(new Label("Grade Points Total"), 2, 4);
		pane.add(new Label(Double.toString(transcriptService.getTotalGradePoints())), 3, 4);
		pane.add(new Label("GPA"), 2, 5);
		pane.add(new Label(Double.toString(transcriptService.getGPA())), 3, 5);

		scene = new Scene(pane, ViewSettings.SCENE_WIDTH, ViewSettings.SCENE_HEIGHT);
	}

	public Scene getScene() {
		return scene;
	}
}
