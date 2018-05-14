package edu.cuny.brooklyn.web.exception;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No taken any courses or no such course") // 404
public class StudentNoCourseException extends RuntimeException {
	private static final long serialVersionUID = 4917383726997688691L;

	public StudentNoCourseException(String sid) {
		super("Student hash(" + Objects.hashCode(sid) 
			+ ") has not taken any classes or does not have such a course");
	}
}
