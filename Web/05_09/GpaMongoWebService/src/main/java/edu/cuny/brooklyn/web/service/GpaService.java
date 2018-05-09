package edu.cuny.brooklyn.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cuny.brooklyn.web.data.CourseRepository;
import edu.cuny.brooklyn.web.data.GradePointRepository;
import edu.cuny.brooklyn.web.data.StudentRepository;

@Service
public class GpaService {
	private final static Logger LOGGER = LoggerFactory.getLogger(GpaService.class);

	@Autowired
	private final GradePointRepository gradePointRepository;

	@Autowired
	private final CourseRepository courseRepository;

	@Autowired
	private final StudentRepository studentRepository;

	Map<String, Double> gradePointsMap;

	@Autowired
	public GpaService(GradePointRepository gradePointRepository, StudentRepository studentRepository,
			CourseRepository courseRepository) {
		this.gradePointRepository = gradePointRepository;
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;

		gradePointsMap = new HashMap<String, Double>();
		List<GradePoint> gradePointList = this.gradePointRepository.findAll();
		gradePointList.forEach((gradePoint) -> {
			gradePointsMap.put(gradePoint.getGrade(), gradePoint.getPoints());
		});
	}

	public void saveStudent(Student student) {
		studentRepository.save(student);
	}

	public void saveCourse(Course course) {
		course.updateId();
		courseRepository.save(course);
	}
	

	public Course findCoursesById(String id) {
		Optional<Course> optional = courseRepository.findById(id);
		if (optional.isPresent()) return optional.get();
		else return null;
	}

	public Student findStudentById(String studentId) {
		Student student = studentRepository.findBySid(studentId);
		return student;
	}

	public List<Course> findCoursesByStudentId(String studentId) {
		List<Course> courses = courseRepository.findAllByStudentId(studentId);
		return courses;
	}

	public Map<String, Double> getGradePointMap() {
		List<GradePoint> gradePointList = gradePointRepository.findAll();

		Map<String, Double> gradePointMap = new HashMap<String, Double>();
		gradePointList.forEach(gradePoint -> {
			gradePointMap.put(gradePoint.getGrade(), gradePoint.getPoints());
		});

		return gradePointMap;
	}

	public double computeGPA(String sid) {
		List<Course> courseList = courseRepository.findAllByStudentId(sid);
		if (courseList == null || courseList.isEmpty()) {
			LOGGER.info("Student " + sid + " has not taken any classes.");
			return 0.0;
		} else {
			return computeGPA(courseList);
		}
	}

	public double computeGPA(List<Course> courseList) {
		double accumulatedGradePoints = 0;
		int accumulatedCredits = 0;
		for (Course course : courseList) {
			accumulatedGradePoints += course.getGradePoint() * course.getCreditHours();
			accumulatedCredits += course.getCreditHours();
		}
		double gpa = accumulatedGradePoints / accumulatedCredits;
		return gpa;
	}
}
