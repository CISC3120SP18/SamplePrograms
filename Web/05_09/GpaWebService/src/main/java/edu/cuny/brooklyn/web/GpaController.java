package edu.cuny.brooklyn.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cuny.brooklyn.web.error.StudentNotFoundException;

@RestController
@RequestMapping("/gpa")
public class GpaController {
	private Random rng;
	private Map<String, Student> studentMap;
	
	public GpaController() {
		rng = new Random();
		studentMap = new HashMap<String, Student>();
		createRandomStudents(10);
	}

	@RequestMapping("/{studentid}")
	public Student getGpa(@PathVariable(name = "studentid") String sid) throws StudentNotFoundException {
		Student student = studentMap.get(sid);
		if (student == null) {
			throw new StudentNotFoundException("Requested student not found");
		}
		return student;
	}
	
	/* create a bound of random student object for demo purpose */
	private void createRandomStudents(int numStudents) {
		String[] firstnames = {"Tom", "Jane", "Uma", "Will", "Bruce", "Nicole"};
		String[] lastnames = {"Willis", "Smith", "Hanks", "Thurman", "Portman", "Fonda"}; 
		for (int i=0; i<numStudents; i++) {
			String sid = String.format("%02d", i);
			String fn = firstnames[rng.nextInt(firstnames.length)];
			String ln = lastnames[rng.nextInt(lastnames.length)];
			double gpa = 2.0 + 2.0*rng.nextDouble();
			studentMap.put(sid, 
					new Student(sid, fn, ln, gpa));
		}
	}
}
