package com.wechat.message.handler.impl;

import org.apache.log4j.Logger;

import com.web.dao.entity.Message;
import com.web.dao.entity.User;
import com.web.dao.impl.UserDao;
import com.wechat.Const;
import com.wechat.message.handler.IMessageHandler;
import com.wechat.message.reply.IMessageReply;
import com.wechat.message.reply.impl.TextMessageReply;
import com.wechat.session.SessionPool;
import com.wechat.session.StateHandler;

public class AddressUpdateMessageHandler implements IMessageHandler {

	static Logger logger = Logger.getLogger(AddressMessageHandler.class);

	@Override
	public IMessageReply handleMessage(Message message, StateHandler state) {
		if (!message.getMsgType().equals(Const.TYPE_TEXT)) {
			logger.info("Not Suitable Message");
			return null;
		}
		UserDao dao = new UserDao(SessionPool.current());
		User user = dao.load(message.getOpenId());
		user.setAddress(message.getContent());
		dao.update(user);
		TextMessageReply reply = new TextMessageReply(message);
		reply.setContent(Const.RES_ADDR_UPDT_SUCC);
		return reply;
	}

}
