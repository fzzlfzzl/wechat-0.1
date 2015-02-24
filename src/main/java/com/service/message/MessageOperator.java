package com.service.message;

import com.service.message.handler.IMessageHandler;

public class MessageOperator {

	private IMessageHandler handler;

	public IMessageHandler getHandler() {
		return handler;
	}

	public void setHandler(IMessageHandler handler) {
		this.handler = handler;
	}
}
