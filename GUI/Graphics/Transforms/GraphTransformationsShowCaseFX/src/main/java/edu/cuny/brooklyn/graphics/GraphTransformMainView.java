package edu.cuny.brooklyn.graphics;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GraphTransformMainView extends TransformBaseView {

	public GraphTransformMainView(Stage stage) {
		addButton(stage, "Translate Demo", new TranslateDemoView(), 0, 0);
		addButton(stage, "Rotate Demo", new RotateDemoView(), 0, 1);
		addButton(stage, "Scale Demo", new ScaleDemoView(), 1, 0);
		addButton(stage, "Shear Demo", new ShearDemoView(), 1, 1);
	}
	
	private void addButton(Stage stage, String text, TransformBaseView view, int column, int row) {
		Button button = new Button(text);
		button.setOnAction(e ->view.showOn(stage, text));
		pane.add(button,  column,  row);
		button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		GridPane.setFillWidth(button, true);
	}
}
