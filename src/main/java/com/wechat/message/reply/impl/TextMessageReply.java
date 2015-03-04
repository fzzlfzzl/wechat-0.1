package com.wechat.message.reply.impl;

import com.web.dao.entity.Message;
import com.wechat.message.reply.ITextMessageReply;

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
