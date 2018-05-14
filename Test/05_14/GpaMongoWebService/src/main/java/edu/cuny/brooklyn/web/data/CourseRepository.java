package edu.cuny.brooklyn.web.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import edu.cuny.brooklyn.web.service.Course;

public interface CourseRepository extends CrudRepository<Course, String> {
	public double findByNumber(String number);

	public List<Course> findAll();

	public List<Course> findAllByStudentId(String studentId);

	public Optional<Course> findById(String courseId);
}
