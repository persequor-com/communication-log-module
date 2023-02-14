package com.persequor.saga.service.model;

import com.persequor.event.Event;
import com.persequor.saga.service.exception.FailedRequestException;
import com.persequor.saga.service.model.exception.RequestObjectConstructionException;

public interface IRequestSender<RequestObject> {

	/**
	 * Method is intended to create the object to send
	 * @param event
	 * @return
	 */
	RequestObject getObjectToSend(Event event) throws RequestObjectConstructionException;

	/**
	 * This method is intended for sending the event (without modification) to the external system and returning what is meaningful in terms of what was received
	 * @param object to be forwarded. This event is the one created in getObjectEvent-method.
	 * @return the object that should go into the response part of the UI
	 */
	RequestResponseResult send(RequestObject object) throws FailedRequestException;
	String getSystem();
}
