package com.persequor.saga;

import com.persequor.extension.ExtensionInfo;
import com.persequor.extension.ioc.module.ExtensionModule;
import com.persequor.extension.ioc.module.IModuleSubConfiguration;

@ExtensionInfo(
		name = "Request Response Module",
		description = "Module for providing visualisation of outgoing communication and responses from other systems."
)
public class CommunicationLogModule extends ExtensionModule {

	public CommunicationLogContext context() {
		return this.getExtensionContext(CommunicationLogContext.class);
	}

	public IModuleSubConfiguration defaultConfiguration() {
		return this.getSubConfigurationContext(CommunicationLogConfiguration.class);
	}

	@Override
	public void defaultModuleConfiguration() {
		this.defaultConfiguration().require();
	}


}
