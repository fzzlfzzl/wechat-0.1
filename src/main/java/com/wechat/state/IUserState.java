package com.wechat.state;

import com.web.dao.entity.Message;
import com.web.dao.entity.User;
import com.wechat.message.handler.IMessageHandler;
import com.wechat.message.reply.IMessageReply;

public interface IUserState {

	public void setNextHandler(IMessageHandler handler);

	public IMessageReply handleMessage(Message message);

	public User getUser();

	public void persist();

}
