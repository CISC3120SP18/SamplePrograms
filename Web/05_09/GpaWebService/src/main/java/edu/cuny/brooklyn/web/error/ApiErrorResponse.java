package edu.cuny.brooklyn.web.error;

import org.springframework.http.HttpStatus;

public class ApiErrorResponse {
	private int errorCode;
	private HttpStatus httpStatus;
	private String message;
	private String session;
	
	public ApiErrorResponse(int errorCode, HttpStatus status, String localizedMessage, String sessionId) {
		this.errorCode = errorCode;
		this.httpStatus = status;
		this.message = localizedMessage;
		this.session = sessionId;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public String getSession() {
		return session;
	}

}
