package com.service.message.reply.impl;

import com.service.message.reply.ITextMessageReply;

public class TextMessageReply extends MessageReply implements ITextMessageReply {

	@Override
	public void setContent(String content) {
		res.get("Content").setCDATA(content);
	}

}
