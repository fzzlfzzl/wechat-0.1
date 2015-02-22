package com.service.message.impl;

import com.service.message.IMessageHandler;
import com.service.message.handler.impl.AddressClickEventMessageHandler;
import com.util.XmlObject;

public class AddressClickEventMessage extends ClickEventMessage {

	public static final String NAME = "地址";
	public static final String EVENT_KEY = "ADDRESS";

	private static IMessageHandler HANDLER = new AddressClickEventMessageHandler();

	public AddressClickEventMessage(XmlObject req) {
		super(req);
	}

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
