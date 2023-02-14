package com.persequor.saga.repository;

import com.persequor.saga.repository.model.RequestResponseLogEntry;
import com.valqueries.automapper.ValqueriesCrudRepositoryImpl;
import com.valqueries.automapper.ValqueriesRepositoryFactory;
import io.ran.CompoundKey;

import javax.inject.Inject;
import java.time.ZonedDateTime;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CREATE TABLE IF NOT EXISTS request_response_log_entry (
 * 	 `id` CHAR(36) CHARACTER SET latin1,
 * 	 `context` VARCHAR(255),
 * 	 `log_id` CHAR(36) CHARACTER SET latin1,
 * 	 `request` TEXT,
 * 	 `request_time` DATETIME,
 * 	 `response` TEXT,
 * 	 `response_time` DATETIME,
 * 	 `message_type` VARCHAR(255),
 * 	 PRIMARY KEY(`id`, `context`, `log_id`),
 * 	 INDEX `request_time_idx` (`request_time`)
 * 	)
 */
public class RequestResponseLogEntrySqlRepository extends ValqueriesCrudRepositoryImpl<RequestResponseLogEntry, CompoundKey> implements IRequestResponseRepository {

	@Inject
	public RequestResponseLogEntrySqlRepository(ValqueriesRepositoryFactory factory) {
		super(factory, RequestResponseLogEntry.class, CompoundKey.class);
	}

	public List<RequestResponseLogEntry> findLogEntries(UUID sagaEventId, String complianceRepository) {
		return query()
				.eq(RequestResponseLogEntry::getId, sagaEventId)
				.eq(RequestResponseLogEntry::getContext, complianceRepository)
				.execute()
				.collect(Collectors.toList());
	}

	@Override
	public void store(RequestResponseLogEntry logEntry) {
		Objects.requireNonNull(logEntry);

		super.save(logEntry);
	}

	@Override
	public Stream<RequestResponseLogEntry> getForIds(List<String> ids, int offset, int limit) {
		return query()
				.in(RequestResponseLogEntry::getId, ids)
				.limit(offset, limit)
				.sortDescending(RequestResponseLogEntry::getRequestTime)
				.execute();
	}

	@Override
	public void cleanComplianceLogEntriesRequestedBefore(ZonedDateTime cutOff) {
		this.query()
				.lt(RequestResponseLogEntry::getRequestTime, cutOff)
				.delete();
	}
}
