package com.service.message.handler.impl;

import com.service.message.IMessage;
import com.service.message.IMessageHandler;
import com.service.message.reply.IMessageReply;
import com.service.message.reply.impl.TextMessageReply;

public class AddressClickEventMessageHandler implements IMessageHandler {

	@Override
	public IMessageReply handleMessage(IMessage message) {
		TextMessageReply reply = new TextMessageReply(message);
		reply.setContent("请输入地址");
		return reply;
	}
}
