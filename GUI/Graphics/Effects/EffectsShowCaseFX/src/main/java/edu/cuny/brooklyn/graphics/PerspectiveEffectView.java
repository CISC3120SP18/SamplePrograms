package edu.cuny.brooklyn.graphics;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PerspectiveEffectView extends EffectBaseView {
	
	public PerspectiveEffectView() {
		pane.add(createPerspectiveEffectNoe(), 0, 0);
	}

	private Node createPerspectiveEffectNoe() {
		 PerspectiveTransform perspectiveTrasform = new PerspectiveTransform();
		 perspectiveTrasform.setUlx(10.0);
		 perspectiveTrasform.setUly(10.0);
		 perspectiveTrasform.setUrx(310.0);
		 perspectiveTrasform.setUry(40.0);
		 perspectiveTrasform.setLrx(310.0);
		 perspectiveTrasform.setLry(60.0);
		 perspectiveTrasform.setLlx(10.0);
		 perspectiveTrasform.setLly(90.0);

		 Group g = new Group();
		 g.setEffect(perspectiveTrasform);
		 g.setCache(true);

		 Rectangle rect = new Rectangle();
		 rect.setX(10.0);
		 rect.setY(10.0);
		 rect.setWidth(280.0);
		 rect.setHeight(80.0);
		 rect.setFill(Color.web("0x3b596d"));

		 Text text = new Text();
		 text.setX(20.0);
		 text.setY(65.0);
		 text.setText("Perspective");
		 text.setFill(Color.ALICEBLUE);
		 text.setFont(Font.font(null, FontWeight.BOLD, 36));

		 g.getChildren().addAll(rect, text);
		 
		 return g;
	}
}
