package com.wechat.state;

import com.web.dao.entity.Message;
import com.web.dao.entity.User;
import com.web.dao.impl.UserDao;
import com.wechat.message.factory.MessageHandlerFactory;
import com.wechat.message.handler.IMessageHandler;
import com.wechat.message.reply.IMessageReply;

public class UserState implements IUserState {

	private IMessageHandler nextHandler = null;
	private User user = null;

	public UserState(User user) {
		this.user = user;
	}

	@Override
	public void setNextHandler(IMessageHandler handler) {
		this.nextHandler = handler;
	}

	@Override
	public IMessageReply handleMessage(Message message) {
		logMessage(message);
		IMessageHandler handler = nextHandler;
		nextHandler = null;
		// 使用默认的handler
		if (null == handler) {
			return defaultHandler(message).handleMessage(message, this);
		}
		// 使用状态机中的handler
		IMessageReply reply = handler.handleMessage(message, this);
		if (null != reply) {
			return reply;
		}
		// 如果状态机的handler不能处理，使用默认的handler处理
		return defaultHandler(message).handleMessage(message, this);
	}

	private void logMessage(Message message) {
		user.getMessages().add(message);
		persist();
	}

	private IMessageHandler defaultHandler(Message message) {
		return MessageHandlerFactory.createMessageHandler(message);
	}

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public void persist() {
		UserDao.save(user);
	}
}
