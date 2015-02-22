package com.test;

import org.junit.Test;

import com.service.WechatService;
import com.service.message.impl.AddressClickEventMessage;
import com.util.XmlObject;
import static org.junit.Assert.*;

public class ClickEventTest {

	private static final String APP_NAME = "gh_5cb711bbf02b";
	private static final String USER_NAME = "o5bFts7d47KVX7OEIoK_DY9WJ_xY";

	public XmlObject createClickEventMessage(String key) {
		XmlObject req = new XmlObject("xml");
		req.get("ToUserName").setCDATA(APP_NAME);
		req.get("FromUserName").setCDATA(USER_NAME);
		req.get("CreateTime").setText("" + System.currentTimeMillis());
		req.get("MsgType").setCDATA("event");
		req.get("Event").setCDATA("CLICK");
		req.get("EventKey").setCDATA(key);
		return req;
	}

	@Test
	public void addressMenuTest() {
		XmlObject req = createClickEventMessage(AddressClickEventMessage.EVENT_KEY);
		XmlObject res = WechatService.service(req);
		System.out.println(res.toXmlString());
		assertEquals(res.get("FromUserName").getText(), APP_NAME);
		assertEquals(res.get("ToUserName").getText(), USER_NAME);
		assertEquals(res.get("MsgType").getText(), "text");
	}

}
