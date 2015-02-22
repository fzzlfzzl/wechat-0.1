package com.service.message.impl;

import com.service.message.IClickEventMessage;
import com.service.message.IMessage;
import com.util.XmlObject;

public abstract class ClickEventMessage extends Message implements IClickEventMessage {

	public ClickEventMessage(XmlObject req) {
		super(req);
	}

	@Override
	public String getMsgType() {
		return IMessage.TYPE_EVENT;
	}

	@Override
	public String getEvent() {
		return IMessage.TYPE_CLICK;
	}

}
