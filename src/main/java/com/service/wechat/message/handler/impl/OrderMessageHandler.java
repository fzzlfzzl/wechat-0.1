package com.service.wechat.message.handler.impl;

import com.service.wechat.Const.EventKey;
import com.service.wechat.Const.MenuName;
import com.service.wechat.message.handler.IClickEventMessageHandler;
import com.service.wechat.message.handler.IMenuMessageHandler;
import com.service.wechat.message.handler.StateHandler;
import com.service.wechat.message.reply.IMessageReply;
import com.service.wechat.message.reply.impl.UserRedirectMessageReply;
import com.site.Config;
import com.web.dao.entity.Message;

public class OrderMessageHandler implements IClickEventMessageHandler, IMenuMessageHandler {

	public static final String EVENT_KEY = EventKey.ORDER;
	public static final String NAME = MenuName.ORDER;

	@Override
	public IMessageReply handleMessage(Message message, StateHandler state) {
		UserRedirectMessageReply reply = new UserRedirectMessageReply(message);
		reply.setDescription("description");
		reply.setTitle("title");
		reply.setPicUrl(Config.getHost() + "img/banner.jpg");
		reply.setUrl(Config.getHost() + "order/" + message.getOpenId());
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
