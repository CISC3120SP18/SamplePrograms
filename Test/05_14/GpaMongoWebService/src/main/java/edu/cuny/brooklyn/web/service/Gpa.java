package edu.cuny.brooklyn.web.service;

public class Gpa {
	private String sid;
	private double gpa;
	
	public Gpa(String sid, double gpa) {
		this.sid = sid;
		this.gpa = gpa;
	}

	public String getSid() {
		return sid;
	}

	public double getGpa() {
		return gpa;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
}
