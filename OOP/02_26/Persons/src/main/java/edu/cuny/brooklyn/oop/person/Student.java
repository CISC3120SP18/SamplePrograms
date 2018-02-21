package edu.cuny.brooklyn.oop.person;

import java.util.ArrayList;

public class Student extends Person {
	private ArrayList<String> classesTaken;	// where should  my grade hold, how about credit hours?

	public Student(String name, String id, String address) {
		super(name, id, address);
		
		classesTaken = new ArrayList<String>();
	}
	
	public void haveTakenClass(String className) {
		classesTaken.add(className);
	}
	
	public void showClassesTaken() {
		System.out.println(name + " has taken " + classesTaken.size() + " classes.");
		for (String className: classesTaken) {
			System.out.println("\t\t" + className);
		}
	}
	
	public String toString() {
		return "Student (name=" + name + ", id=" + id + ", address=" + address 
				+ ", coursesTaken=[" + String.join(", ", classesTaken) + "])";
	}
}
