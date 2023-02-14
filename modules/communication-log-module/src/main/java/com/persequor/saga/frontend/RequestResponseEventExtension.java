package com.persequor.saga.frontend;

import com.persequor.event.Event;
import com.persequor.extension.frontend.IEventDataConfiguration;
import com.persequor.extension.frontend.IEventDataExtension;
import com.persequor.extension.frontend.link.ExtensionLink;
import com.persequor.saga.service.IRequestResponseLogService;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class RequestResponseEventExtension implements IEventDataExtension {
	private final IRequestResponseLogService requestResponseLogService;

	@Inject
	public RequestResponseEventExtension(IRequestResponseLogService requestResponseLogService) {
		this.requestResponseLogService = requestResponseLogService;
	}

	@Override
	public void configure(Event event, IEventDataConfiguration configuration) {
		List<String> ids = Collections.singletonList(event.getSagaEventId().toString());
		requestResponseLogService.getForIds(ids, 0, 1).forEach(logEntry -> {
					configuration.section("Request/Response", section -> {
						section.setOrder(1);
						section.row(logEntry.getContext() + "(" + logEntry.getMessageType() + ")", content -> {
							content.icon(FrontendDisplayUtil.getIconForRequestResponseLogEntry(logEntry))
									.setText(FrontendDisplayUtil.getTextForRequestResponseLogEntry(logEntry))
									.setLink(ExtensionLink.pageBuilder(RequestResponseDetailsFrontend.FRONTEND_PATH)
											.addParameter(RequestResponseDetailsFrontend.PARAM_ID, logEntry.getId()));
						});
					});
				}
		);
	}

}
