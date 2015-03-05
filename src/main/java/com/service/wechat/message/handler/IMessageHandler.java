package com.service.wechat.message.handler;

import com.service.wechat.message.reply.IMessageReply;
import com.service.wechat.session.StateHandler;
import com.web.dao.entity.Message;

public interface IMessageHandler {

	public IMessageReply handleMessage(Message message, StateHandler state);

}