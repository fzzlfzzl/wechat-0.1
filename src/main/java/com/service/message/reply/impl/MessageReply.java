package com.service.message.reply.impl;

import com.service.message.IMessage;
import com.service.message.reply.IMessageReply;
import com.util.XmlObject;

public class MessageReply implements IMessageReply {

	protected XmlObject res = null;
	protected IMessage message = null;

	public void setMessage(IMessage message) {
		this.res = new XmlObject("xml");
		this.message = message;
		initRes();
	}

	private void initRes() {
		res.get("ToUserName").setCDATA(message.getFromUserName());
		res.get("FromUserName").setCDATA(message.getToUserName());
		res.get("CreateTime").setText(message.getCreateTime());
		res.get("MsgType").setCDATA(message.getMsgType());
	}

	@Override
	public XmlObject getResponse() {
		return res;
	}

}
