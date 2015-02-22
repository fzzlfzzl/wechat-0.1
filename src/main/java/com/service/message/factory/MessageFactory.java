package com.service.message.factory;

import com.service.message.IMessage;
import com.service.message.impl.AddressClickEventMessage;
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
		String event = req.get("Event").getText();
		if (IMessage.TYPE_CLICK.equals(event)) {
			return createClickEventMessage(req);
		}
		throw new RuntimeException("Unknown Message");
	}

	private static IMessage createClickEventMessage(XmlObject req) {
		String eventKey = req.get("EventKey").getText();
		if (AddressClickEventMessage.EVENT_KEY.equals(eventKey)) {
			return new AddressClickEventMessage(req);
		}
		throw new RuntimeException("Unknown Message");
	}

	private static IMessage createTextMessage(XmlObject req) {
		TextMessage ret = new TextMessage(req);
		return ret;
	}
}
