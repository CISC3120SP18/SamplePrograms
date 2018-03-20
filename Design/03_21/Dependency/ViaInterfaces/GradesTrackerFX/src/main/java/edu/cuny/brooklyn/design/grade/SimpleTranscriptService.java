package edu.cuny.brooklyn.design.grade;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleTranscriptService implements TranscriptService {
	private final static Logger LOGGER = LoggerFactory.getLogger(SimpleTranscriptService.class); 

	private String student;
	private List<Course> courseList;
	
	private double totalCredits;
	private double gradePoints;
	private double gpa;
	
	public SimpleTranscriptService() {
		courseList = new ArrayList<Course>();
	}

	@Override
	public void setName(String student) {
		this.student = student;
	}
	
	/* any course change, the gpa may need to recompute! what is the best way to handle it? */
	@Override
	public double getGPA() {
		return gpa;
	}

	@Override
	public void addCourse(Course course) {
		courseList.add(course);
	}
	
	@Override
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

	@Override
	public int getNumberOfCourses() {
		return courseList.size();
	}

	@Override
	public double getTotalCredits() {
		return totalCredits;
	}

	@Override
	public double getTotalGradePoints() {
		return gradePoints;
	}
	
	@Override
	public String getStudent() {
		return student;
	}
}
