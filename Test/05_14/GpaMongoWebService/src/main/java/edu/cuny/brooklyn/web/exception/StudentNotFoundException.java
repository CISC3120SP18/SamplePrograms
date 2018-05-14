package edu.cuny.brooklyn.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such student") // 404
public class StudentNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 2599507351936967393L;
	
	public StudentNotFoundException(String msg) {
		super(msg);
	}
}
