package edu.cuny.brooklyn.io;

import java.io.Serializable;

public class Course implements Serializable {
	private static final long serialVersionUID = -246278207912562415L;
	private String title;
	private String prefix;
	private double credits;
	private String grade;
	
	public Course(String prefix, String title, double credits, String grade) {
		this.prefix = prefix;
		this.title = title;
		this.credits = credits;
		this.grade = grade;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Course{")
			.append("prefix=").append("\"").append(prefix).append("\",")
			.append("title=").append("\"").append(title).append("\",")
			.append("credits=").append(credits).append(",")
			.append("grade=").append("\"").append(grade).append("\"}");
		return sb.toString();
			
	}
}
