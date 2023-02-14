package com.persequor.saga.frontend;

import com.persequor.extension.frontend.Color;
import com.persequor.extension.frontend.Icon;
import com.persequor.extension.frontend.IconSize;
import com.persequor.saga.repository.model.RequestResponseLogEntry;


public class FrontendDisplayUtil {

	public static Icon getIconForRequestResponseLogEntry(RequestResponseLogEntry logEntry) {
		if (logEntry.isRequestAccepted()) {
			return Icon.fa.check_square().setColor(Color.green()).setSize(IconSize.L);
		}
		return Icon.fa.exclamation_square().setColor(Color.red()).setSize(IconSize.L);
	}

	public static String getTextForRequestResponseLogEntry(RequestResponseLogEntry logEntry) {
		if (logEntry.isRequestAccepted()) {
			return "Message was received correctly by external system";
		}
		return "Message was not received correctly by external system";
	}
}
