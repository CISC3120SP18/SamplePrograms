package edu.cuny.brooklyn.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import edu.cuny.brooklyn.web.exception.ApiErrorResponse;
import edu.cuny.brooklyn.web.exception.StudentAlreadyExistsException;
import edu.cuny.brooklyn.web.exception.StudentNoCourseException;
import edu.cuny.brooklyn.web.exception.StudentNotFoundException;

@RestControllerAdvice
public class ExceptionHandlingController {
	private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingController.class);

	@ExceptionHandler(value = { StudentNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorResponse handleStudentNotFoundException(Exception e, WebRequest r) {
		LOGGER.error("Student not found: " + e.getLocalizedMessage());
		return new ApiErrorResponse(HttpStatus.NOT_FOUND, e.getLocalizedMessage(), r.getSessionId());
	}	
	
	@ExceptionHandler(value = { StudentNoCourseException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorResponse handleStudentNoCourseException(Exception e, WebRequest r) {
		LOGGER.error("Student has no courses: " + e.getLocalizedMessage());
		return new ApiErrorResponse(HttpStatus.NOT_FOUND, e.getLocalizedMessage(), r.getSessionId());
	}
	
	@ExceptionHandler(value = { StudentAlreadyExistsException.class })
	@ResponseStatus(HttpStatus.FOUND)
	public ApiErrorResponse handleStudentAlreadyExistsException(Exception e, WebRequest r) {
		LOGGER.error("Student already exists: " + e.getLocalizedMessage());
		return new ApiErrorResponse(HttpStatus.NOT_FOUND, e.getLocalizedMessage(), r.getSessionId());
	}	
}
