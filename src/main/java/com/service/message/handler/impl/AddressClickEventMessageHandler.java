package com.service.message.handler.impl;

import com.service.menu.IMenu;
import com.service.message.bean.IMessageBean;
import com.service.message.handler.IClickEventMessageHandler;
import com.service.message.handler.IMenuMessageHandler;
import com.service.message.reply.IMessageReply;
import com.service.message.reply.impl.TextMessageReply;

public class AddressClickEventMessageHandler implements IClickEventMessageHandler, IMenuMessageHandler {

	public static final String EVENT_KEY = IMessageBean.EVENT_KEY_ADDRESS;
	public static final String NAME = IMenu.NAME_ADDRESS;

	@Override
	public IMessageReply handleMessage(IMessageBean message) {
		TextMessageReply reply = new TextMessageReply(message);
		reply.setContent("请输入地址");
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
