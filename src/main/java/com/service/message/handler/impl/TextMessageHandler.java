package com.service.message.handler.impl;

import com.service.message.IMessage;
import com.service.message.IMessageHandler;
import com.service.message.reply.IMessageReply;
import com.service.message.reply.impl.TextMessageReply;

public class TextMessageHandler implements IMessageHandler {

	@Override
	public IMessageReply handleMessage(IMessage message) {
		TextMessageReply reply = new TextMessageReply(message);
		
		reply.setContent("你好," + message.getFromUserName());
		return reply;
	}
}
