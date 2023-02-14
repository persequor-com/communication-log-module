package com.persequor.saga.service;

import com.persequor.saga.service.exception.RequestResponseSerializationException;

public interface IRequestResponseSerializer {
	public <Obj> String serializeObject(Obj obj) throws RequestResponseSerializationException;

}
