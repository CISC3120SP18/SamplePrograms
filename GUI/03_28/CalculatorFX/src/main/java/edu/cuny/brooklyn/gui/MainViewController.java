package edu.cuny.brooklyn.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainViewController {
	private String operator = "";
	private String operandLeft = "";
	private CalculatorModel calculatorModel = new CalculatorModel();

	@FXML
	TextField outputTextField;

	@FXML
	void processNumberKeys(ActionEvent event) {
		outputTextField.setText(outputTextField.getText() + ((Button) event.getSource()).getText());
	}

	@FXML
	void processOperatorKeys(ActionEvent event) {
		operator = ((Button) event.getSource()).getText();
		operandLeft = outputTextField.getText();
		outputTextField.setText("");
	}

	void processEqualKey(ActionEvent event) {
		if (operator.isEmpty())
			return;

		long operandLeft = Long.parseLong(this.operandLeft);
		long operandRight = outputTextField.getText().isEmpty() ? 0 : Long.parseLong(outputTextField.getText());
		long result = calculatorModel.compute(operandLeft, operandRight, operator);
		outputTextField.setText(Long.toString(result));
		this.operandLeft = outputTextField.getText();

		operator = "";
	}

}
