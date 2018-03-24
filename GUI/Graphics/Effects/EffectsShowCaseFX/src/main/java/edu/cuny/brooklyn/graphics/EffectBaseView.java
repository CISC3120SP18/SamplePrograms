package edu.cuny.brooklyn.graphics;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public abstract class EffectBaseView {
	protected Scene scene;
	protected GridPane pane;
	
	EffectBaseView() {
		pane = new GridPane();
		scene = new Scene(new ScrollPane(pane));
		
		pane.setPadding(new Insets(50., 50., 150., 50.));
		pane.setHgap(50.);
		pane.setVgap(50.);
	}
	
	public void showOn(Stage stage, String title) {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
}
