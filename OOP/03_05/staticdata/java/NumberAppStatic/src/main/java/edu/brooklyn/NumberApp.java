package edu.brooklyn;

public class NumberApp {
	public static void main(String[] args) {
		NumberUtil numberUtilOne = new NumberUtil();

		System.out.println("Automatic Data: The sum from 0 to 5 is " + numberUtilOne.sumToNumberWithAuto(5));
		System.out.println("Static Data: The sum from 0 to 5 is " + numberUtilOne.sumToNumberWithStatic(5));

		NumberUtil numberUtilTwo = new NumberUtil();

		System.out.println("Automatic Data: The sum from 0 to 5 is " + numberUtilTwo.sumToNumberWithAuto(5));
		System.out.println("Static Data: The sum from 0 to 5 is " + numberUtilTwo.sumToNumberWithStatic(5));
	}
}
