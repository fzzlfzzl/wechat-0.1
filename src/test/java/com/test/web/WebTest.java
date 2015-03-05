package com.test.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.service.wechat.message.handler.impl.OrderMessageHandler;
import com.site.Config;
import com.site.util.HttpClient;
import com.site.util.XmlObject;

public class WebTest {
	private static final String APP_NAME = "gh_5cb711bbf02b";
	private static final String USER_NAME = "o5bFts7d47KVX7OEIoK_DY9WJ_xY";
	private HttpClient client = new HttpClient("http://127.0.0.1:8080/wechat/wechat/service");

	@Test
	public void normalMessageTest() throws Exception {
		String message = "<xml><ToUserName><![CDATA[gh_5cb711bbf02b]]></ToUserName><MsgId>6116423240939715753</MsgId><Content><![CDATA[hello]]></Content><MsgType><![CDATA[text]]></MsgType><CreateTime>1424090760</CreateTime><FromUserName><![CDATA[o5bFts7d47KVX7OEIoK_DY9WJ_xY]]></FromUserName></xml>";
		HttpClient client = new HttpClient("http://127.0.0.1:8080/wechat/wechat/service");
		System.out.println(message);
		String res = client.post(message);
		System.out.println(res);
		XmlObject resObj = XmlObject.toXmlObject(res);
		assertEquals(resObj.get("FromUserName").getText(), "gh_5cb711bbf02b");
	}

	@Test
	public void orderTest() {
		try {
			String picUrl = Config.getHost() + "img/banner.jpg";
			String url = Config.getHost() + "order/index?openid=" + USER_NAME;
			XmlObject req = createClickEventMessage(OrderMessageHandler.EVENT_KEY);
			String resStr = client.post(req.toXmlString());
			System.out.println(resStr);
			XmlObject res = XmlObject.toXmlObject(resStr);
			assertEquals(res.get("MsgType").getText(), "news");
			assertEquals(res.get("ArticleCount").getText(), "1");
			res = res.get("Articles").get("item");
			assertEquals(res.get("Title").getText(), "title");
			assertEquals(res.get("Description").getText(), "description");
			assertEquals(res.get("PicUrl").getText(), picUrl);
			assertEquals(res.get("Url").getText(), url);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

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

	public XmlObject createTextMessage(String content) {
		XmlObject req = new XmlObject("xml");
		req.get("ToUserName").setCDATA(APP_NAME);
		req.get("FromUserName").setCDATA(USER_NAME);
		req.get("CreateTime").setText("" + System.currentTimeMillis());
		req.get("MsgType").setCDATA("text");
		req.get("MsgId").setText("" + System.currentTimeMillis());
		req.get("Content").setCDATA(content);
		return req;
	}
}
