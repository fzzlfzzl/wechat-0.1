package com.wechat.message.handler.impl;

import org.apache.log4j.Logger;

import com.web.dao.entity.Message;
import com.wechat.Const;
import com.wechat.message.handler.IMessageHandler;
import com.wechat.message.reply.IMessageReply;
import com.wechat.message.reply.impl.TextMessageReply;
import com.wechat.state.IUserState;

public class AddressUpdateMessageHandler implements IMessageHandler {

	static Logger logger = Logger.getLogger(AddressMessageHandler.class);

	@Override
	public IMessageReply handleMessage(Message message, IUserState state) {
		if (!message.getMsgType().equals(Const.TYPE_TEXT)) {
			logger.info("Not Suitable Message");
			return null;
		}
		state.getUser().setAddress(message.getContent());
		state.persist();
		TextMessageReply reply = new TextMessageReply(message);
		reply.setContent(Const.RES_ADDR_UPDT_SUCC);
		return reply;
	}

}
