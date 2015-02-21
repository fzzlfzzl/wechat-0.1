package com.service.message;

import com.util.XmlObject;

public interface IMessage {

	public static String TYPE_TEXT = "text";
	public static String TYPE_EVENT = "event";
	public static String TYPE_CLICK = "CLICK";

	public void setRequest(XmlObject req);

	public String getToUserName();

	public String getFromUserName();

	public String getCreateTime();

	public String getMsgType();

	public String getMsgId();

	public IMessageHandler getHandler();

}
