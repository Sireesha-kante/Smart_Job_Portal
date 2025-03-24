package com.job.jobservice.exception;

public class JobAlreadyExistsException extends RuntimeException {
	
	public JobAlreadyExistsException(String message) {
		super(message);
	}
}
