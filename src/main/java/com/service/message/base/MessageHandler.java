package com.service.message.base;

import com.service.message.IMessageHandler;
import com.util.XmlObject;

public abstract class MessageHandler implements IMessageHandler {

	public abstract void handleMessage(XmlObject request, XmlObject response);

	public abstract String getMsgType();

	@Override
	public XmlObject handleMessage(XmlObject reqObject) {
		XmlObject resObject = new XmlObject("xml");

		String userName = reqObject.get("FromUserName").getText();
		String appName = reqObject.get("ToUserName").getText();
		String createTime = reqObject.get("CreateTime").getText();
		String content = reqObject.get("Content").getText();

		resObject.get("FromUserName").setCDATA(appName);
		resObject.get("ToUserName").setCDATA(userName);
		resObject.get("CreateTime").setText(createTime);
		resObject.get("Content").setCDATA(content);
		resObject.get("MsgType").setCDATA("text");

		handleMessage(reqObject, resObject);
		return resObject;
	}

}
