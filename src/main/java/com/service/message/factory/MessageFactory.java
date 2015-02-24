package com.service.message.factory;

import com.service.message.bean.IMessageBean;
import com.service.message.bean.impl.NetworkMessageBean;
import com.util.XmlObject;

public class MessageFactory {

	public static IMessageBean createMessage(XmlObject req) {
		return new NetworkMessageBean(req);
	}
}
