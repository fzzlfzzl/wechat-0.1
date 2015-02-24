package com.service.message.factory;

import com.dao.entity.Message;
import com.service.Constraint;
import com.service.message.handler.IMessageHandler;
import com.service.message.handler.impl.AddressClickEventMessageHandler;
import com.service.message.handler.impl.TextMessageHandler;

public class MessageHandlerFactory {

	public static IMessageHandler createMessageHandler(Message message) {
		String msgType = message.getMsgType();
		if (Constraint.TYPE_TEXT.equals(msgType))
			return createTextMessageHandler(message);
		if (Constraint.TYPE_EVENT.equals(msgType))
			return createEventMessageHandler(message);
		throw new RuntimeException("Unknown Message");
	}

	private static IMessageHandler createEventMessageHandler(Message message) {
		String event = message.getEvent();
		if (Constraint.TYPE_CLICK.equals(event)) {
			return createClickEventMessageHandler(message);
		}
		throw new RuntimeException("Unknown Message");
	}

	private static IMessageHandler createClickEventMessageHandler(Message message) {
		String eventKey = message.getEventKey();
		if (AddressClickEventMessageHandler.EVENT_KEY.equals(eventKey)) {
			return new AddressClickEventMessageHandler();
		}
		throw new RuntimeException("Unknown Message");
	}

	private static IMessageHandler createTextMessageHandler(Message message) {
		return new TextMessageHandler();
	}
}
