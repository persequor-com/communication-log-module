package com.persequor.saga.repository;

import com.persequor.saga.repository.model.RequestResponseLogEntry;
import com.valqueries.automapper.ValqueriesCrudRepository;
import io.ran.CompoundKey;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public interface IRequestResponseRepository extends ValqueriesCrudRepository<RequestResponseLogEntry, CompoundKey> {

	List<RequestResponseLogEntry> findLogEntries(UUID id, String complianceRepository);
	void cleanComplianceLogEntriesRequestedBefore(ZonedDateTime cutOff);
	void store(RequestResponseLogEntry logEntry);

	Stream<RequestResponseLogEntry> getForIds(List<String> ids, int offset, int limit);
}
