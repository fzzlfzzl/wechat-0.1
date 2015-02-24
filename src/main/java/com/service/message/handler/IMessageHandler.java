package com.service.message.handler;

import com.dao.entity.Message;
import com.service.message.reply.IMessageReply;

public interface IMessageHandler {

	public IMessageReply handleMessage(Message message);

	public IMessageHandler nextHandler();

}