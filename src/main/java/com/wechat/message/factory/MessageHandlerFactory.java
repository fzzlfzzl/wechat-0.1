package com.wechat.message.factory;

import com.web.dao.entity.Message;
import com.wechat.Const;
import com.wechat.message.handler.IMessageHandler;
import com.wechat.message.handler.impl.AddressMessageHandler;
import com.wechat.message.handler.impl.OrderMessageHandler;
import com.wechat.message.handler.impl.TextMessageHandler;

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
		} else if (OrderMessageHandler.EVENT_KEY.equals(eventKey)) {
			return new OrderMessageHandler();
		}
		throw new RuntimeException("Unknown Message");
	}

	private static IMessageHandler createTextMessageHandler(Message message) {
		return new TextMessageHandler();
	}
}
