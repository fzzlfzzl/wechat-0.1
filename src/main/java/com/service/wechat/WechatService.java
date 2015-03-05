package com.service.wechat;

import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.service.wechat.message.factory.MessageFactory;
import com.service.wechat.message.handler.StateHandler;
import com.service.wechat.message.reply.IMessageReply;
import com.site.util.Util;
import com.site.util.XmlObject;
import com.web.dao.entity.Message;
import com.web.dao.entity.User;
import com.web.dao.impl.UserDao;
import com.web.interceptor.context.UserContext;

public class WechatService {
	private static String TOKEN = "jiuwubaodu";

	private static Logger logger = Logger.getLogger(WechatService.class);

	public static void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		String digest = Util.sha1(bigStr).toLowerCase();
		if (digest.equals(signature)) {
			logger.info("Get Response:" + echostr);
			response.getWriter().print(echostr);
		} else {
			logger.error("Invalid signature");
		}
	}

	public static void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	}

	public static XmlObject service(XmlObject reqObject) {
		Message message = MessageFactory.createMessage(reqObject);
		logMessage(message);

		StateHandler handler = StateHandler.byUser(message.getOpenId());
		IMessageReply reply = handler.handleMessage(message);
		XmlObject resObject = reply.getResponse();
		return resObject;

	}

	private static void logMessage(Message message) {
		UserDao dao = new UserDao(UserContext.current().getSession());
		User user = dao.get(message.getOpenId());
		if (user == null) {
			user = new User();
			user.setOpenId(message.getOpenId());
			dao.save(user);
		}
		dao.addMessage(user, message);
	}

	private static boolean invalidParam(String signature, String echostr, String timestamp, String nonce) {
		return null == signature || null == echostr || null == timestamp || null == nonce;
	}
}
