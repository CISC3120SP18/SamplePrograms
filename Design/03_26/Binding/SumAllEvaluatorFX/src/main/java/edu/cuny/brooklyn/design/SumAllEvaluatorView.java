package edu.cuny.brooklyn.design;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.StringProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class SumAllEvaluatorView {
	private final static Logger LOGGER = LoggerFactory.getLogger(SumAllEvaluatorView.class);
	private Scene scene;
	private ArrayList<StringProperty> inputList;
	
	public SumAllEvaluatorView() {
		inputList = new ArrayList<StringProperty>();
		
		GridPane pane = new GridPane();
		scene = new Scene(pane);

		pane.setHgap(5.);
		pane.setVgap(10.);
		pane.setPadding(new Insets(50., 250., 150., 250.));
		
		addColumns(pane);
		
		for (int i = 0; i < 5; i++) {
			addInputField(pane, i);
		}
		
		addOperatorAndSeparator(pane, 5);
		
		addResultField(pane, 6);
	}


	public void showOn(Stage stage) {
		stage.setTitle("Sum All Fields");
		stage.setScene(scene);
		stage.show();
	}
	
	private void addColumns(GridPane pane) {
		ColumnConstraints column = new ColumnConstraints(50., 50., Double.MAX_VALUE, Priority.NEVER, HPos.RIGHT, true);
		pane.getColumnConstraints().add(column);
		column = new ColumnConstraints(150., 150., Double.MAX_VALUE, Priority.ALWAYS, HPos.RIGHT, true);
		pane.getColumnConstraints().add(column);
	}
	
	private void addInputField(GridPane pane, int rowIndex) {
		TextField input = new TextField();
		input.setAlignment(Pos.CENTER_RIGHT);
		inputList.add(input.textProperty());
		pane.add(input, 1, rowIndex);
		input.setOnMouseClicked(e -> input.selectAll());
	}
	
	private void addOperatorAndSeparator(GridPane pane, int rowIndex) {
		pane.add(new Label("+"), 0, rowIndex-1);
		pane.add(new Separator(), 0, rowIndex, 2, 1);
	}
	
	
	private void addResultField(GridPane pane, int rowIndex) {
		pane.add(new Label("="), 0, rowIndex);
		Label result = new Label();
		pane.add(result, 1, rowIndex);
		
		StringBinding binding = new SumAllBinding(inputList);
		
		Button computeButton = new Button("Compute");
		pane.add(computeButton, 1, rowIndex + 1);
		computeButton.setOnAction(e -> {
			result.setText(binding.get());
		});
		
		// we may also get rid of the computeButton, bind 
		// the result's textProperty to the binding
//		result.textProperty().bind(binding);
	}
	
	private class SumAllBinding extends StringBinding {
		SumAllBinding(final ArrayList<StringProperty> inputList) {
			super.bind(inputList.toArray(new StringProperty[inputList.size()]));
		}

		@Override
		protected String computeValue() {
			LOGGER.debug("computeValue called");
			double sum = 0.;
			for (StringProperty e: inputList) {
				String s = e.get();
				if (!s.isEmpty()) {
					double d = Double.parseDouble(s);
					sum += d;
				}
			}
			return Double.toString(sum);
		}
	}
}
