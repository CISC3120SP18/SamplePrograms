package edu.cuny.brooklyn.web;

public class Student {
	private double gpa;
	private String sid;
	private String firstname;
	private String lastname;

	public Student(String sid, String firstname, String lastname, double gpa) {
		this.sid = sid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gpa = gpa;
	}

	public String getFirstname() {
		return firstname;
	}

	public double getGpa() {
		return gpa;
	}

	public String getLastname() {
		return lastname;
	}

	public String getSid() {
		return sid;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
}
