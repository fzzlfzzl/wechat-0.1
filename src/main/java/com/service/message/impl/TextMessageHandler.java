package com.service.message.impl;

import com.service.message.base.MessageHandler;
import com.util.XmlObject;

public class TextMessageHandler extends MessageHandler {

	public String getMsgType() {
		return "text";
	}

	public void handleMessage(XmlObject request, XmlObject response) {
		String content = String.format("你好，%s", request.get("FromUserName")
				.getText());
		response.get("Content").setCDATA(content);
		response.get("MsgType").setCDATA("text");
	}
}
