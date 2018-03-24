package edu.cuny.brooklyn.graphics;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public abstract class TransformBaseView {
	private Scene scene;
	protected GridPane pane;
	
	public TransformBaseView() {
		pane = new GridPane();
		scene = new Scene(pane);
		
		pane.setPadding(new Insets(50.0, 50.0, 150.0, 50.0));
		pane.setHgap(20.);
		pane.setVgap(20.);
	}
	
	public void showOn(Stage stage, String title) {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
}
