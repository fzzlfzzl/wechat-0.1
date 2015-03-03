package com.wechat.message.reply.impl;

import com.util.XmlObject;
import com.web.dao.entity.Message;
import com.wechat.message.reply.IMessageReply;

public class MessageReply implements IMessageReply {

	protected XmlObject res = null;
	protected Message message = null;

	public MessageReply(Message message) {
		this.res = new XmlObject("xml");
		this.message = message;
		initRes();
	}

	private void initRes() {
		res.get("ToUserName").setCDATA(message.getFromUserName());
		res.get("FromUserName").setCDATA(message.getToUserName());
		res.get("CreateTime").setText(message.getCreateTime());
	}

	@Override
	public XmlObject getResponse() {
		return res;
	}

}
