package edu.cuny.brooklyn.graphics;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GlowEffectView extends EffectBaseView {
	public GlowEffectView() {
		pane.add(createGlowEffectNode(0.0), 0, 0);
		pane.add(createGlowEffectNode(0.3), 1, 0);
		pane.add(createGlowEffectNode(0.7), 2, 0);	
		pane.add(createGlowEffectNode(1.0), 3, 0);		
	}

	private Node createGlowEffectNode(double level) {
		Group group = new Group();
		Image image = new Image("boat.jpg");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(200);
		imageView.setPreserveRatio(true);

		imageView.setEffect(new Glow(level));
		group.getChildren().add(imageView);
		
		return group;
	}
}
