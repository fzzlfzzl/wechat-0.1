package com.service.message.factory;

import com.service.message.bean.IMessageBean;
import com.service.message.handler.IMessageHandler;
import com.service.message.handler.impl.AddressClickEventMessageHandler;
import com.service.message.handler.impl.TextMessageHandler;

public class MessageHandlerFactory {

	public static IMessageHandler createMessageHandler(IMessageBean message) {
		String msgType = message.getMsgType();
		if (IMessageBean.TYPE_TEXT.equals(msgType))
			return createTextMessageHandler(message);
		if (IMessageBean.TYPE_EVENT.equals(msgType))
			return createEventMessageHandler(message);
		throw new RuntimeException("Unknown Message");
	}

	private static IMessageHandler createEventMessageHandler(IMessageBean message) {
		String event = message.getEvent();
		if (IMessageBean.TYPE_CLICK.equals(event)) {
			return createClickEventMessageHandler(message);
		}
		throw new RuntimeException("Unknown Message");
	}

	private static IMessageHandler createClickEventMessageHandler(IMessageBean message) {
		String eventKey = message.getEventKey();
		if (AddressClickEventMessageHandler.EVENT_KEY.equals(eventKey)) {
			return new AddressClickEventMessageHandler();
		}
		throw new RuntimeException("Unknown Message");
	}

	private static IMessageHandler createTextMessageHandler(IMessageBean message) {
		return new TextMessageHandler();
	}
}
