package edu.cuny.brooklyn.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "bad request") // 404
public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = 2599507351936967393L;
	
	public BadRequestException(String msg) {
		super(msg);
	}
}
