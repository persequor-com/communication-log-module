package com.persequor.saga.frontend;

import javax.inject.Inject;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

public class MessageFormatter {

	private final TransformerFactory transformerFactory;
	private final Transformer transformer;


	@Inject
	public MessageFormatter() throws TransformerConfigurationException {
		transformerFactory = TransformerFactory.newInstance();
		transformerFactory.setAttribute("indent-number", 2);
		transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	}


	public String format(String request) {
		if (request.trim().startsWith("<")) {
			try {
				request = prettyFormat(request);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return request;
	}


	public String prettyFormat(String input) {
		try {
			Source xmlInput = new StreamSource(new StringReader(input));
			StringWriter stringWriter = new StringWriter();
			StreamResult xmlOutput = new StreamResult(stringWriter);
			transformer.transform(xmlInput, xmlOutput);
			return xmlOutput.getWriter().toString();
		} catch (Exception e) {
			return input;
		}
	}
}
