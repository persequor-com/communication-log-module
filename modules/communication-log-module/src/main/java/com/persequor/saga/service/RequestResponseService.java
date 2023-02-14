package com.persequor.saga.service;

import com.persequor.saga.repository.IRequestResponseRepository;
import com.persequor.saga.repository.model.RequestResponseLogEntry;
import com.persequor.saga.service.exception.FailedRequestException;
import com.persequor.saga.service.model.Request;
import com.persequor.saga.service.model.RequestResponseResult;
import com.persequor.saga.service.model.IRequestSender;
import com.persequor.saga.service.model.exception.RequestObjectConstructionException;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.UUID;

public class RequestResponseService implements IRequestResponseService {

	private final SenderFactory senderFactory;
	private final IRequestResponseRepository requestResponseRepository;
	private final IRequestResponseSerializer communicationSerializer;

	@Inject
	public RequestResponseService(SenderFactory senderFactory,
								  IRequestResponseRepository requestResponseRepository,
								  IRequestResponseSerializer communicationSerializer) {
		this.senderFactory = senderFactory;
		this.requestResponseRepository = requestResponseRepository;
		this.communicationSerializer = communicationSerializer;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public RequestResponseResult sendWithRequestResponseLogging(Request requestDetails) throws FailedRequestException, RequestObjectConstructionException {
		IRequestSender sender = getSender(requestDetails);

		Object request = sender.getObjectToSend(requestDetails.getEvent());
		String serializedRequestObject;
		serializedRequestObject = communicationSerializer.serializeObject(request);

		RequestResponseLogEntry logEntry = getLogEntry(
				requestDetails.getId(),
				requestDetails.getContext(),
				serializedRequestObject,
				sender.getSystem(),
				UUID.randomUUID());
		//step 1. log message:
		requestResponseRepository.save(logEntry);
		try {
				//step 2. send message:
				RequestResponseResult response = sender.send(request);
				//step 3 save response and result
				logEntry.setResponse(communicationSerializer.serializeObject(response));
				logEntry.setRequestAccepted(response.isSystemAcceptsAndHandlesCorrectly());
				requestResponseRepository.save(logEntry);
				return response;
			} catch (FailedRequestException e){
				logEntry.setResponse(e.getMessage());
				logEntry.setRequestAccepted(false);
				requestResponseRepository.save(logEntry);
				throw e;
			}
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private IRequestSender getSender(Request requestDetails) {
		return senderFactory.get(requestDetails.getSenderClass());
	}

	private RequestResponseResult returnFailedResponse(String reason) {
		return new RequestResponseResult(null, 0, false)
				.setFailReason(reason);
	}

	private RequestResponseLogEntry getLogEntry(String eventId, String requestType, String serializedEvent, String system, UUID logId) {
		RequestResponseLogEntry model = new RequestResponseLogEntry();
		model.setLogId(logId);
		model.setId(eventId);
		model.setRequest(serializedEvent);
		model.setContext(system);
		model.setRequestTime(ZonedDateTime.now());
		model.setMessageType(requestType);
		return model;
	}
}
