package com.persequor.saga.service.model;

import java.util.Optional;

public class RequestResponseResult {
	private final Object responseBody;
	private final int httpCode;
	private final boolean systemAcceptsAndHandlesCorrectly;
	private String failReason;

	public RequestResponseResult(Object responseObject, int httpCode, boolean success) {
		this.responseBody = responseObject;
		this.httpCode = httpCode;
		this.systemAcceptsAndHandlesCorrectly = success;
	}

	public Object getResponseBody() {
		return responseBody;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public boolean isSystemAcceptsAndHandlesCorrectly() {
		return systemAcceptsAndHandlesCorrectly;
	}

	public Optional<String> getFailReason() {
		return Optional.ofNullable(failReason);
	}

	public RequestResponseResult setFailReason(String failReason) {
		this.failReason = failReason;
		return this;
	}
}
