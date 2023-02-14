package com.persequor.saga.service.model;

import com.persequor.event.Event;

public class Request<Sender extends IRequestSender> {

	//sender-class
	private Class<Sender> senderClass;
	private Event event;
	private String context;

	private String id;

	public Class<Sender> getSenderClass() {
		return senderClass;
	}

	public void setSenderClass(Class<Sender> senderClass) {
		this.senderClass = senderClass;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	//request-object

}
