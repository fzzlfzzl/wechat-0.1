package com.service.message.bean;

import com.service.message.handler.IMessageHandler;

public interface IMessageBean {

	public static String TYPE_TEXT = "text";
	public static String TYPE_EVENT = "event";
	public static String TYPE_CLICK = "CLICK";

	public static String EVENT_KEY_ADDRESS = "ADDRESS";

	public String getToUserName();

	public String getFromUserName();

	public String getCreateTime();

	public String getMsgType();

	public String getMsgId();

	public String getContent();

	public String getEvent();

	public String getEventKey();

	public IMessageHandler getHandler();

}
