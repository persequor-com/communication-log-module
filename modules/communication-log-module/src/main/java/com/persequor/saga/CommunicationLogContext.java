package com.persequor.saga;

import com.persequor.extension.ioc.IMultipleImplementationsBinder;
import com.persequor.extension.ioc.registry.ModuleExtensionContext;
import com.persequor.saga.service.model.IRequestSender;

public class CommunicationLogContext extends ModuleExtensionContext {


	@SuppressWarnings("rawtypes")
	public IMultipleImplementationsBinder<IRequestSender> requestSender(){
		return getMultipleImplementationsBinder(IRequestSender.class);
	}

}
