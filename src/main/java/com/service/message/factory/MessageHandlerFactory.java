package com.service.message.factory;

import com.service.Const;
import com.service.message.handler.IMessageHandler;
import com.service.message.handler.impl.AddressMessageHandler;
import com.service.message.handler.impl.TextMessageHandler;
import com.web.dao.entity.Message;

public class MessageHandlerFactory {

	public static IMessageHandler createMessageHandler(Message message) {
		String msgType = message.getMsgType();
		if (Const.TYPE_TEXT.equals(msgType))
			return createTextMessageHandler(message);
		if (Const.TYPE_EVENT.equals(msgType))
			return createEventMessageHandler(message);
		throw new RuntimeException("Unknown Message");
	}

	private static IMessageHandler createEventMessageHandler(Message message) {
		String event = message.getEvent();
		if (Const.TYPE_CLICK.equals(event)) {
			return createClickEventMessageHandler(message);
		}
		throw new RuntimeException("Unknown Message");
	}

	private static IMessageHandler createClickEventMessageHandler(Message message) {
		String eventKey = message.getEventKey();
		if (AddressMessageHandler.EVENT_KEY.equals(eventKey)) {
			return new AddressMessageHandler();
		}
		throw new RuntimeException("Unknown Message");
	}

	private static IMessageHandler createTextMessageHandler(Message message) {
		return new TextMessageHandler();
	}
}
