package edu.cuny.brooklyn.web.service;

import org.springframework.data.annotation.Id;

public class Student {
	@Id
	private String sid;
	private String name;
	private double gpa;

	public Student(String sid, String name, double gpa) {
		this.sid = sid;
		this.name = name;
		this.gpa = gpa;
	}

	public Student() {
		sid = "";
		name = "";
		gpa = 0.0;

	}

	public double getGpa() {
		return gpa;
	}

	public String getName() {
		return name;
	}

	public String getSid() {
		return sid;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String toString() {
		return String.format("Student[sid=%s, name=%s, gpa=%5.3f]", sid, name, gpa);
	}
}
