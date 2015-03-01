package com.service.message.handler.impl;

import org.apache.log4j.Logger;

import com.service.Const;
import com.service.message.handler.IMessageHandler;
import com.service.message.reply.IMessageReply;
import com.service.message.reply.impl.TextMessageReply;
import com.service.state.IUserState;
import com.web.dao.entity.Message;

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
		reply.setContent(Const.RPY_ADDR_UPDT_SUCC);
		return reply;
	}

}
