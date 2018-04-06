package edu.cuny.brooklyn.io;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
	private static final long serialVersionUID = -554251929151862131L;
	private String name;
	private List<Course> courseList;
	
	public Student(String name) {
		this.name = name;
		courseList = new ArrayList<Course>();
	}
	
	public void addCourses(List<Course> courseList) {
		for (Course course: courseList) {
			this.courseList.add(course);
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Student{")
			.append("name=")
			.append("\"").append(name).append("\", ")
			.append("courseList=")
			.append(courseList)
			.append("}");
		return sb.toString();
	}
}
