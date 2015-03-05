package com.service.wechat.message.factory;

import com.service.wechat.Const.Type;
import com.service.wechat.message.handler.IMessageHandler;
import com.service.wechat.message.handler.impl.AddressMessageHandler;
import com.service.wechat.message.handler.impl.OrderMessageHandler;
import com.service.wechat.message.handler.impl.TextMessageHandler;
import com.web.dao.entity.Message;

public class MessageHandlerFactory {

	public static IMessageHandler createMessageHandler(Message message) {
		String msgType = message.getMsgType();
		if (Type.TEXT.equals(msgType))
			return createTextMessageHandler(message);
		if (Type.EVENT.equals(msgType))
			return createEventMessageHandler(message);
		throw new RuntimeException("Unknown Message");
	}

	private static IMessageHandler createEventMessageHandler(Message message) {
		String event = message.getEvent();
		if (Type.CLICK.equals(event)) {
			return createClickEventMessageHandler(message);
		}
		throw new RuntimeException("Unknown Message");
	}

	private static IMessageHandler createClickEventMessageHandler(Message message) {
		String eventKey = message.getEventKey();
		if (AddressMessageHandler.EVENT_KEY.equals(eventKey)) {
			return new AddressMessageHandler();
		} else if (OrderMessageHandler.EVENT_KEY.equals(eventKey)) {
			return new OrderMessageHandler();
		}
		throw new RuntimeException("Unknown Message");
	}

	private static IMessageHandler createTextMessageHandler(Message message) {
		return new TextMessageHandler();
	}
}
