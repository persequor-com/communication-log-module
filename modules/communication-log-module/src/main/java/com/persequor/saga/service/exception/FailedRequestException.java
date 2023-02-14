package com.persequor.saga.service.exception;

public class FailedRequestException extends Exception{
	public FailedRequestException(Exception e) {
		super(e);
	}
}
