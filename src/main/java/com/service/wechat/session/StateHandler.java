package com.service.wechat.session;

import java.util.concurrent.ConcurrentHashMap;

import com.service.wechat.message.factory.MessageHandlerFactory;
import com.service.wechat.message.handler.IMessageHandler;
import com.service.wechat.message.reply.IMessageReply;
import com.web.dao.entity.Message;

public class StateHandler {

	private IMessageHandler nextHandler = null;

	public StateHandler() {
	}

	public void setNextHandler(IMessageHandler handler) {
		this.nextHandler = handler;
	}

	public IMessageReply handleMessage(Message message) {
		IMessageHandler handler = nextHandler;
		nextHandler = null;
		// 使用默认的handler
		if (null == handler) {
			return defaultHandler(message).handleMessage(message, this);
		}
		// 使用状态机中的handler
		IMessageReply reply = handler.handleMessage(message, this);
		if (null != reply) {
			return reply;
		}
		// 如果状态机的handler不能处理，使用默认的handler处理
		return defaultHandler(message).handleMessage(message, this);
	}

	private IMessageHandler defaultHandler(Message message) {
		return MessageHandlerFactory.createMessageHandler(message);
	}

	private static ConcurrentHashMap<String, StateHandler> map = new ConcurrentHashMap<String, StateHandler>();

	public static StateHandler byUser(String openId) {
		if (!map.containsKey(openId)) {
			StateHandler handler = new StateHandler();
			map.put(openId, handler);
		}
		return map.get(openId);
	}
}
