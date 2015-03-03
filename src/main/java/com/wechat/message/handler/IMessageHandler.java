package com.wechat.message.handler;

import com.web.dao.entity.Message;
import com.wechat.message.reply.IMessageReply;
import com.wechat.state.IUserState;

public interface IMessageHandler {

	public IMessageReply handleMessage(Message message, IUserState state);

}