package com.persequor.saga.service;

import com.persequor.saga.service.exception.FailedRequestException;
import com.persequor.saga.service.model.RequestResponseResult;
import com.persequor.saga.service.model.Request;
import com.persequor.saga.service.model.exception.RequestObjectConstructionException;

public interface IRequestResponseService {
	RequestResponseResult sendWithRequestResponseLogging(Request requestDetails) throws FailedRequestException, RequestObjectConstructionException;
}
