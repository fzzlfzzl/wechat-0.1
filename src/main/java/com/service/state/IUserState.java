package com.service.state;

import com.dao.entity.Message;
import com.dao.entity.User;
import com.service.message.handler.IMessageHandler;
import com.service.message.reply.IMessageReply;

public interface IUserState {

	public void setNextHandler(IMessageHandler handler);

	public IMessageReply handleMessage(Message message);

	public User getUser();

	public void persist();

}
