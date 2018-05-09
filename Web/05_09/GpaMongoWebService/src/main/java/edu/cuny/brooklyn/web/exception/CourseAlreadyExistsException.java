package edu.cuny.brooklyn.web.exception;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND, reason = "Course already exists") // 302
public class CourseAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = -4513666896855642367L;

	public CourseAlreadyExistsException(String cid) {
		super("Course hash(" + Objects.hashCode(cid) + ") already exists");
	}
}
