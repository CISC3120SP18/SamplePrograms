package edu.cuny.brooklyn.web.exception;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND, reason = "Student already exists") // 302
public class StudentAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = -4513666896855642367L;

	public StudentAlreadyExistsException(String sid) {
		super("Student hash(" + Objects.hashCode(sid) + ") already exists");
	}
}
