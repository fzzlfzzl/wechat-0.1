package com.service.wechat.message.handler.impl;

import com.service.wechat.Const;
import com.service.wechat.message.handler.IMessageHandler;
import com.service.wechat.message.reply.IMessageReply;
import com.service.wechat.message.reply.impl.TextMessageReply;
import com.service.wechat.session.StateHandler;
import com.web.dao.entity.Message;

public class TextMessageHandler implements IMessageHandler {

	@Override
	public IMessageReply handleMessage(Message message, StateHandler state) {
		TextMessageReply reply = new TextMessageReply(message);
		reply.setContent(Const.RES_NORMAL);
		return reply;
	}

}
