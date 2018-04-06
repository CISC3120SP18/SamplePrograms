package edu.cuny.brooklyn.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentObjInputCliApp {
	private final static Logger LOGGER = LoggerFactory.getLogger(StudentObjInputCliApp.class);
	
	public static void main(String[] args) {
		StudentObjInputCliApp app = new StudentObjInputCliApp();
		
			try {
				List<Student> studentList = app.readStudentListFromFile("files/studentlist.bin");
				app.print(studentList);
			} catch (FileNotFoundException e) {
				System.err.println("The input file is not found.");
				LOGGER.error("The input file is not found.", e);
			} catch (IOException e) {
				System.err.println("Failed to read the object file.");
				LOGGER.error("Failed to read the object file.", e);
			} catch (ClassNotFoundException e) {
				System.err.println("The file isn't student object list file.");
				LOGGER.error("The file isn't student object list file.", e);
			}
	}
	
	private List<Student> readStudentListFromFile(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
		List<Student> studentList = new ArrayList<Student>();
		int numStudents;
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
			numStudents = in.readInt();
			for (int i=0; i<numStudents; i++) {
				Student student = (Student)in.readObject();
				studentList.add(student);
			}
		}
		return studentList;
	}
	
	private void print(List<Student> studentList) {
		for (Student student: studentList) {
			System.out.println(student.toString());
		}
	}
}
