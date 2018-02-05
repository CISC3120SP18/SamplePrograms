/*
 * Selection is a means of flow-control in imperative programming. Some
 * literature also call it branching. This example shows two selection
 * statements, the if-then statement and the if-then-else statement
 *
 * See 
 *  https://docs.oracle.com/javase/specs/jls/se9/html/jls-14.html#jls-14.9
 *  https://docs.oracle.com/javase/specs/jls/se9/html/jls-14.html#jls-14.11
 * 
 * More discussion on Flow Control is in C05
 */
class SimpleExpr {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: Expr Operand_1 Operator Operand_2");
        }

        int operand1 = Integer.parseInt(args[0]);
        String operator = args[1];
        int operand2 = Integer.parseInt(args[2]);

        int result = 0;
        if (operator.equals("+")) {
            result = operand1 + operand2;
        } else if (operator.equals("-")) {
            result = operand1 - operand2;
        } else if (operator.equals("*")) {
            result = operand1 * operand2;
        } else if (operator.equals("/")) {
            result = operand1 / operand2;
        } else {
            System.out.println("Error: operator " + operator + " not supported.");
            System.exit(1);
        }
        System.out.println(operand1 + " " + operator + " " + operand2 + " = " + result);
    }
}
