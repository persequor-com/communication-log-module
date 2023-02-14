package com.persequor.saga.frontend;

import com.persequor.extension.frontend.*;
import com.persequor.extension.frontend.data.IDateTimeData;
import com.persequor.extension.frontend.link.ExtensionLink;
import com.persequor.saga.service.IRequestResponseLogService;

import javax.inject.Inject;
import java.util.Collections;

public class RequestResponseDetailsFrontend implements IFrontendComponent {
	public static final String FRONTEND_PATH = "compliance-details";
	private static final String FRONTEND_TITLE = "Request Response details";
	public static final String PARAM_ID = "ID";

	private final MessageFormatter formatter;
	private final IRequestResponseLogService requestResponseLogService;

	@Inject
	public RequestResponseDetailsFrontend(MessageFormatter messageFormatter, IRequestResponseLogService requestResponseLogService) {
		this.formatter = messageFormatter;
		this.requestResponseLogService = requestResponseLogService;
	}

	@Override
	public String getPagePath() {
		return FRONTEND_PATH;
	}

	@Override
	public void configure(ConfigurerDetails configurer) {
		// No need to configure anything for this page.
	}

	@Override
	public void handle(IPage page, IInputData inputData) {
		page.title(FRONTEND_TITLE);
		String id = inputData.getString(PARAM_ID).orElseThrow();

		page.table(inputData, table -> {
					table.name("request response overview");
					int limit = 10;
					int offset = table.getOffset();
					table.serverSidePagination();
					table.limit(limit);
					//TODO find event relating to??
					requestResponseLogService.getForIds(Collections.singletonList(id), offset, limit).forEach(logEntry ->
							table.addRow(r -> {
								r.addDateTimeCell("Date", logEntry.getRequestTime(), IDateTimeData.DateTimeDataTimezone.local);
								r.addTextCell("Context", logEntry.getContext());
								r.addTextCell("Message Type", logEntry.getMessageType());

								/*r.addCell("Saga Event Id", cell -> cell.text(reply.getSagaEventId().toString())
										.setLink(ExtensionLink.event(reply.getSagaEventId())));*/
								r.addTextCell("Id", logEntry.getId());
								r.addCell("Status",
										cell -> {
												Icon icon = FrontendDisplayUtil.getIconForRequestResponseLogEntry(logEntry);
												String text = FrontendDisplayUtil.getTextForRequestResponseLogEntry(logEntry);
												cell.icon(icon).setTooltip(I18nText.of(text));

										}
								);
								r.addCell("Request Log", c ->
										c.iconButton(Icon.fa.arrow_right().setColor(Color.blue()), cropText(logEntry.getRequest()))
												.setLink(ExtensionLink.dialog("Request Log", formatter.prettyFormat(logEntry.getRequest()))));
								r.addCell("Response Log", c ->
										c.iconButton(Icon.fa.arrow_left().setColor(Color.blue()), cropText(logEntry.getResponse()))
												.setLink(ExtensionLink.dialog("Response Log", formatter.format(logEntry.getResponse())))
								);


							})
					);

				}
		);

	}
	private String cropText(String text) {
		if (text == null || text.length() < 30) {
			return text;
		}
		return text.substring(0, 27) + "...";
	}
}
