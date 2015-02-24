package com.service.message.reply.impl;

import com.service.message.bean.IMessageBean;
import com.service.message.reply.ITextMessageReply;

public class TextMessageReply extends MessageReply implements ITextMessageReply {

	public TextMessageReply(IMessageBean message) {
		super(message);
		res.get("MsgType").setCDATA("text");
	}

	@Override
	public void setContent(String content) {
		res.get("Content").setCDATA(content);
	}

}
