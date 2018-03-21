package edu.cuny.brooklyn.design;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TwoOperandExpression {
	private DoubleProperty operand1;
	private DoubleProperty operand2;
	private StringProperty operator;
	private DoubleProperty result;
	
	public TwoOperandExpression() {
		operand1 = new SimpleDoubleProperty();
		operand2 = new SimpleDoubleProperty();
		operator = new SimpleStringProperty();
		result = new SimpleDoubleProperty();
	}

	/*----------------------------------------------------------------------------*/
	/* Following the convention, create the  setter, getter, and property methods */
	public double getOperand1() {
		return operand1.get();
	}

	public double getOperand2() {
		return operand2.get();
	}

	public String getOperator() {
		return operator.get();
	}
	
	public double getResult() {
		return result.get();
	}

	public void setOperand1(double operandValue) {
		operand1.set(operandValue);
	}

	public void setOperand2(double operandValue) {
		operand2.set(operandValue);
	}

	public void setOperator(String operatorValue) {
		operator.set(operatorValue);
	}
	
	public void setResult(double resultValue) {
		result.set(resultValue);
	}
	
	public DoubleProperty operand1Property() {
		return operand1;
	}
	
	public DoubleProperty operand2Property() {
		return operand2;
	}
	
	public StringProperty operatorProperty() {
		return operator;
	}
	
	public DoubleProperty resultProperty() {
		return result;
	}
	/*----------------------------------------------------------------------------*/
	
	public void evaluate() {
		double resultValue = Double.NaN;
		switch(operator.get()) {
		case "+": 
			resultValue = operand1.get() + operand2.get();
			break;
		case "-":
			resultValue = operand1.get() - operand2.get();
			break;
		case "*":
			resultValue = operand1.get() * operand2.get();
			break;
		case "/":
			if (operand2.get() != 0.) {
				resultValue = operand1.get() / operand2.get();
			}
			break;
		default:
			throw new RuntimeException("Unsupported operator " + operator.get() + ". This should never happen.");
		}
		result.set(resultValue);
	}
}
