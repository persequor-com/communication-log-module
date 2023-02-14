package com.persequor.saga.service;

import com.persequor.saga.repository.IRequestResponseRepository;
import com.persequor.saga.repository.model.RequestResponseLogEntry;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Stream;

public class RequestResponseLogService implements IRequestResponseLogService{

	private final IRequestResponseRepository repository;

	@Inject
	public RequestResponseLogService(IRequestResponseRepository repository) {
		this.repository = repository;
	}

	@Override
	public Stream<RequestResponseLogEntry> getForIds(List<String> ids, int offset, int limit) {
		return repository.getForIds(ids, offset, limit);
	}
}
