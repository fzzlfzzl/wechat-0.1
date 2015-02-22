package com.service.message.impl;

import com.service.message.IMessage;
import com.util.XmlObject;

public abstract class Message implements IMessage {

	protected XmlObject req = null;

	public Message(XmlObject req) {
		this.req = req;
	}

	@Override
	public String getFromUserName() {
		return req.get("FromUserName").getText();
	}

	@Override
	public String getToUserName() {
		return req.get("ToUserName").getText();
	}

	@Override
	public String getCreateTime() {
		return req.get("CreateTime").getText();
	}

	@Override
	public String getMsgId() {
		return req.get("MsgId").getText();
	}

}