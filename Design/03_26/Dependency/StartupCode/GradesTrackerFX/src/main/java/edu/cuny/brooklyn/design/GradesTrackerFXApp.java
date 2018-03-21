package edu.cuny.brooklyn.design;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.design.grade.Course;
import edu.cuny.brooklyn.design.grade.GradeNumberService;
import edu.cuny.brooklyn.design.grade.TranscriptService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GradesTrackerFXApp extends Application {
	private final static Logger LOGGER = LoggerFactory.getLogger(GradesTrackerFXApp.class);
	private final static double SCENE_WIDTH = 800.;
	private final static double SCENE_HEIGHT = 600.;
	private final static int NUM_OF_GRIDS = 8;
	private final static double COLUMN_WIDTH = 150.;
	private final static double GRID_GAP = 5.;
	private final static Insets PADDING = new Insets(20., 20., 20., 20.);
	private final static int NUMBER_OF_COURSES = 4;
	private final static int COURSE_COLUMN_START = 1;
	private final static int COURSE_ROW_START = 1;
	private final static int COURSE_FIELD_NUMBER = 3;
	
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
		Scene courseScene = buildEnterCourseScene(NUMBER_OF_COURSES, primaryStage);
		Scene studentScene = buildEnterStudentScene(primaryStage, courseScene);

		primaryStage.setTitle("GradesTracker - Enter student's name");
		primaryStage.setScene(studentScene);
		primaryStage.show();
	}

	@Override
	public void stop() {
		LOGGER.info("Exiting the app...");
	}

	private Scene buildEnterStudentScene(Stage nextStage, Scene nextScene) {
		GridPane pane = new GridPane();
		pane.setHgap(GRID_GAP);
		pane.setVgap(GRID_GAP);
		pane.setPadding(PADDING);
		for (int i = 0; i < NUM_OF_GRIDS; i++) {
			pane.getColumnConstraints().add(new ColumnConstraints(COLUMN_WIDTH)); // column 0 is 100 wide
		}

		pane.add(new Label("Name"), 1, 2);
		TextField nameField = new TextField();
		pane.add(nameField, 2, 2);

		Button confirmButton = new Button("OK");
		confirmButton.setOnAction(e -> enterName(nameField, nextStage, nextScene));
		pane.add(confirmButton, 2, 4);

		Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
		return scene;
	}

	private Scene buildEnterCourseScene(int numOfCoursesToEnter, Stage nextStage) {
		GridPane pane = new GridPane();
		Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
		
		pane.setHgap(GRID_GAP);
		pane.setVgap(GRID_GAP);
		pane.setPadding(PADDING);
		for (int i = 0; i < NUM_OF_GRIDS; i++) {
			pane.getColumnConstraints().add(new ColumnConstraints(COLUMN_WIDTH)); // column 0 is 100 wide
		}
		
		pane.add(new Label("Course Number"), 1, 0);
		pane.add(new Label("# of Credits"), 2, 0);
		pane.add(new Label("Letter Grade"), 3, 0);
		
		for (int i = COURSE_ROW_START; i < COURSE_ROW_START + numOfCoursesToEnter; i ++) {
			for (int j = COURSE_COLUMN_START; j <= COURSE_FIELD_NUMBER; j++) {
				pane.add(new TextField(), j, i);
			}
		}
		
		Button buttonSubmit = new Button("Submit");
		buttonSubmit.setOnAction(e -> submitCourses(pane, nextStage));
		Button buttonMoreCourses = new Button("More Courses...");
		buttonMoreCourses.setOnAction(e -> addMoreCourses(pane, nextStage, scene));
		
		pane.add(buttonSubmit, 2, COURSE_ROW_START + numOfCoursesToEnter + 1);
		pane.add(buttonMoreCourses, 3, COURSE_ROW_START + numOfCoursesToEnter + 1);

		return scene;
	}
	

	private Scene buildShowGPAScene() {
		GridPane pane = new GridPane();
		
		pane.setHgap(GRID_GAP);
		pane.setVgap(GRID_GAP);
		pane.setPadding(PADDING);
		
		transcriptService.updateGPA(); // what if we forget to call this? 
		
		pane.add(new Label("# of Courses"), 2, 2);
		pane.add(new Label(Integer.toString(transcriptService.getNumberOfCourses())), 3, 2);
		pane.add(new Label("# of Credits"), 2, 3);
		pane.add(new Label(Double.toString(transcriptService.getTotalCredits())), 3, 3);
		pane.add(new Label("Grade Points Total"), 2, 4);
		pane.add(new Label(Double.toString(transcriptService.getTotalGradePoints())), 3, 4);
		pane.add(new Label("GPA"), 2, 5);
		pane.add(new Label(Double.toString(transcriptService.getGPA())), 3, 5);

		Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
		return scene;
	}

	private void addCourses(GridPane pane, Stage nextStage) {
		Course[] courses = new Course[NUMBER_OF_COURSES];
		boolean[] validCourses = new boolean[NUMBER_OF_COURSES];
		for (int i=0; i<NUMBER_OF_COURSES; i++) {
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
		
		
		for (int i=0; i<NUMBER_OF_COURSES; i++) {
			if (validCourses[i]) {
				transcriptService.addCourse(courses[i]);
				LOGGER.debug("Added to the transcript the course: " + courses[i].toString()
						+ " the service has courses " + transcriptService.getNumberOfCourses());
			}
		}
		
	}
	
	
	private void addMoreCourses(GridPane pane, Stage nextStage, Scene scene) {
		LOGGER.debug("called addMoreCourses ...");
		addCourses(pane, nextStage);
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
		return row >= COURSE_ROW_START && row <= COURSE_ROW_START + NUMBER_OF_COURSES;
	}
	
	private boolean isCourseColumn(int column) {
		return column >= COURSE_COLUMN_START && column <= COURSE_COLUMN_START + COURSE_FIELD_NUMBER;
	}

	private void submitCourses(GridPane pane, Stage nextStage) {
		addCourses(pane, nextStage);

		/*
		 * Why do we need to build this every time?
		 */
		Scene gpaScene = buildShowGPAScene();
		nextStage.setScene(gpaScene);
		nextStage.setTitle("GradesTracker: showing GPA");
		nextStage.show();
	}
	
	private int getColumnType(int column) {
		return column - COURSE_COLUMN_START;
	}

	private int getCourseIndex(int row) {
		return row - COURSE_ROW_START;
	}
	
	private void enterName(TextField nameField, Stage nextStage, Scene nextScene) {
		String name = nameField.getText();
		if (!name.isEmpty()) {
			transcriptService.setName(name);
			nextStage.setScene(nextScene);
			nextStage.setTitle("GradesTracker - Add courses and grades.");
			nextStage.show();
		}
	}
}
