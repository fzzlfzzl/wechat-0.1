package com.service.message.reply.impl;

import com.service.message.IMessage;
import com.service.message.reply.ITextMessageReply;

public class TextMessageReply extends MessageReply implements ITextMessageReply {

	public TextMessageReply(IMessage message) {
		super(message);
	}

	@Override
	public void setContent(String content) {
		res.get("Content").setCDATA(content);
	}

}
