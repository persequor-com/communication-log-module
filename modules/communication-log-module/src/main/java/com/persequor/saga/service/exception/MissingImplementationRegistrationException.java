package com.persequor.saga.service.exception;

public class MissingImplementationRegistrationException extends RuntimeException{
	public MissingImplementationRegistrationException(String message) {
		super(message);
	}
}
