package edu.cuny.brooklyn.graphics;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.FloatMap;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DisplacementMapEffectView extends EffectBaseView {
	public DisplacementMapEffectView() {
		pane.add(createDisplacementMapNode(), 0, 0);
	}

	private Node createDisplacementMapNode() {
		Group group = new Group();
		 int width = 220;
		 int height = 100;

		 FloatMap floatMap = new FloatMap();
		 floatMap.setWidth(width);
		 floatMap.setHeight(height);

		 for (int i = 0; i < width; i++) {
		     double v = (Math.sin(i / 20.0 * Math.PI) - 0.5) / 40.0;
		     for (int j = 0; j < height; j++) {
		         floatMap.setSamples(i, j, 0.0f, (float) v);
		     }
		 }

		 DisplacementMap displacementMap = new DisplacementMap();
		 displacementMap.setMapData(floatMap);

		 Text text = new Text();
		 text.setX(40.0);
		 text.setY(80.0);
		 text.setText("Wavy Text");
		 text.setFill(Color.web("0x3b596d"));
		 text.setFont(Font.font(null, FontWeight.BOLD, 50));
		 text.setEffect(displacementMap);
		 
		group.getChildren().add(text);
		return group;
	}
}
