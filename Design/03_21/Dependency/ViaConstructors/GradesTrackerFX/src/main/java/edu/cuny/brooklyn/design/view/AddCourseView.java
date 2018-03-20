package edu.cuny.brooklyn.design.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.design.grade.Course;
import edu.cuny.brooklyn.design.grade.GradeNumberService;
import edu.cuny.brooklyn.design.grade.TranscriptService;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddCourseView {
	private final static Logger LOGGER = LoggerFactory.getLogger(AddCourseView.class);
	
	private TranscriptService transcriptService;
	private GridPane pane; // root of the scene graph
	private Scene scene;

	public AddCourseView(Stage stage) {
		pane = new GridPane();
		scene = new Scene(pane, ViewSettings.SCENE_WIDTH, ViewSettings.SCENE_HEIGHT);
		
		pane.setHgap(ViewSettings.GRID_GAP);
		pane.setVgap(ViewSettings.GRID_GAP);
		pane.setPadding(ViewSettings.PADDING);
		for (int i = 0; i < ViewSettings.NUM_OF_GRIDS; i++) {
			pane.getColumnConstraints().add(new ColumnConstraints(ViewSettings.COLUMN_WIDTH)); // column 0 is 100 wide
		}
		
		pane.add(new Label("Course Number"), 1, 0);
		pane.add(new Label("# of Credits"), 2, 0);
		pane.add(new Label("Letter Grade"), 3, 0);
		
		for (int i = ViewSettings.COURSE_ROW_START; i < ViewSettings.COURSE_ROW_START + ViewSettings.NUMBER_OF_COURSES; i ++) {
			for (int j = ViewSettings.COURSE_COLUMN_START; j <= ViewSettings.COURSE_FIELD_NUMBER; j++) {
				pane.add(new TextField(), j, i);
			}
		}
		
		Button buttonSubmit = new Button("Submit");
		buttonSubmit.setOnAction(e -> submitCourses(ViewSettings.NUMBER_OF_COURSES, pane, stage));
		Button buttonMoreCourses = new Button("More Courses...");
		buttonMoreCourses.setOnAction(e -> addMoreCourses(pane, stage, scene));
		
		pane.add(buttonSubmit, 2, ViewSettings.COURSE_ROW_START + ViewSettings.NUMBER_OF_COURSES + 1);
		pane.add(buttonMoreCourses, 3, ViewSettings.COURSE_ROW_START + ViewSettings.NUMBER_OF_COURSES + 1);
	}
	

	public Scene getScene() {
		return scene;
	}
	
	
	private void submitCourses(int numOfCoursesToEnter, GridPane pane, Stage nextStage) {
		Course[] courses = new Course[numOfCoursesToEnter];
		boolean[] validCourses = new boolean[numOfCoursesToEnter];
		for (int i=0; i<numOfCoursesToEnter; i++) {
			validCourses[i] = true;
		}
		
		for (Node child : pane.getChildren()) {
			Integer column = GridPane.getColumnIndex(child);
			Integer row = GridPane.getRowIndex(child);
			if (column != null && row != null && isCourseRow(row) && isCourseColumn(column)) {
				int idx = getCourseIndex(row);
				if (courses[idx] == null) {
					courses[idx] = new Course();
				}
				String fieldValue = ((TextField) child).getText();

				if (validCourses[idx] && !fieldValue.isEmpty()) {
					int columnType = getColumnType(column);
					if ( columnType == 0) {
							courses[idx].setCourseNo(fieldValue);
					} else if (columnType == 1) {
						courses[idx].setCredits(Integer.parseInt(fieldValue));
					} else if (columnType == 2) {
						courses[idx].setLetterGrade(GradeNumberService.letterGradeValueOf(fieldValue));
					}
				} else {
					validCourses[idx] = false;
				}
			}
		}
		
		for (int i=0; i<numOfCoursesToEnter; i++) {
			if (validCourses[i]) {
				transcriptService.addCourse(courses[i]);
				LOGGER.debug("Added to the transcript the course: " + courses[i].toString()
						+ " the service has courses " + transcriptService.getNumberOfCourses());
			}
		}
		
		/*
		 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 *  Why do we need to build this every time? How can we not to?
		 */
		ShowGPAView showGPAView = new ShowGPAView(transcriptService);
		nextStage.setScene(showGPAView.getScene());
		nextStage.setTitle("GradesTracker: showing GPA");
		nextStage.show();
	}

	private void addMoreCourses(GridPane pane, Stage nextStage, Scene scene) {
		LOGGER.debug("called addMoreCourses ...");
		for (Node child : pane.getChildren()) {
			Integer column = GridPane.getColumnIndex(child);
			Integer row = GridPane.getRowIndex(child);
			if (column != null && row != null && isCourseRow(row) && isCourseColumn(column)) {
				((TextField) child).setText("");
			}
		}
		nextStage.setScene(scene);
		nextStage.setTitle("GradesTracker - Add more courses and grades.");
		nextStage.show();
	}
	

	
	private boolean isCourseRow(int row) {
		return row >= ViewSettings.COURSE_ROW_START && row <= ViewSettings.COURSE_ROW_START + ViewSettings.NUMBER_OF_COURSES;
	}
	
	private boolean isCourseColumn(int column) {
		return column >= ViewSettings.COURSE_COLUMN_START && column <= ViewSettings.COURSE_COLUMN_START + ViewSettings.COURSE_FIELD_NUMBER;
	}


	private int getColumnType(int column) {
		return column - ViewSettings.COURSE_COLUMN_START;
	}

	private int getCourseIndex(int row) {
		return row - ViewSettings.COURSE_ROW_START;
	}


	
}
