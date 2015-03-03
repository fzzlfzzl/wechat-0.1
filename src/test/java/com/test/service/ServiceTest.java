package com.test.service;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.test.common.Common;
import com.util.HttpClient;
import com.util.XmlObject;
import com.wechat.Const;
import com.wechat.WechatService;
import com.wechat.message.handler.impl.AddressMessageHandler;

import static org.junit.Assert.*;

public class ServiceTest {

	static Logger logger = Logger.getLogger("Test");

	private static final String APP_NAME = "gh_5cb711bbf02b";
	private static final String USER_NAME = "o5bFts7d47KVX7OEIoK_DY9WJ_xY";

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
	public void addressMenuTest() {
		XmlObject req = new XmlObject("xml");
		req.get("ToUserName").setCDATA("gh_5cb711bbf02b");
		req.get("FromUserName").setCDATA("o5bFts7d47KVX7OEIoK_DY9WJ_xY");
		req.get("CreateTime").setText("" + System.currentTimeMillis());
		req.get("MsgType").setCDATA("event");

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

	@Test
	public void addressUpdateTest() {
		Common.getDbManager().dropTables();
		String addr1 = "地址1";
		String addr2 = "地址2";
		String addr3 = "地址3";
		{
			// 第一次请求
			XmlObject req = createTextMessage("hello");
			logger.info(req.toXmlString());
			XmlObject res = WechatService.service(req);
			logger.info(res.toXmlString());
			assertEquals(res.get("FromUserName").getText(), APP_NAME);
			assertEquals(res.get("ToUserName").getText(), USER_NAME);
			assertEquals(res.get("MsgType").getText(), "text");
			assertEquals(res.get("Content").getText(), Const.RES_NORMAL);
		}
		{
			// 点击地址按钮
			XmlObject req = createClickEventMessage(AddressMessageHandler.EVENT_KEY);
			logger.info(req.toXmlString());
			XmlObject res = WechatService.service(req);
			logger.info(res.toXmlString());
			assertEquals(res.get("FromUserName").getText(), APP_NAME);
			assertEquals(res.get("ToUserName").getText(), USER_NAME);
			assertEquals(res.get("MsgType").getText(), "text");
			assertEquals(res.get("Content").getText(), Const.RES_ADDR);
		}
		{
			// 输入地址
			XmlObject req = createTextMessage(addr1);
			logger.info(req.toXmlString());
			XmlObject res = WechatService.service(req);
			logger.info(res.toXmlString());
			assertEquals(res.get("FromUserName").getText(), APP_NAME);
			assertEquals(res.get("ToUserName").getText(), USER_NAME);
			assertEquals(res.get("MsgType").getText(), "text");
			assertEquals(res.get("Content").getText(), Const.RES_ADDR_UPDT_SUCC);
		}
		{
			// 再次点击地址按钮，判断地址
			XmlObject req = createClickEventMessage(AddressMessageHandler.EVENT_KEY);
			logger.info(req.toXmlString());
			XmlObject res = WechatService.service(req);
			logger.info(res.toXmlString());
			assertEquals(res.get("FromUserName").getText(), APP_NAME);
			assertEquals(res.get("ToUserName").getText(), USER_NAME);
			assertEquals(res.get("MsgType").getText(), "text");
			String check = String.format(Const.RES_ADDR_CHK, addr1);
			assertEquals(res.get("Content").getText(), check);
		}
		{
			// 输入是
			XmlObject req = createTextMessage("是");
			logger.info(req.toXmlString());
			XmlObject res = WechatService.service(req);
			logger.info(res.toXmlString());
			assertEquals(res.get("FromUserName").getText(), APP_NAME);
			assertEquals(res.get("ToUserName").getText(), USER_NAME);
			assertEquals(res.get("MsgType").getText(), "text");
			assertEquals(res.get("Content").getText(), Const.RES_ADDR);
		}
		{
			// 输入地址2
			XmlObject req = createTextMessage(addr2);
			logger.info(req.toXmlString());
			XmlObject res = WechatService.service(req);
			logger.info(res.toXmlString());
			assertEquals(res.get("FromUserName").getText(), APP_NAME);
			assertEquals(res.get("ToUserName").getText(), USER_NAME);
			assertEquals(res.get("MsgType").getText(), "text");
			assertEquals(res.get("Content").getText(), Const.RES_ADDR_UPDT_SUCC);
		}
		{
			// 再次点击地址按钮,判断地址2
			XmlObject req = createClickEventMessage(AddressMessageHandler.EVENT_KEY);
			logger.info(req.toXmlString());
			XmlObject res = WechatService.service(req);
			logger.info(res.toXmlString());
			assertEquals(res.get("FromUserName").getText(), APP_NAME);
			assertEquals(res.get("ToUserName").getText(), USER_NAME);
			assertEquals(res.get("MsgType").getText(), "text");
			String check = String.format(Const.RES_ADDR_CHK, addr2);
			assertEquals(res.get("Content").getText(), check);
		}
		{
			// 再次点击地址按钮,依然是2
			XmlObject req = createClickEventMessage(AddressMessageHandler.EVENT_KEY);
			logger.info(req.toXmlString());
			XmlObject res = WechatService.service(req);
			logger.info(res.toXmlString());
			assertEquals(res.get("FromUserName").getText(), APP_NAME);
			assertEquals(res.get("ToUserName").getText(), USER_NAME);
			assertEquals(res.get("MsgType").getText(), "text");
			String check = String.format(Const.RES_ADDR_CHK, addr2);
			assertEquals(res.get("Content").getText(), check);
		}
		{
			// 不输入是，输入其他烂七八糟
			XmlObject req = createTextMessage(addr3);
			logger.info(req.toXmlString());
			XmlObject res = WechatService.service(req);
			logger.info(res.toXmlString());
			assertEquals(res.get("FromUserName").getText(), APP_NAME);
			assertEquals(res.get("ToUserName").getText(), USER_NAME);
			assertEquals(res.get("MsgType").getText(), "text");
			assertEquals(res.get("Content").getText(), Const.RES_NORMAL);
		}
		{
			// 再次点击地址按钮,依然是2,不更新
			XmlObject req = createClickEventMessage(AddressMessageHandler.EVENT_KEY);
			logger.info(req.toXmlString());
			XmlObject res = WechatService.service(req);
			logger.info(res.toXmlString());
			assertEquals(res.get("FromUserName").getText(), APP_NAME);
			assertEquals(res.get("ToUserName").getText(), USER_NAME);
			assertEquals(res.get("MsgType").getText(), "text");
			String check = String.format(Const.RES_ADDR_CHK, addr2);
			assertEquals(res.get("Content").getText(), check);
		}
	}

}
