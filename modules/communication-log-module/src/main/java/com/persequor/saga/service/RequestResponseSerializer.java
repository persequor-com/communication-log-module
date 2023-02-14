package com.persequor.saga.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.persequor.event.Model;
import com.persequor.saga.service.exception.RequestResponseSerializationException;
import com.persequor.xml.serialization.SerializationException;
import com.persequor.xml.serialization.SerializationFactory;

import javax.inject.Inject;

public class RequestResponseSerializer implements IRequestResponseSerializer {

	private final SerializationFactory serializationFactory;

	@Inject
	public RequestResponseSerializer(SerializationFactory serializationFactory) {
		this.serializationFactory = serializationFactory;
	}

	@Override
	public <Obj> String serializeObject(Obj obj) throws RequestResponseSerializationException {
		try {
			if (obj instanceof Model) {
				return serializationFactory.getXmlSerializer().serialize((Model) obj);
			} else {
				return serializationFactory.getJsonMapper().writeValueAsString(obj);
			}
		} catch (SerializationException | JsonProcessingException e) {
			throw new RequestResponseSerializationException(e);
		}
	}
}
