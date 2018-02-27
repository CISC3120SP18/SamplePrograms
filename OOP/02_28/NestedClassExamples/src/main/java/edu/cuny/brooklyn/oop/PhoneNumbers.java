/**
 * Adapted from the Java Tutorial by Oracle at
 * https://docs.oracle.com/javase/tutorial/java/javaOO/localclasses.html
 */
package edu.cuny.brooklyn.oop;

public class PhoneNumbers {
	static String nonDigitRegularExpression = "[^\\d]";
 
    public static boolean validatePhoneNumber(String phoneNumber) {
        final int numberLength = 10;

        class PhoneNumber {
            String formattedPhoneNumber = null;

            PhoneNumber(String phoneNumber){
            	// Remove all non-digit characters from the phoneNumber
                String currentNumber = phoneNumber.replaceAll(nonDigitRegularExpression, "");

                if (currentNumber.length() == numberLength) {
                    formattedPhoneNumber = currentNumber;
                } else { 
                    formattedPhoneNumber = null;
                }
            }
            
            public boolean isValid() {
            	return formattedPhoneNumber != null;
            }
        }

        PhoneNumber number = new PhoneNumber(phoneNumber);
        return number.isValid();
    }
}
