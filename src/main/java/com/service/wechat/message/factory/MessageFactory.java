package com.service.wechat.message.factory;

import com.site.util.XmlObject;
import com.web.dao.entity.Message;

public class MessageFactory {

	public static Message createMessage(XmlObject req) {
		Message message = new Message();
		message.setCreateTime(Long.parseLong(req.get("CreateTime").getText()));
		message.setFromUserName(req.get("FromUserName").getText());
		message.setToUserName(req.get("ToUserName").getText());
		message.setMsgType(req.get("MsgType").getText());
		message.setContent(req.get("Content").getText());
		message.setMsgId(req.get("MsgId").getText());
		message.setEvent(req.get("Event").getText());
		message.setEventKey(req.get("EventKey").getText());
		return message;
	}
}
