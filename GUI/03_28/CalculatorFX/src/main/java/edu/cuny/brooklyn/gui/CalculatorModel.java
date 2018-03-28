package edu.cuny.brooklyn.gui;

public class CalculatorModel {

	public long compute(long operandLeft, long operandRight, String operator) {
		long result = 0;
		switch (operator) {
		case "+":
			result = operandLeft + operandRight;
			break;
		case "-":
			result = operandLeft - operandRight;
		}
		return result;
	}
}
