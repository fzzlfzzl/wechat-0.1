package com.test.service;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.service.wechat.Const;
import com.service.wechat.WechatService;
import com.service.wechat.message.handler.impl.AddressMessageHandler;
import com.site.util.XmlObject;
import com.test.common.Common;

public class ServiceTest {

	static Logger logger = Logger.getLogger("Test");

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
