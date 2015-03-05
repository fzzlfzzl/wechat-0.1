package com.wechat.session;

import com.web.dao.entity.Message;
import com.wechat.message.factory.MessageHandlerFactory;
import com.wechat.message.handler.IMessageHandler;
import com.wechat.message.reply.IMessageReply;

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

}
