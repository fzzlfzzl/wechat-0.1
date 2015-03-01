package com.service.message.handler;

import com.service.message.reply.IMessageReply;
import com.service.state.IUserState;
import com.web.dao.entity.Message;

public interface IMessageHandler {

	public IMessageReply handleMessage(Message message, IUserState state);

}