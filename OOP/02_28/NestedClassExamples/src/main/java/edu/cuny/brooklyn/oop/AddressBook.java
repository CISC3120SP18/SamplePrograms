package edu.cuny.brooklyn.oop;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

public class AddressBook {
	// Why Set? 
	private Set<String> phoneNumbers;
	
	public AddressBook() {
		phoneNumbers = new HashSet<String>();
	}
	
	public void addPhoneNumber(String number) {
		phoneNumbers.add(number);
	}
	
	public void removeIf(Predicate<? super String> filter) {
		phoneNumbers.removeIf(filter);
	}
	
	public Iterator<String> iterator() {
		return phoneNumbers.iterator();
	}
	
	public String toString() {
		return String.join(",", phoneNumbers.toArray(new String[phoneNumbers.size()]));
	}
}
