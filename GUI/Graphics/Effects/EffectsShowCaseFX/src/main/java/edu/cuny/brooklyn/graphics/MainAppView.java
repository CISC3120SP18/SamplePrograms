package edu.cuny.brooklyn.graphics;


import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainAppView extends EffectBaseView {
	public MainAppView(Stage stage) {
		addButton(stage, "Blend Effect", new BlendEffectView(), 0, 0);
		addButton(stage, "Bloom Effect", new BloomEffectView(), 0, 1);
		addButton(stage, "Blur Effect", new BlurEffectView(), 0, 2);
		addButton(stage, "Drop Shadow Effect", new DropShadowEffectView(), 0, 3);
		addButton(stage, "Inner Shadow Effect", new InnerShadowEffectView(), 0, 4);
		addButton(stage, "Shadow Effect", new ShadowEffectView(), 0, 5);
		addButton(stage, "Reflection Effect", new ReflectionEffectView(), 0, 6);

		addButton(stage, "Lighting Effect", new LightingEffectView(), 1, 0);
		addButton(stage, "Perspective Effect", new PerspectiveEffectView(), 1, 1);
		addButton(stage, "Chain of Effects", new ChainEffectView(), 1, 2);
		addButton(stage, "Color Adjust Effect", new ColorAdjustEffectView(), 1, 3);
		addButton(stage, "Displacement Map Effect", new DisplacementMapEffectView(), 1, 4);
		addButton(stage, "Glow Effect", new GlowEffectView(), 1, 5);
		addButton(stage, "Sepia Tone Effect", new SepiaToneEffectView(), 1, 6);
	}

	public void showOn(Stage stage, String title) {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	
	private void addButton(Stage stage, String text, EffectBaseView view, int column, int row) {
		Button button = new Button(text);
		button.setOnAction(e ->view.showOn(stage, text));
		pane.add(button,  column,  row);
		button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		GridPane.setFillWidth(button, true);
	}
}
