package com.test;

import org.junit.Test;

import com.util.HttpClient;
import com.util.XmlObject;
import static org.junit.Assert.*;

public class ServiceTest {

	@Test
	public void textMessageTest() throws Exception {
		String message = "<xml><ToUserName><![CDATA[gh_5cb711bbf02b]]></ToUserName><MsgId>6116423240939715753</MsgId><Content><![CDATA[hello]]></Content><MsgType><![CDATA[text]]></MsgType><CreateTime>1424090760</CreateTime><FromUserName><![CDATA[o5bFts7d47KVX7OEIoK_DY9WJ_xY]]></FromUserName></xml>";
		HttpClient client = new HttpClient("http://127.0.0.1:8080/wechat/wechat/service");
		System.out.println(message);
		String res = client.post(message);
		System.out.println(res);
		XmlObject resObj = XmlObject.toXmlObject(res);
		assertEquals(resObj.get("FromUserName").getText(), "gh_5cb711bbf02b");
	}

	/*
	 * public static String txt =
	 * "<xml><ToUserName><![CDATA[gh_5cb711bbf02b]]></ToUserName><MsgId>6116423240939715753</MsgId><Content><![CDATA[??]]></Content><MsgType><![CDATA[text]]></MsgType><CreateTime>1424090760</CreateTime><FromUserName><![CDATA[o5bFts7d47KVX7OEIoK_DY9WJ_xY]]></FromUserName></xml>"
	 * ; public static String key =
	 * "<xml><ToUserName><![CDATA[gh_d46ef0e2c7e5]]></ToUserName><FromUserName><![CDATA[o0b6Ks0kuzfd6oVKTgXSTLsYMUiU]]></FromUserName><CreateTime>1424101033</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[CLICK]]></Event><EventKey><![CDATA[ADDRESS]]></EventKey></xml>"
	 * ;
	 * 
	 * public static String accessToken =
	 * "IQvzRoLSEvxxlR1xzI7Qg5lIo0yLtu6-JxvS_HSE-cChIurjr9j4zAk5ue2OVanc25GTDq17aGRhJQvO6SBvB7qypqpn3zQAXmhYZffmJ1A"
	 * ;
	 * 
	 * public static void getMenu() throws Exception { String urlFmt =
	 * "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%s"; String url
	 * = String.format(urlFmt, accessToken); HttpClient client = new
	 * HttpClient(url); String res = client.get(); System.out.println(res); }
	 * 
	 * public static void main(String args[]) throws Exception { // getMenu();
	 * // String url = WeChat.getAccessTokenUrl(); // System.out.println(url);
	 * // // MenuManager.registMenu();
	 * 
	 * HttpClient client = new HttpClient( "http://localhost/wechat");
	 * System.out.println(client.post(key)); }
	 */

}
