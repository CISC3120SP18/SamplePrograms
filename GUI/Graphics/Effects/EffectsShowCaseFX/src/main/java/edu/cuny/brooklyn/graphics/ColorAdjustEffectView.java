package edu.cuny.brooklyn.graphics;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ColorAdjustEffectView extends EffectBaseView {
	public ColorAdjustEffectView() {
		pane.add(createColorAdjustEffectNode( 0.00,  0.00, 0.0, 0.0), 0, 0);
		pane.add(createColorAdjustEffectNode(-0.50,  0.00, 0.0, 0.0), 1, 0);
		pane.add(createColorAdjustEffectNode(+0.50,  0.00, 0.0, 0.0), 2, 0);
		
		pane.add(createColorAdjustEffectNode(0.00, -0.50, 0.0, 0.0), 1, 1);
		pane.add(createColorAdjustEffectNode(0.00,  0.50, 0.0, 0.0), 2, 1);
		
		pane.add(createColorAdjustEffectNode(0.00,  0.00, -0.50, 0.0), 1, 2);
		pane.add(createColorAdjustEffectNode(0.00,  0.00,  0.50, 0.0), 2, 2);
		
		pane.add(createColorAdjustEffectNode(0.00,  0.00,  0.00, -0.50), 1, 3);
		pane.add(createColorAdjustEffectNode(0.00,  0.00,  0.00,  0.50), 2, 3);
	}

	private Node createColorAdjustEffectNode(double contrast, double hue, double brightness, double saturation) {
		Group group = new Group();
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setContrast(contrast);
		colorAdjust.setHue(hue);
		colorAdjust.setBrightness(brightness);
		colorAdjust.setSaturation(saturation);

		Image image = new Image("boat.jpg");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(200);
		imageView.setPreserveRatio(true);
		imageView.setEffect(colorAdjust);
		group.getChildren().add(imageView);
		
		return group;
	}
}
