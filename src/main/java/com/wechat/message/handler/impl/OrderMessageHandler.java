package com.wechat.message.handler.impl;

import com.site.Config;
import com.web.dao.entity.Message;
import com.wechat.Const;
import com.wechat.menu.IMenu;
import com.wechat.message.handler.IClickEventMessageHandler;
import com.wechat.message.handler.IMenuMessageHandler;
import com.wechat.message.reply.IMessageReply;
import com.wechat.message.reply.impl.UserRedirectMessageReply;
import com.wechat.state.IUserState;

public class OrderMessageHandler implements IClickEventMessageHandler, IMenuMessageHandler {

	public static final String EVENT_KEY = Const.EVENT_KEY_ORDER;
	public static final String NAME = IMenu.NAME_ORDER;

	@Override
	public IMessageReply handleMessage(Message message, IUserState state) {
		UserRedirectMessageReply reply = new UserRedirectMessageReply(message);
		reply.setDescription("description");
		reply.setTitle("title");
		reply.setPicUrl(Config.getHost() + "img/order.jpg");
		reply.setUrl(Config.getHost() + "order/index");
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
}
