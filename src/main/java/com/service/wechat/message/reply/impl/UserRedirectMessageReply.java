package com.service.wechat.message.reply.impl;

import com.web.dao.entity.Message;

public class UserRedirectMessageReply extends MessageReply {

	public UserRedirectMessageReply(Message message) {
		super(message);
		res.get("ArticleCount").setText("1");
	}

	@Override
	protected String getMsgType() {
		return "news";
	}

	public void setTitle(String str) {
		res.get("Articles").get("item").get("Title").setCDATA(str);
	}

	public void setDescription(String str) {
		res.get("Articles").get("item").get("Description").setCDATA(str);
	}

	public void setPicUrl(String str) {
		res.get("Articles").get("item").get("PicUrl").setCDATA(str);
	}

	public void setUrl(String str) {
		if (str.endsWith("/")) {
			str = str.substring(0, str.length() - 1);
		}
		String url = String.format("%s?openid=%s", str, message.getOpenId());
		res.get("Articles").get("item").get("Url").setCDATA(url);
	}
}
