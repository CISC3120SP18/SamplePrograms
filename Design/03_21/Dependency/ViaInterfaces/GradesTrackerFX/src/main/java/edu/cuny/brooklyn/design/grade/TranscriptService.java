package edu.cuny.brooklyn.design.grade;

public interface TranscriptService {
	/* any course change, the gpa may need to recompute! what is the best way to handle it? */
	public double getGPA();
	
	public int getNumberOfCourses();

	public double getTotalCredits();

	public double getTotalGradePoints();

	public String getStudent();

	public void setName(String name);

	public void updateGPA();

	public void addCourse(Course course);
}
