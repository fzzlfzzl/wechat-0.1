package com.service.wechat.message.reply.impl;

import com.service.wechat.message.reply.ITextMessageReply;
import com.web.dao.entity.Message;

public class TextMessageReply extends MessageReply implements ITextMessageReply {

	public TextMessageReply(Message message) {
		super(message);
	}

	@Override
	public void setContent(String content) {
		res.get("Content").setCDATA(content);
	}

	@Override
	protected String getMsgType() {
		return "text";
	}

}
