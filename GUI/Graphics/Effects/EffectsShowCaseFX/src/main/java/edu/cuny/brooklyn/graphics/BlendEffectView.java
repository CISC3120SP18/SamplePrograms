package edu.cuny.brooklyn.graphics;

import javafx.beans.binding.Bindings;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class BlendEffectView extends EffectBaseView {

	public BlendEffectView() {
		/*
		 * Blend is an effect that combines two inputs together using one of the
		 * predefined blending modes.
		 * 
		 * In the case of a node blending (node.setBlendMode()), the two inputs are:
		 *		The node being rendered (a top input)
		 *		Everything underneath the node (a bottom input)
		 * 
		 * The determination of the bottom input is based on the following rules:
		 *		All lower Z-order siblings in the same group are included.
		 *		If the group has a defined blend mode, then the process stops, and the bottom
		 *			input is defined.
		 *		If the group has the default blend mode, then everything underneath the group
		 *			is included, recursively using this same rule.
		 *		If the process recursively gets back to the root node, then the background
		 *			paint of the scene is included.
		 */
		
		pane.add(createBlendModeNodeForTwo(BlendMode.SRC_ATOP, BlendMode.SRC_OVER), 0, 0);
		pane.add(createBlendModeNodeForTwo(BlendMode.MULTIPLY, BlendMode.SRC_OVER), 1, 0);
		pane.add(createBlendModeNodeForTwo(BlendMode.SRC_OVER, BlendMode.SRC_OVER), 2, 0);
		
		/*
		 * If the background paint of the scene, which is usually an opaque color, is
		 * included in the bottom input, then the SRC_ATOP mode renders on a completely
		 * opaque bottom source and has no effect. In this case, the SRC_ATOP mode is
		 * equivalent to SRC_OVER.
		 */
		pane.add(createCompositeBlendModeNode(BlendMode.SRC_ATOP, BlendMode.SRC_ATOP), 0, 1);
		pane.add(createCompositeBlendModeNode(BlendMode.MULTIPLY, BlendMode.SRC_ATOP), 1, 1);
		pane.add(createCompositeBlendModeNode(BlendMode.SRC_OVER, BlendMode.SRC_ATOP), 2, 1);
		
		pane.add(createCompositeBlendModeNode(BlendMode.SRC_ATOP, BlendMode.SRC_OVER), 0, 2);
		pane.add(createCompositeBlendModeNode(BlendMode.MULTIPLY, BlendMode.SRC_OVER), 1, 2);
		pane.add(createCompositeBlendModeNode(BlendMode.SRC_OVER, BlendMode.SRC_OVER), 2, 2);
		
		pane.add(createCompositeBlendModeNode(BlendMode.SRC_ATOP, BlendMode.MULTIPLY), 0, 3);
		pane.add(createCompositeBlendModeNode(BlendMode.MULTIPLY, BlendMode.MULTIPLY), 1, 3);
		pane.add(createCompositeBlendModeNode(BlendMode.SRC_OVER, BlendMode.MULTIPLY), 2, 3);
	}
	
	private Node createBlendModeNodeForTwo(BlendMode modeForPart, BlendMode modeForAll) {
		Rectangle r = new Rectangle();
		r.setX(0);
		r.setY(50);
		r.setWidth(50);
		r.setHeight(50);
		r.setFill(Color.BLUE);

		Circle c = new Circle();
		c.setFill(Color.RED);
		c.setCenterX(0);
		c.setCenterY(50);
		c.setRadius(25);
		c.setBlendMode(modeForPart);

		Group g = new Group();
		g.setBlendMode(modeForAll);
		g.getChildren().add(r);
		g.getChildren().add(c);
		return g;
	}
	
	private Node createCompositeBlendModeNode(BlendMode modeForPart, BlendMode modeForAll) {
		StackPane nodeHolder = new StackPane();
		Group group = new Group();
		nodeHolder.getChildren().add(group);
		Rectangle rect = new Rectangle(); 
		group.getChildren().add(rect);
		rect.setFill(Color.GREEN);
		rect.widthProperty().bind(Bindings.add(-20., nodeHolder.widthProperty()));
		rect.heightProperty().bind(nodeHolder.heightProperty());
		group.getChildren().add(createBlendModeNodeForTwo(modeForPart, modeForAll));
		return nodeHolder;
	}
}
