package edu.cuny.brooklyn.design.grade;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptService {
	private final static Logger LOGGER = LoggerFactory.getLogger(TranscriptService.class); 


	private String student;
	private List<Course> courseList;
	
	private double totalCredits;
	private double gradePoints;
	private double gpa;
	
	public TranscriptService() {
		courseList = new ArrayList<Course>();
	}

	public void setName(String student) {
		this.student = student;
	}
	
	/* any course change, the gpa may need to recompute! what is the best way to handle it? */
	public double getGPA() {
		return gpa;
	}

	public void addCourse(Course course) {
		courseList.add(course);
	}
	
	public void updateGPA() {
		totalCredits = 0.0;
		gradePoints = 0.0;
		for (Course course: courseList) {
			LOGGER.debug("Process course: " + course.toString());
			totalCredits += course.getCredits();
			gradePoints += course.getGradePoints();
		}
		gpa = gradePoints / totalCredits;
	}

	public int getNumberOfCourses() {
		return courseList.size();
	}

	public double getTotalCredits() {
		return totalCredits;
	}

	public double getTotalGradePoints() {
		return gradePoints;
	}
	
	
	public String getStudent() {
		return student;
	}
}
