class SquareRoot {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: SquareRoot <number>");
            System.exit(1);
        }

        double number = Double.parseDouble(args[0]);
        System.out.println("The aquare root of " + number + " is "  + Math.sqrt(number));
    }
}
