package com.persequor.saga.service;

import com.persequor.saga.repository.model.RequestResponseLogEntry;

import java.util.List;
import java.util.stream.Stream;

public interface IRequestResponseLogService {

	Stream<RequestResponseLogEntry> getForIds(List<String> id, int offset, int limit);
}
