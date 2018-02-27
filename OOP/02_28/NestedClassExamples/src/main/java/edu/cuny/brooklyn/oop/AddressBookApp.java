package edu.cuny.brooklyn.oop;

import java.util.Iterator;

public class AddressBookApp {
	public static void main(String... args) {
		AddressBook ab = new AddressBook ();
		ab.addPhoneNumber("123-456-7890");
		ab.addPhoneNumber("234-567-8901");;
		System.out.println(ab.toString());
		
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
	}
		
}
