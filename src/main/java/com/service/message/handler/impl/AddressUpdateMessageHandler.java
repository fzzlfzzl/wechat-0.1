package com.service.message.handler.impl;

import com.dao.entity.Message;
import com.dao.entity.User;
import com.dao.impl.UserDao;
import com.service.Constraint;
import com.service.message.handler.IMessageHandler;
import com.service.message.reply.IMessageReply;
import com.service.message.reply.impl.TextMessageReply;
import com.util.LoggerHelper;

public class AddressUpdateMessageHandler implements IMessageHandler {

	@Override
	public IMessageReply handleMessage(Message message) {
		if (!message.getMsgType().equals(Constraint.TYPE_TEXT)) {
			return null;
		}
		User user = UserDao.load(message.getOpenId());
		if (null == user) {
			LoggerHelper.error("Impossible");
			return null;
		}
		user.setAddress(message.getContent());
		UserDao.save(user);
		TextMessageReply reply = new TextMessageReply(message);
		reply.setContent("地址已保存");
		return reply;
	}

	@Override
	public IMessageHandler nextHandler() {
		return null;
	}

}
