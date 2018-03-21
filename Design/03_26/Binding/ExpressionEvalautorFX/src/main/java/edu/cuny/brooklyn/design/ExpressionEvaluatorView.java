package edu.cuny.brooklyn.design;

import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class ExpressionEvaluatorView {
	private Scene scene;
	
	public ExpressionEvaluatorView() {
		GridPane pane = new GridPane();
		scene = new Scene(pane);
		
		pane.setPadding(new Insets(20., 20., 20., 20.));
		pane.setHgap(2.);
		pane.setVgap(10.);
		
		createExpressionOnUI(pane, 0, true, "+");
		createExpressionOnUI(pane, 1, false, "-");
		createExpressionOnUI(pane, 2, false, "*");
		createExpressionOnUI(pane, 3, false, "/");
	}
	
	public void showOn(Stage stage) {
		stage.setTitle("A simple two-operand expression evaluator");
		stage.setScene(scene);
		stage.show();
	}
	
	private void createExpressionOnUI(GridPane pane, int rowIndex, boolean addColumnConstraints, String operatorValue) {
		// we need this a few times
		StringConverter<Number> converter = new NumberStringConverter();
		
		// prepare UI for an exprssion
		ColumnConstraints columnConstraints;
		if (addColumnConstraints) {
			columnConstraints = new ColumnConstraints(150, 150, Double.MAX_VALUE, Priority.ALWAYS, HPos.RIGHT, true);
			pane.getColumnConstraints().add(columnConstraints);
			
			columnConstraints = new ColumnConstraints(30., 30., Double.MAX_VALUE, Priority.NEVER, HPos.CENTER, true);
			pane.getColumnConstraints().add(columnConstraints);

			columnConstraints = new ColumnConstraints(150, 150, Double.MAX_VALUE, Priority.ALWAYS, HPos.LEFT, true);
			pane.getColumnConstraints().add(columnConstraints);		

			columnConstraints = new ColumnConstraints(30., 30., Double.MAX_VALUE, Priority.NEVER, HPos.CENTER, true);
			pane.getColumnConstraints().add(columnConstraints);

			columnConstraints = new ColumnConstraints(150, 150, Double.MAX_VALUE, Priority.ALWAYS, HPos.LEFT, true);
			pane.getColumnConstraints().add(columnConstraints);	
		}
		TextField operand1 = new TextField();
		pane.add(operand1, 0, rowIndex);

		Label operator = new Label(operatorValue);
		pane.add(operator, 1, rowIndex);
		
		TextField operand2 = new TextField();
		pane.add(operand2, 2, rowIndex);
		
		pane.add(new Label("="), 3, rowIndex);
		Label result = new Label();
		pane.add(result, 4, rowIndex);
		// prepare the expression object
		TwoOperandExpression expression = new TwoOperandExpression();
		// bind TextField operand1 to the expression object's operand1
		Bindings.bindBidirectional(operand1.textProperty(), expression.operand1Property(), converter);
		Bindings.bindBidirectional(operand2.textProperty(), expression.operand2Property(), converter);
		expression.operatorProperty().bind(operator.textProperty());
		
		// add two listeners
		operand1.textProperty().addListener((obsv, oldv, newv) -> expression.evaluate());
		operand2.textProperty().addListener((obsv, oldv, newv) -> expression.evaluate());
		
		// select all text when clicked (to provide convience to the  users)
		operand1.setOnMouseClicked(e -> operand1.selectAll());
		operand2.setOnMouseClicked(e -> operand2.selectAll());
		
		// bind results together
		result.textProperty().bindBidirectional(expression.resultProperty(), converter);	
	}
}
