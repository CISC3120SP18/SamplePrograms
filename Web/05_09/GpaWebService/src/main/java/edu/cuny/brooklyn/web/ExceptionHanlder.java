package edu.cuny.brooklyn.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import edu.cuny.brooklyn.web.error.ApiErrorResponse;
import edu.cuny.brooklyn.web.error.StudentNotFoundException;

@RestControllerAdvice
public class ExceptionHanlder {
	@ExceptionHandler(value = { StudentNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorResponse unknownException(Exception e, WebRequest r) {
		return new ApiErrorResponse(404, HttpStatus.NOT_FOUND, e.getLocalizedMessage(), r.getSessionId());
	}
}
