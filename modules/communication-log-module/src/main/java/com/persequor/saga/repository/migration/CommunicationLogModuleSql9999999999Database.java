package com.persequor.saga.repository.migration;

import com.persequor.extension.migration.ISandboxMigration;
import com.persequor.extension.migration.migrator.SandboxMigrator;
import com.persequor.saga.repository.CommunicationLogModuleSqlDbConfig;

public class CommunicationLogModuleSql9999999999Database implements ISandboxMigration {
	@Override
	public void up(SandboxMigrator migrator) throws Exception {
		migrator.sqlAdmin().createDatabaseAndUser(CommunicationLogModuleSqlDbConfig.class);
	}
}
