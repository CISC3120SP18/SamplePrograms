/*
 * This example is about 
 *  - anonymous class
 *  - lambda expression
 */
package edu.cuny.brooklyn.oop;

import java.util.Iterator;
import java.util.Random;
// import java.util.function.Predicate;

public class AddressBookApp {
	private final static String[] NYC_AREA_CODES = {"212", "332", "347", "646", "718", "917", "929"};
	private final static int PHONE_NUMBER_LENGTH = 10;
	private final static int AREA_CODE_LENGTH = 3;
	
	public static void main(String... args) {
		AddressBookApp app = new AddressBookApp();
		
		// method demonstrates anonymous class 
		app.printAddressBookViaAnonymousClass();
		
		// method demonstrates lambda expression
		System.out.println("Make and print out a list of random phone numbers:");
		AddressBook ab = app.makeRandomPhoneBook(10);
		app.printAddressBook(ab);
		app.removeRandomAreaCode(ab);
		System.out.println("Remove phone numbers with a random chosen area code, and print out the rest:");
		app.printAddressBook(ab);
	}

	private void removeRandomAreaCode(AddressBook ab) {
		final String areaCode = NYC_AREA_CODES[new Random().nextInt(NYC_AREA_CODES.length)];
		System.out.println("Area code to remove is: " + areaCode);
		
		ab.removeIf(p -> p.indexOf(areaCode) == 0);

		// You may write a lambda expression starting from the anonymous class expression
		// step 1. write the expression for the anonymous class
//		ab.removeIf(new Predicate<String>() {
//
//			@Override
//			public boolean test(String phoneNumber) {
//				return phoneNumber.indexOf(areaCode) == 0;
//			}
//		});
		
		// step 2. comment out the boiler-plate code
//		ab.removeIf(/*new Predicate<String>() {
//
//			@Override
//			public boolean test*/(String phoneNumber) /* add -> */ -> {
//				return phoneNumber.indexOf(areaCode) == 0;
//			}/*
//		}*/);		
		
		// step 3. get rid of the boiler-plate code
//		ab.removeIf((String phoneNumber) -> {return phoneNumber.indexOf(areaCode) == 0;});

		// step 4. simply it further
//		ab.removeIf(phoneNumber -> phoneNumber.indexOf(areaCode) == 0);
		
		// step 5. more ...
//		ab.removeIf(p -> p.indexOf(areaCode) == 0);
	}

	public void printAddressBookViaAnonymousClass( ) {
		System.out.println("Print out a phone book:");
		AddressBook ab = new AddressBook();
		ab.addPhoneNumber("123-456-7890");
		ab.addPhoneNumber("234-567-8901");
		System.out.println(ab.toString());
		System.out.println("-----------------------------------------");
		System.out.println();

		System.out.println("Print out a phone book using an overriden toString() method in an anonymous class:");
		System.out.println("Do over with enhanced toString() via an anonymous class:");
		ab = new AddressBook() {
			
			@Override 
			public String toString() {
				StringBuilder sb = new StringBuilder();
				Iterator<String> iter = iterator();
				int i = 0;
				while (iter.hasNext()) {
					String number = iter.next();
					sb.append("Phone Number[")
						.append(i)
						.append("]=")
						.append(number)
						.append(System.getProperty("line.separator"));
					i ++;
				}
				return sb.toString();
			}
		};
		ab.addPhoneNumber("123-456-7890");
		ab.addPhoneNumber("234-567-8901");
		System.out.println(ab.toString());
		System.out.println("-----------------------------------------");
	}

	
	private AddressBook makeRandomPhoneBook(int numOfEntries) {
		final int DASH_POSITION = 3;

		
		AddressBook addressBook = new AddressBook();
		
		StringBuilder sb = new StringBuilder(); 
		Random rng = new Random();
		for(int i=0; i<numOfEntries; i++) {
			int areaCodeIndex = rng.nextInt(NYC_AREA_CODES.length);
			sb.append(NYC_AREA_CODES[areaCodeIndex]).append("-");
			for (int j=0; j<PHONE_NUMBER_LENGTH-AREA_CODE_LENGTH; j++) {
				if (j == DASH_POSITION) sb.append('-'); 
				sb.append(rng.nextInt(10));
			}
			addressBook.addPhoneNumber(sb.toString());
			sb.setLength(0);
		}

		return addressBook;
	}


	private void printAddressBook(AddressBook ab) {
		StringBuilder sb = new StringBuilder();
		Iterator<String> iter = ab.iterator();
		int i = 0;
		while (iter.hasNext()) {
			String number = iter.next();
			sb.append("Phone Number[")
				.append(i)
				.append("]=")
				.append(number)
				.append(System.getProperty("line.separator"));
			i ++;
		}
		System.out.println(sb.toString());
	}
}
