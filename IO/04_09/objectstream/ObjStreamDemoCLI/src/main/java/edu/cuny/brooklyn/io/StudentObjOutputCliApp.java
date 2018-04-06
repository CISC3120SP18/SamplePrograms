package edu.cuny.brooklyn.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentObjOutputCliApp {
	private final static Logger LOGGER = LoggerFactory.getLogger(StudentObjOutputCliApp.class);
	
	public static void main(String... args) {
		StudentObjOutputCliApp app = new StudentObjOutputCliApp();
		
		Student student = app.prepareStudentData();
		List<Student> studentList = new ArrayList<Student>();
		studentList.add(student);
		
		try {
			app.prepareObjFileDirectory("files");
			app.writeStudentListToFile("files/studentlist.bin", studentList);
			System.out.println("Wrote out the student list.");
		} catch(FileNotFoundException e) {
			System.err.println("Cannot open the file for output.");
			LOGGER.error("Cannot open the file for output", e);
		} catch(IOException e) {
			System.err.println("Cannot create the required directory or write the data.");
			LOGGER.error("Cannot create the required directory or write the data.", e);
		} 
		

	}
	
	private void prepareObjFileDirectory(String objDir) throws IOException {
		Path objFileDirPath = Paths.get(objDir);
		if (Files.notExists(objFileDirPath)) {
			Files.createDirectory(objFileDirPath);
		}
	}
	
	private Student prepareStudentData() {
		List<Course> courseList = new ArrayList<Course>();
		courseList.add(new Course("CISC 3120", "Design & Implementation of Software Applications I", 3.0, "A"));
		courseList.add(new Course("CISC 3115", "Introduction to Modern Programming", 4.0, "A"));
		
		Student student = new Student("Jane Doe");
		student.addCourses(courseList);
		
		return student;
	}
	
	private void writeStudentListToFile(String filename, List<Student> studentList) throws FileNotFoundException, IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
			out.writeInt(studentList.size());
			for(Student student: studentList) {
				out.writeObject(student);
			}
		}
	}

}
