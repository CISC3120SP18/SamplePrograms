package edu.cuny.brooklyn.design.view;

import edu.cuny.brooklyn.design.grade.TranscriptService;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EnterStudentView {
	protected TranscriptService transcriptService;
	private GridPane pane;	// root of the scene graph
	private Scene scene;
	private ShowGPAView showGPAView;
	
	protected EnterStudentView(Stage nextStage) {
		pane = new GridPane();
		pane.setHgap(ViewSettings.GRID_GAP);
		pane.setVgap(ViewSettings.GRID_GAP);
		pane.setPadding(ViewSettings.PADDING);
		for (int i = 0; i < ViewSettings.NUM_OF_GRIDS; i++) {
			pane.getColumnConstraints().add(new ColumnConstraints(ViewSettings.COLUMN_WIDTH)); // column 0 is 100 wide
		}

		pane.add(new Label("Name"), 1, 2);
		TextField nameField = new TextField();
		pane.add(nameField, 2, 2);

		Button confirmButton = new Button("OK");
		confirmButton.setOnAction(e -> enterName(nameField, nextStage));
		pane.add(confirmButton, 2, 4);
		
		scene = new Scene(pane, ViewSettings.SCENE_WIDTH, ViewSettings.SCENE_HEIGHT);
	}
	
	
	public void setShowGPAView(ShowGPAView view) {
		if (view == null) {
			throw new IllegalArgumentException("ShowGPAView must not be null.");
		}
		showGPAView = view;
	}
	

	public void showOn(Stage stage) {
		stage.setTitle("GradesTracker - Enter student's name");
		stage.setScene(scene);
		stage.show();
	}
	
	private void enterName(TextField nameField, Stage stage) {
		String name = nameField.getText();
		if (!name.isEmpty()) {
			validateServiceState();
			transcriptService.setName(name);
			AddCourseView addCourseView = new AddCourseView(stage);
			addCourseView.setShowGPAView(showGPAView);
			addCourseView.showOn(stage);
		}
	}
	
	
	private void validateServiceState() {
		if (transcriptService == null) {
			throw new IllegalArgumentException("TranscriptService must not be null.");
		}
	}
}
