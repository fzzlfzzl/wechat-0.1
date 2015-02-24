package com.service.message.handler.impl;

import com.service.message.bean.IMessageBean;
import com.service.message.handler.IMessageHandler;
import com.service.message.reply.IMessageReply;
import com.service.message.reply.impl.TextMessageReply;

public class TextMessageHandler implements IMessageHandler {

	@Override
	public IMessageReply handleMessage(IMessageBean message) {
		TextMessageReply reply = new TextMessageReply(message);

		reply.setContent("你好," + message.getFromUserName());
		return reply;
	}
}
