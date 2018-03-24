package edu.cuny.brooklyn.graphics;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainAppView extends BaseView {
	public MainAppView(Stage stage) {
		addButton(stage, "Fade Transition", new FadeTransitionDemoView(), 0, 0);
		addButton(stage, "Path Transition", new PathTransitionDemoView(), 0, 1);
		addButton(stage, "Parallel Transition", new ParallelTransitionDemoView(), 0, 2);
		addButton(stage, "Sequential Transition", new SequentialTransitionDemoView(), 0, 3);
		addButton(stage, "Timeline Animation", new TimelineAnimationDemoView(), 0, 4);
		addButton(stage, "Sequential Timeline Animation", new SequentialTimelineAnimationDemoView(), 0, 5);
	}
	
	private void addButton(Stage stage, String text, BaseView view, int column, int row) {
		Button button = new Button(text);
		button.setOnAction(e ->view.showOn(stage, text));
		pane.add(button,  column,  row);
		button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		GridPane.setFillWidth(button, true);
	}
}
