package com.service.message.impl;

import com.service.message.IMessage;
import com.service.message.IMessageHandler;
import com.service.message.ITextMessage;
import com.service.message.handler.impl.TextMessageHandler;
import com.util.XmlObject;

public class TextMessage extends Message implements ITextMessage {

	private static IMessageHandler handler = new TextMessageHandler();

	public TextMessage(XmlObject req) {
		super(req);
	}

	@Override
	public String getContent() {
		return req.get("text").getText();
	}

	@Override
	public String getMsgType() {
		return IMessage.TYPE_TEXT;
	}

	@Override
	public IMessageHandler getHandler() {
		return handler;
	}

}
