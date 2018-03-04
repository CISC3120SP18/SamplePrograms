package edu.brooklyn;

public class NumberUtil {

	public int sumToNumberWithAuto(int number) {
		int sum = 0;
		for (int i = 0; i < number; i++) {
			sum += i;
		}
		return sum;
	}

	public int sumToNumberWithStatic(int number) {
		for (int i = 0; i < number; i++) {
			sum += i;
		}
		return sum;
	}

	private static int sum = 0;
}
