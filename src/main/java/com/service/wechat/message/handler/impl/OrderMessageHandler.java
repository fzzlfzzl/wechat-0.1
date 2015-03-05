package com.service.wechat.message.handler.impl;

import com.service.wechat.Const.EventKey;
import com.service.wechat.menu.IMenu;
import com.service.wechat.message.handler.IClickEventMessageHandler;
import com.service.wechat.message.handler.IMenuMessageHandler;
import com.service.wechat.message.reply.IMessageReply;
import com.service.wechat.message.reply.impl.UserRedirectMessageReply;
import com.service.wechat.session.StateHandler;
import com.site.Config;
import com.web.dao.entity.Message;

public class OrderMessageHandler implements IClickEventMessageHandler, IMenuMessageHandler {

	public static final String EVENT_KEY = EventKey.ORDER;
	public static final String NAME = IMenu.NAME_ORDER;

	@Override
	public IMessageReply handleMessage(Message message, StateHandler state) {
		UserRedirectMessageReply reply = new UserRedirectMessageReply(message);
		reply.setDescription("description");
		reply.setTitle("title");
		reply.setPicUrl(Config.getHost() + "img/banner.jpg");
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
