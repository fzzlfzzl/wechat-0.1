package com.service.message;

import com.service.message.reply.IMessageReply;

public interface IMessageHandler {

	public IMessageReply handleMessage(IMessage message);

}