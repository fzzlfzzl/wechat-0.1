package com.service.state;

import com.service.message.handler.IMessageHandler;
import com.service.message.reply.IMessageReply;
import com.web.dao.entity.Message;
import com.web.dao.entity.User;

public interface IUserState {

	public void setNextHandler(IMessageHandler handler);

	public IMessageReply handleMessage(Message message);

	public User getUser();

	public void persist();

}
