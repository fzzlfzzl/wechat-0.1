package com.service.message.impl;

import org.apache.log4j.Logger;

import com.service.message.base.MessageHandler;
import com.util.WeChatMenu;
import com.util.XmlObject;

public class EventMessageHandler extends MessageHandler {

	private static Logger logger = Logger.getLogger(EventMessageHandler.class);

	public String getMsgType() {
		return "event";
	}

	public void handleMessage(XmlObject request, XmlObject response) {
		String eventKey = request.get("EventKey").getText();
		String content = null;
		if (WeChatMenu.ADDRESS.equals(eventKey)) {
			content = "请输入地址";
		} else if (WeChatMenu.ORDER.equals(eventKey)) {
			content = "订餐成功";
		} else {
			logger.warn("Unknown Key:" + eventKey);
			content = "系统错误";
		}
		response.get("Content").setCDATA(content);
		response.get("MsgType").setCDATA("text");
	}
}
