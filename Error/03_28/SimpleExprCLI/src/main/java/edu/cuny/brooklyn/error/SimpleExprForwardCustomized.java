package edu.cuny.brooklyn.error;

public class SimpleExprForwardCustomized {
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Usage: SimpleExprImproved <integer> <+|-|*|/> <integer>");
			return;
		}
		int d1 = 0, d2 = 0;
		try {
			d1 = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			System.out.println("SimpleExprImproved must take two integer operants." + args[0] + " is not an integer.");
			System.exit(-1);
		}
		try {
			d2 = Integer.parseInt(args[2]);
		} catch (NumberFormatException e) {
			System.out.println("SimpleExprImproved must take two integer arguments." + args[2] + " is not an integer.");
			System.exit(-1);
		}

		int result;
		result = expr(d1, d2, args[1]);
		System.out.println(d1 + args[1] + d2 + "=" + result);
	}

	public static int expr(int d1, int d2, String operator) throws ArithmeticException, IllegalArgumentException {
		int result = 0;
		switch (operator) {
		case "+":
			result = d1 + d2;
			break;
		case "-":
			result = d1 - d2;
			break;
		case "*":
			result = d1 * d2;
			break;
		case "/":
			try {
				result = d1 / d2;
			} catch (ArithmeticException e) {
				throw  new ArithmeticException("Attempt to evaluate " + d1 + "/" + d2);
			}
			break;
		default:
			throw new IllegalArgumentException("Operator " + operator + " is not supported.");
		}
		return result;
	}
}
