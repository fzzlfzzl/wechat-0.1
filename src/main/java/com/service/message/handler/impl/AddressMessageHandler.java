package com.service.message.handler.impl;

import com.service.Const;
import com.service.menu.IMenu;
import com.service.message.handler.IClickEventMessageHandler;
import com.service.message.handler.IMenuMessageHandler;
import com.service.message.reply.IMessageReply;
import com.service.message.reply.impl.TextMessageReply;
import com.service.state.IUserState;
import com.web.dao.entity.Message;

public class AddressMessageHandler implements IClickEventMessageHandler, IMenuMessageHandler {

	public static final String EVENT_KEY = Const.EVENT_KEY_ADDRESS;
	public static final String NAME = IMenu.NAME_ADDRESS;

	@Override
	public IMessageReply handleMessage(Message message, IUserState state) {
		state.setNextHandler(new AddressUpdateMessageHandler());
		TextMessageReply reply = new TextMessageReply(message);
		if (null != state.getUser().getAddress()) {
			// 已有地址，需要确认
			state.setNextHandler(new AddressCheckMessageHandler());
			String content = String.format(Const.RPY_ADDR_CHK, state.getUser().getAddress());
			reply.setContent(content);
		} else {
			// 没有地址，直接填写
			reply.setContent(Const.RPY_ADDR);
			state.setNextHandler(new AddressUpdateMessageHandler());
		}
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
