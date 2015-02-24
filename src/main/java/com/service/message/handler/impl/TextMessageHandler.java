package com.service.message.handler.impl;

import com.dao.entity.Message;
import com.dao.entity.User;
import com.dao.impl.UserDao;
import com.service.message.handler.IMessageHandler;
import com.service.message.reply.IMessageReply;
import com.service.message.reply.impl.TextMessageReply;
import com.util.Util;

public class TextMessageHandler implements IMessageHandler {

	@Override
	public IMessageReply handleMessage(Message message) {
		TextMessageReply reply = new TextMessageReply(message);
		User user = UserDao.load(message.getOpenId());
		if (user == null || Util.isNullOrEmpty(user.getAddress())) {
			reply.setContent("未设置地址");
		} else {
			reply.setContent("地址是" + user.getAddress());
		}
		return reply;
	}

	@Override
	public IMessageHandler nextHandler() {
		return null;
	}
}
