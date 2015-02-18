package com.service.message.factory;

import org.apache.log4j.Logger;

import com.service.message.IMessageHandler;
import com.service.message.base.MessageHandler;
import com.service.message.impl.EventMessageHandler;
import com.service.message.impl.TextMessageHandler;

public class MessageHandlerFactory {

	private static Logger logger = Logger.getLogger(MessageHandler.class);

	public static MessageHandler[] handlers = { new TextMessageHandler(),
			new EventMessageHandler() };

	public static IMessageHandler getHandler(String type) {
		for (MessageHandler handler : handlers) {
			if (handler.getMsgType().equals(type)) {
				return handler;
			}
		}
		logger.warn("Invalid Type: " + type);
		return null;
	}
}
