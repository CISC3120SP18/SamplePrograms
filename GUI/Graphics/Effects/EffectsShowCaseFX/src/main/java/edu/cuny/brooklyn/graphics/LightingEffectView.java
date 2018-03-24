package edu.cuny.brooklyn.graphics;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LightingEffectView extends EffectBaseView {
	
	public LightingEffectView() {
		super();
		
		pane.add(createLightingNode(-135, 0.0), 0, 0);
		pane.add(createLightingNode(-135, 5.0), 1, 0);
		pane.add(createLightingNode(-135, 10.0), 2, 0);
		
		pane.add(createLightingNode(135, 0.0), 0, 1);
		pane.add(createLightingNode(135, 5.0), 1, 1);
		pane.add(createLightingNode(135, 10.0), 2, 1);
	}

	private Node createLightingNode(double azimuth, double surfaceScale) {
		Group group = new Group();
		Light.Distant light = new Light.Distant();
		light.setAzimuth(azimuth);

		Lighting lighting = new Lighting();
		lighting.setLight(light);
		lighting.setSurfaceScale(surfaceScale);

		Text text = new Text();
		text.setText("Lighting");
		text.setFill(Color.STEELBLUE);
		text.setFont(Font.font(null, FontWeight.BOLD, 60));
		text.setX(10.0);
		text.setY(10.0);
		text.setTextOrigin(VPos.TOP);

		text.setEffect(lighting);
		
		
		Circle circle = new Circle(100);
		circle.setCenterX(130);
		circle.setCenterY(200);
		circle.setFill(Color.STEELBLUE);
		circle.setEffect(lighting);
		
		group.getChildren().addAll(text, circle);
		return group;
	}
}
