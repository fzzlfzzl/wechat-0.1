package com.service.wechat.message.handler.impl;

import com.service.wechat.Const;
import com.service.wechat.menu.IMenu;
import com.service.wechat.message.handler.IClickEventMessageHandler;
import com.service.wechat.message.handler.IMenuMessageHandler;
import com.service.wechat.message.reply.IMessageReply;
import com.service.wechat.message.reply.impl.TextMessageReply;
import com.service.wechat.session.StateHandler;
import com.web.dao.entity.Message;

/**
 * 请用户确认
 * 
 * @author yml
 * 
 */
public class AddressCheckMessageHandler implements IClickEventMessageHandler, IMenuMessageHandler {

	public static final String EVENT_KEY = Const.EVENT_KEY_ADDRESS;
	public static final String NAME = IMenu.NAME_ADDRESS;

	@Override
	public IMessageReply handleMessage(Message message, StateHandler state) {
		if (!message.getMsgType().equals(Const.TYPE_TEXT)) {
			return null;
		}
		if (!message.getContent().equals(Const.REQ_YES)) {
			return null;
		}
		state.setNextHandler(new AddressUpdateMessageHandler());
		TextMessageReply reply = new TextMessageReply(message);
		reply.setContent(Const.RES_ADDR);
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