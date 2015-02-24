package com.service.message.handler.impl;

import com.dao.entity.Message;
import com.dao.entity.User;
import com.dao.impl.UserDao;
import com.service.Constraint;
import com.service.menu.IMenu;
import com.service.message.handler.IClickEventMessageHandler;
import com.service.message.handler.IMenuMessageHandler;
import com.service.message.handler.IMessageHandler;
import com.service.message.reply.IMessageReply;
import com.service.message.reply.impl.TextMessageReply;

public class AddressClickEventMessageHandler implements IClickEventMessageHandler, IMenuMessageHandler {

	public static final String EVENT_KEY = Constraint.EVENT_KEY_ADDRESS;
	public static final String NAME = IMenu.NAME_ADDRESS;

	@Override
	public IMessageReply handleMessage(Message message) {
		User user = UserDao.load(message.getOpenId());
		if (user == null) {
			user = new User();
			user.setOpenId(message.getOpenId());
		}
		user.setLastMessage(message);
		UserDao.save(user);
		TextMessageReply reply = new TextMessageReply(message);
		reply.setContent(Constraint.STR_ADDR_CLK_EVNT_RPY);
		return reply;
	}

	@Override
	public String getEventKey() {
		return EVENT_KEY;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public IMessageHandler nextHandler() {
		return new AddressUpdateMessageHandler();
	}
}
