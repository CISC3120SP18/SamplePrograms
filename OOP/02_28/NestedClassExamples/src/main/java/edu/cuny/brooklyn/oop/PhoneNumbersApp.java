package edu.cuny.brooklyn.oop;

public class PhoneNumbersApp {
	public static void main(String... args) {
		// let's try "123-456-7890" and "456-7890" on the command line
		for (String phoneNumber : args) {
			if (PhoneNumbers.validatePhoneNumber(phoneNumber)) {
				System.out.println("Phone number " + phoneNumber + " is valid.");
			} else {
				System.out.println("Phone number " + phoneNumber + " is invalid.");
			}
		}
	}
}
