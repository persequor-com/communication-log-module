package com.persequor.saga;

import com.persequor.extension.ioc.IExtensionPointConfiguration;
import com.persequor.extension.ioc.IExtensionPointIoc;
import com.persequor.saga.frontend.RequestResponseDetailsFrontend;
import com.persequor.saga.frontend.RequestResponseEventExtension;
import com.persequor.saga.repository.IRequestResponseRepository;
import com.persequor.saga.repository.RequestResponseLogEntrySqlRepository;
import com.persequor.saga.repository.migration.CommunicationLogModuleSql9999999999Database;
import com.persequor.saga.service.*;

public class CommunicationLogConfiguration implements IExtensionPointConfiguration {
	@Override
	public void configure(IExtensionPointIoc baseIoc) {
		baseIoc.db().addMigrationScriptsPackage(CommunicationLogModuleSql9999999999Database.class.getPackage());

		baseIoc.frontend().component().add(RequestResponseDetailsFrontend.class);
		baseIoc.frontend().eventExtension().add(RequestResponseEventExtension.class);

		//potentially overridden in future
		baseIoc.bind(IRequestResponseRepository.class).to(RequestResponseLogEntrySqlRepository.class);
		baseIoc.bind(IRequestResponseService.class).to(RequestResponseService.class);
		baseIoc.bind(IRequestResponseSerializer.class).to(RequestResponseSerializer.class);
		baseIoc.bind(IRequestResponseLogService.class).to(RequestResponseLogService.class);
	}
}
