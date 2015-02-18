package com.service.message;

import com.util.XmlObject;

public interface IMessageHandler {

	public XmlObject handleMessage(XmlObject reqObject);

}