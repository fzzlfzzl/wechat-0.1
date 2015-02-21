package com.service.message.impl;

import com.service.message.IClickEventMessage;
import com.service.message.IMessage;

public abstract class ClickEventMessage extends Message implements
		IClickEventMessage {

	@Override
	public String getMsgType() {
		return IMessage.TYPE_EVENT;
	}

	@Override
	public String getEvent() {
		return IMessage.TYPE_CLICK;
	}

}
