package com.service.message.handler.impl;

import com.service.Const;
import com.service.message.handler.IMessageHandler;
import com.service.message.reply.IMessageReply;
import com.service.message.reply.impl.TextMessageReply;
import com.service.state.IUserState;
import com.web.dao.entity.Message;

public class TextMessageHandler implements IMessageHandler {

	@Override
	public IMessageReply handleMessage(Message message, IUserState state) {
		TextMessageReply reply = new TextMessageReply(message);
		reply.setContent(Const.RPY_NORMAL);
		return reply;
	}

}
