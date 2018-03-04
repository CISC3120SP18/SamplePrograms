package edu.brooklyn;

public class NumberUtil {
	public int sumToNumber(int number) {
		int sum = 0;
		for (int i = 0; i <= number; i++) {
			sum += i;
		}
		return sum;
	}
}
