package com.service.message.impl;

import com.service.message.IMessageHandler;
import com.service.message.handler.impl.AddressClickEventMessageHandler;

public class AddressClickEventMessage extends ClickEventMessage {

	private static String NAME = "地址";
	private static String EVENT_KEY = "ADDRESS";
	private static IMessageHandler HANDLER = new AddressClickEventMessageHandler();

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getEventKey() {
		return EVENT_KEY;
	}

	@Override
	public IMessageHandler getHandler() {
		return HANDLER;
	}

}
