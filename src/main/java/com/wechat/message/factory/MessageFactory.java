package com.wechat.message.factory;

import com.util.XmlObject;
import com.web.dao.entity.Message;

public class MessageFactory {

	public static Message createMessage(XmlObject req) {
		Message message = new Message();
		message.setContent(req.get("Content").getText());
		message.setCreateTime(req.get("CreateTime").getText());
		message.setEvent(req.get("Event").getText());
		message.setEventKey(req.get("EventKey").getText());
		message.setMsgId(req.get("MsgId").getText());
		message.setMsgType(req.get("MsgType").getText());
		message.setFromUserName(req.get("FromUserName").getText());
		message.setToUserName(req.get("ToUserName").getText());
		return message;
	}
}
