package com.service.message.handler;

import com.service.message.bean.IMessageBean;
import com.service.message.reply.IMessageReply;

public interface IMessageHandler {

	public IMessageReply handleMessage(IMessageBean message);

}