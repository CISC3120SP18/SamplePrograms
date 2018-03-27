package edu.cuny.brooklyn.error;

public class SimpleExprTryCatch {
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
		switch (args[1]) {
		case "+":
			result = d1 + d2;
			System.out.println(String.join(" ", args) + " = " + result);
			break;
		case "-":
			result = d1 - d2;
			System.out.println(String.join(" ", args) + " = " + result);
		case "*":
			result = d1 * d2;
			System.out.println(String.join(" ", args) + " = " + result);
		case "/":
			try {
				result = d1 / d2;
				System.out.println(String.join(" ", args) + " = " + result);
			} catch (ArithmeticException e) {
				System.out.println("The divisisor cannot be zero.");
				System.exit(-1);
			}
		}
	}
}
