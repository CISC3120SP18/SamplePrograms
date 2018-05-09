package edu.cuny.brooklyn.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.cuny.brooklyn.web.exception.BadRequestException;
import edu.cuny.brooklyn.web.exception.CourseAlreadyExistsException;
import edu.cuny.brooklyn.web.exception.StudentAlreadyExistsException;
import edu.cuny.brooklyn.web.exception.StudentNoCourseException;
import edu.cuny.brooklyn.web.exception.StudentNotFoundException;
import edu.cuny.brooklyn.web.service.Course;
import edu.cuny.brooklyn.web.service.Gpa;
import edu.cuny.brooklyn.web.service.GpaService;
import edu.cuny.brooklyn.web.service.Student;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/gpa")
public class GpaController {
	private final static Logger LOGGER = LoggerFactory.getLogger(GpaController.class);

	private final GpaService gpaService;

	@Autowired
	GpaController(GpaService gpaService) {
		this.gpaService = gpaService;
	}

	@RequestMapping("/viewgpa/{studentid}")
	public Gpa viewGpa(@PathVariable(name = "studentid") String studentId) {
		/*
		 *  the log statement below may not pass security audit as it may expose sensitive user data
		 *  in the log, use it with caution, and change to something like in production,
		 *  
		 *  LOGGER.info("Request /viewgpa/{studentid} as /viewgpa/ with given student id");
		 */
		LOGGER.info("Request /viewgpa/{studentid} as /viewgpa/" + studentId);
		
		Student student = gpaService.findStudentById(studentId);
		if (student == null) {
			throw new StudentNotFoundException(studentId);
		}
		List<Course> courses = gpaService.findCoursesByStudentId(studentId);
		if (courses == null || courses.isEmpty()) {
			throw new StudentNoCourseException(studentId);
		}
		double gpa = gpaService.computeGPA(courses);
		return new Gpa(student.getSid(), gpa);
	}

	@RequestMapping("/viewcourses/{studentid}")
	public List<Course> viewCourses(@PathVariable(name = "studentid") String studentId) {
		LOGGER.info("Request /viewcourses/{studentid} as /viewcourses/" + studentId);
		Student student = gpaService.findStudentById(studentId);
		if (student == null) {
			throw new StudentNotFoundException(studentId);
		}
		List<Course> courses = gpaService.findCoursesByStudentId(studentId);
		if (courses == null || courses.isEmpty()) {
			throw new StudentNoCourseException(studentId);
		}

		return courses; 
	}
	
	
	@RequestMapping(value = "/addstudent", method = RequestMethod.POST)
	public boolean addStudent(@RequestBody Student student) {
		LOGGER.info("Request /gpa/addstudent: adding student.");
		/*
		 *  the log statement below may not pass security audit as it may expose sensitive user data
		 *  in the log, use it with caution, and change to something like in production,
		 *  
		 *  LOGGER.info("Got student from request");
		 */
		LOGGER.info("Got student from request: " + student.toString());
		
		Student savedStudent = gpaService.findStudentById(student.getSid());
		if ( savedStudent != null) {
			throw new StudentAlreadyExistsException(student.getSid());
		}
		
		gpaService.saveStudent(student);

		LOGGER.info("Saved student: " + student.toString());
		return true;
	}

	@RequestMapping(value = "/addcourse", method = RequestMethod.POST)
	public boolean addCourse(@RequestParam(name="studentid") String sid, @RequestBody Course course) {
		LOGGER.info("Request /gpa/addcourse: adding course.");
		
		/*
		 *  the two log statements below may not pass security audit as it may expose sensitive user data
		 *  in the log, use it with caution, and change to something like in production,
		 *  
		 *  LOGGER.info("Got student from request header");
		 *  LOGGER.info("Got course from request body");
		 */
		LOGGER.info("Got student from request header: " + sid); 
		LOGGER.info("Got course from request body: " + course.toString());
		
		Student student = gpaService.findStudentById(sid);
		if ( student == null) {
			throw new StudentNotFoundException(sid);
		}
		
		if (course.getId() == null || course.getId().isEmpty()) {
			course.updateId();
		}
		Course savedCourse = gpaService.findCoursesById(course.getId());
		if (savedCourse != null) {
			throw new CourseAlreadyExistsException(student.getSid());
		}
		
		Map<String, Double> gradePointMap = gpaService.getGradePointMap();
		course.setGradePoint(gradePointMap);
		course.setStudentId(sid);
		gpaService.saveCourse(course);
		
		/*
		 *  the log statement below may not pass security audit as it may expose sensitive user data
		 *  in the log, use it with caution, and change to something like in production,
		 *  
		 *  LOGGER.info("Saved course hash( " + Objects.hash(course.toString()) + ")");
		 */
		LOGGER.info("Saved course: " + course.toString());

		double gpa = gpaService.computeGPA(student.getSid());
		student.setGpa(gpa);
		gpaService.saveStudent(student);

		/*
		 *  the log statement below may not pass security audit as it may expose sensitive user data
		 *  in the log, use it with caution, and change to something like in production,
		 *  
		 *  LOGGER.info("Saved student hash( " + Objects.hash(student.toString()) + ")");
		 */
		LOGGER.info("Saved student: " + student.toString());
		return true;
	}
	
	/*
	 * If you consider student id is sensitive data, we should not use a path variable for 
	 * it appears in the URL. The following demonstrates required request parameters via
	 * the POST method. These parameters will be encrypted when HTTPS is the scheme of
	 * the URL and thus become intelligible on the network.  
	 * 
	 * With this in mind, should we revise the few APIs that expose student id in the
	 * URLs?
	 */
	@RequestMapping(value = "/gradechange", method = RequestMethod.POST)
	public boolean changeCourseGrade(@RequestBody Course course) {
		LOGGER.info("Request /gpa/gradechange: attempt to change grade for a course.");
		/*
		 *  the the following log statement below may not pass security audit as it may expose sensitive 
		 *  user data in the log, use it with caution, and change to something like in production,
		 *  
		 *  LOGGER.info("Got course parameter from the request body");
		 */
		LOGGER.info("Got parameters in request body: course = ", course.toString());
		if (course.getStudentId() == null || course.getStudentId().isEmpty()) {
			throw new BadRequestException("Required parameter studentId not present in request body");
		}
		if (course.getNumber() == null || course.getNumber().isEmpty()) {
			throw new BadRequestException("Required parameter number not present in request body");
		}
		if (course.getTitle() == null || course.getTitle().isEmpty()) {
			throw new BadRequestException("Required parameter title not present in request body");
		}
		if (course.getTerm() == null || course.getTerm().isEmpty()) {
			throw new BadRequestException("Required parameter term not present in request body");
		}
		if (course.getGrade() == null || course.getGrade().isEmpty()) {
			throw new BadRequestException("Required parameter grade not present in request body");
		}
		
		Student student = gpaService.findStudentById(course.getStudentId());
		if ( student == null) {
			throw new StudentNotFoundException(course.getStudentId());
		}

		if (course.getId() == null || course.getId().isEmpty()) {
			course.updateId();
		}
		Course savedCourse = gpaService.findCoursesById(course.getId());
		if (savedCourse == null) {
			throw new StudentNoCourseException(course.getId());
		}
		savedCourse.setGrade(course.getGrade());
		
		Map<String, Double> gradePointMap = gpaService.getGradePointMap();
		savedCourse.setGradePoint(gradePointMap);
		savedCourse.setStudentId(savedCourse.getStudentId());
		gpaService.saveCourse(savedCourse);
		
		/*
		 *  the log statement below may not pass security audit as it may expose sensitive user data
		 *  in the log, use it with caution, and change to something like in production,
		 *  
		 *  LOGGER.info("Updated course hash( " + Objects.hash(course.toString()) + ") with the grade change");
		 */
		LOGGER.info("Updated course with the grade change: " + savedCourse.toString());
		

		double gpa = gpaService.computeGPA(savedCourse.getStudentId());
		student.setGpa(gpa);
		gpaService.saveStudent(student);

		/*
		 *  the log statement below may not pass security audit as it may expose sensitive user data
		 *  in the log, use it with caution, and change to something like in production,
		 *  
		 *  LOGGER.info("updated student hash( " + Objects.hash(student.toString()) + ") with the grade change");
		 */
		LOGGER.info("Updated student with the grade change: " + student.toString());
		return true;
	}
}
