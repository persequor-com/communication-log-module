package com.persequor.saga.service.exception;

public class RequestResponseSerializationException extends RuntimeException {
	public RequestResponseSerializationException(Exception e) {
		super(e.getMessage());
	}
}
