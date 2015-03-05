package com.service.wechat.message.handler.impl;

import org.apache.log4j.Logger;

import com.service.wechat.Const;
import com.service.wechat.message.handler.IMessageHandler;
import com.service.wechat.message.reply.IMessageReply;
import com.service.wechat.message.reply.impl.TextMessageReply;
import com.service.wechat.session.SessionPool;
import com.service.wechat.session.StateHandler;
import com.web.dao.entity.Message;
import com.web.dao.entity.User;
import com.web.dao.impl.UserDao;

public class AddressUpdateMessageHandler implements IMessageHandler {

	static Logger logger = Logger.getLogger(AddressMessageHandler.class);

	@Override
	public IMessageReply handleMessage(Message message, StateHandler state) {
		if (!message.getMsgType().equals(Const.TYPE_TEXT)) {
			logger.info("Not Suitable Message");
			return null;
		}
		UserDao dao = new UserDao(SessionPool.current());
		User user = dao.get(message.getOpenId());
		user.setAddress(message.getContent());
		dao.update(user);
		TextMessageReply reply = new TextMessageReply(message);
		reply.setContent(Const.RES_ADDR_UPDT_SUCC);
		return reply;
	}

}
