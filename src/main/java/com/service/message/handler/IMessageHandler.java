package com.service.message.handler;

import com.dao.entity.Message;
import com.service.message.reply.IMessageReply;
import com.service.state.IUserState;

public interface IMessageHandler {

	public IMessageReply handleMessage(Message message, IUserState state);

}