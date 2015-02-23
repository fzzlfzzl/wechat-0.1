package com.service;

import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.service.message.IMessage;
import com.service.message.IMessageHandler;
import com.service.message.factory.MessageFactory;
import com.service.message.reply.IMessageReply;
import com.test.tools.JsonObject;
import com.util.EncoderHelper;
import com.util.HttpClient;
import com.util.Util;
import com.util.XmlObject;

public class WechatService {
	private static String TOKEN = "jiuwubaodu";
	private static String APP_SECRET = "a22d71808ae9b3c7f9d926c7967a2c0c";
	private static String APP_ID = "wxddd3064d4314479d";

	private static String ACCESS_TOKEN = "5nLhQDU8LgjjLPSV0i18JYpVbHuNUtzkjGLnvKZPVWKXIL-kfL4WXnMFXyUu0w-_v1S4eKCIuyYDPZhImcTQEuUgtyhxKNvwGDr12fNXclQ";

	private static Logger logger = Logger.getLogger(WechatService.class);

	public static void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info("Get Request:" + request.getQueryString());
			String signature = request.getParameter("signature");
			String echostr = request.getParameter("echostr");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");

			if (invalidParam(signature, echostr, timestamp, nonce)) {
				logger.warn("Invalid Param");
				response.getWriter().print("Invalid Param");
				return;
			}
			String[] str = { TOKEN, timestamp, nonce };
			Arrays.sort(str); // 字典序排序
			String bigStr = str[0] + str[1] + str[2];
			String digest = EncoderHelper.sha1(bigStr).toLowerCase();
			if (digest.equals(signature)) {
				logger.info("Get Response:" + echostr);
				response.getWriter().print(echostr);
			} else {
				logger.warn("Invalid signature");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();

			String req = Util.readStream(request.getInputStream());
			logger.info("Request:" + req);

			XmlObject reqObject = XmlObject.toXmlObject(req);
			XmlObject resObject = service(reqObject);

			String res = resObject.toXmlString();
			logger.info("Response:" + res);
			out.print(res);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static XmlObject service(XmlObject reqObject) {
		IMessage message = MessageFactory.createMessage(reqObject);
		IMessageHandler handler = message.getHandler();
		IMessageReply reply = handler.handleMessage(message);
		XmlObject resObject = reply.getResponse();
		return resObject;
	}

	public static String getAccessTokenUrl() {
		String urlFmt = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
		String url = String.format(urlFmt, APP_ID, APP_SECRET);
		return url;
	}

	public static String getRegistMenuUrl() {
		String urlFmt = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
		String url = String.format(urlFmt, ACCESS_TOKEN);
		return url;
	}

	public static String getAccessToken() {
		return ACCESS_TOKEN;
	}

	public static String getUserInfo(String openId) throws Exception {
		String urlFmt = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
		String url = String.format(urlFmt, ACCESS_TOKEN, openId);
		HttpClient client = new HttpClient(url);
		String res = client.get();
		JsonObject obj = JsonObject.toJsonObject(res);
		return obj.get("nickname").toString();
	}

	private static boolean invalidParam(String signature, String echostr, String timestamp, String nonce) {
		return null == signature || null == echostr || null == timestamp || null == nonce;
	}
}
