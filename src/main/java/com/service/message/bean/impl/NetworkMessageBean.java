package com.service.message.bean.impl;

import com.service.message.bean.IMessageBean;
import com.service.message.handler.IMessageHandler;
import com.util.XmlObject;

public class NetworkMessageBean implements IMessageBean {

	protected XmlObject req = null;

	public NetworkMessageBean(XmlObject req) {
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

	@Override
	public String getMsgType() {
		return req.get("MsgType").getText();
	}

	@Override
	public String getContent() {
		return req.get("Content").getText();
	}

	@Override
	public String getEvent() {
		return req.get("Event").getText();
	}

	@Override
	public String getEventKey() {
		return req.get("EventKey").getText();
	}

	@Override
	public IMessageHandler getHandler() {
		return null;
	}

}