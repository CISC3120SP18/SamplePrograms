package edu.cuny.brooklyn.oop.person;

public class Professor extends Person {
	private final static int SABATTICAL_LEAVE_INTERVAL = 7;
	private int yearStarted;
	
	public Professor(String name, String id, String address, int yearStarted) {
		super(name, id, address);
		this.yearStarted = yearStarted;
	}
	
	public void applySabbatical(int applicationYear) {
		if (applicationYear - yearStarted >= SABATTICAL_LEAVE_INTERVAL) {
			System.out.println("Professor " + name + " can apply a sabbatical leave.");
		} else {
			System.out.println("Professor " + name + " may not apply a sabbatical leave.");
		}
	}
}
