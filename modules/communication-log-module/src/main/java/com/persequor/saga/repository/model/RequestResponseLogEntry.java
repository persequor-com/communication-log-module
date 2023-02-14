package com.persequor.saga.repository.model;

import com.persequor.saga.repository.CommunicationLogModuleSqlDbConfig;
import com.valqueries.automapper.MappedType;
import io.ran.Key;
import io.ran.Mapper;
import io.ran.PrimaryKey;

import java.time.ZonedDateTime;
import java.util.UUID;

@Mapper(dbType = CommunicationLogModuleSqlDbConfig.class)
public class RequestResponseLogEntry {
	@PrimaryKey
	private String id;
	@PrimaryKey
	private String context;
	@PrimaryKey
	private UUID logId;
	@MappedType("TEXT")
	private String request;
	@Key(name = "request_time_idx")
	private ZonedDateTime requestTime;
	@MappedType("TEXT")
	private String response;
	private ZonedDateTime responseTime;
	private String messageType;
	private boolean requestAccepted;

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public ZonedDateTime getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(ZonedDateTime requestTime) {
		this.requestTime = requestTime;
	}

	public ZonedDateTime getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(ZonedDateTime responseTime) {
		this.responseTime = responseTime;
	}

	public UUID getLogId() {
		return logId;
	}

	public void setLogId(UUID logId) {
		this.logId = logId;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public boolean isRequestAccepted() {
		return requestAccepted;
	}

	public void setRequestAccepted(boolean requestAccepted) {
		this.requestAccepted = requestAccepted;
	}
}
