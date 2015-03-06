package com.service.wechat.message.factory;

import com.site.util.XmlObject;
import com.web.dao.entity.Message;

public class MessageFactory {

	public static Message createMessage(XmlObject req) {
		Message message = new Message();
		message.setMsgId(Long.parseLong(req.get("MsgId").getText()));
		message.setCreateTime(Long.parseLong(req.get("CreateTime").getText()));
		message.setContent(req.get("Content").getText());
		message.setEvent(req.get("Event").getText());
		message.setEventKey(req.get("EventKey").getText());
		message.setMsgType(req.get("MsgType").getText());
		message.setFromUserName(req.get("FromUserName").getText());
		message.setToUserName(req.get("ToUserName").getText());
		return message;
	}
}
