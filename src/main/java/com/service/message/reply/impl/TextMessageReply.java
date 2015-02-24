package com.service.message.reply.impl;

import com.dao.entity.Message;
import com.service.message.reply.ITextMessageReply;

public class TextMessageReply extends MessageReply implements ITextMessageReply {

	public TextMessageReply(Message message) {
		super(message);
		res.get("MsgType").setCDATA("text");
	}

	@Override
	public void setContent(String content) {
		res.get("Content").setCDATA(content);
	}

}
