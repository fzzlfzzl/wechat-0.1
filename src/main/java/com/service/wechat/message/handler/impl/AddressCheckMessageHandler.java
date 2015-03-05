package com.service.wechat.message.handler.impl;

import com.service.wechat.Const.EventKey;
import com.service.wechat.Const.MsgReply;
import com.service.wechat.Const.Msg;
import com.service.wechat.Const.Type;
import com.service.wechat.menu.IMenu;
import com.service.wechat.message.handler.IClickEventMessageHandler;
import com.service.wechat.message.handler.IMenuMessageHandler;
import com.service.wechat.message.handler.StateHandler;
import com.service.wechat.message.reply.IMessageReply;
import com.service.wechat.message.reply.impl.TextMessageReply;
import com.web.dao.entity.Message;

/**
 * 请用户确认
 * 
 * @author yml
 * 
 */
public class AddressCheckMessageHandler implements IClickEventMessageHandler, IMenuMessageHandler {

	public static final String EVENT_KEY = EventKey.ADDRESS;
	public static final String NAME = IMenu.NAME_ADDRESS;

	@Override
	public IMessageReply handleMessage(Message message, StateHandler state) {
		if (!message.getMsgType().equals(Type.TEXT)) {
			return null;
		}
		if (!message.getContent().equals(Msg.YES)) {
			return null;
		}
		state.setNextHandler(new AddressUpdateMessageHandler());
		TextMessageReply reply = new TextMessageReply(message);
		reply.setContent(MsgReply.ADDR);
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
