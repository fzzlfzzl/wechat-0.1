package com.service.message.factory;

import com.service.message.IMessage;
import com.service.message.impl.TextMessage;
import com.util.XmlObject;

public class MessageFactory {

	public static IMessage createMessage(XmlObject req) {
		String msgType = req.get("MsgType").getText();
		if (IMessage.TYPE_TEXT.equals(msgType))
			return createTextMessage(req);
		if (IMessage.TYPE_EVENT.equals(msgType))
			return createEventMessage(req);
		throw new RuntimeException("Unknown Message");
	}

	private static IMessage createEventMessage(XmlObject req) {
		return null;
	}

	private static IMessage createTextMessage(XmlObject req) {
		TextMessage ret = new TextMessage();
		ret.setRequest(req);
		return ret;
	}
}
