package edu.cuny.brooklyn.design.grade;

import edu.cuny.brooklyn.design.grade.GradeNumberService.LetterGrade;

public class Course {
	private String courseNo;
	private int credits;
	private LetterGrade letterGrade;


	public String getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public LetterGrade getLetterGrade() {
		return letterGrade;
	}
	
	public void setLetterGrade(LetterGrade grade) {
		letterGrade = grade;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Course[courseNo=")
			.append(courseNo).append(",")
			.append("credits=").append(credits).append(",")
			.append("letterGrade=").append(letterGrade).append("]");
		return sb.toString();
	}

	public double getGradePoints() {
		double gradePoints = 0.0;
		if (GradeNumberService.hasGradeNumber(letterGrade)) {
			gradePoints = GradeNumberService.getGradePointNumber(letterGrade) * credits;
		}
		return gradePoints;
	}
}
