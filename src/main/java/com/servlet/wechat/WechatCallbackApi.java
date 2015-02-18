package com.servlet.wechat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.service.message.IMessageHandler;
import com.service.message.factory.MessageHandlerFactory;
import com.util.EncoderHelper;
import com.util.Util;
import com.util.XmlObject;

public class WechatCallbackApi extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String TOKEN = "jiuwubaodu";
	private static Logger logger = LogManager.getLogger(WechatCallbackApi.class
			.getName());

	public WechatCallbackApi() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Get Request:" + request.getQueryString());

		// 微信加密签名
		String signature = request.getParameter("signature");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");

		if (null == signature || null == echostr || null == timestamp
				|| null == nonce) {
			logger.warn("Invalid Param");
			response.getWriter().print("Invalid Param");
			return;
		}
		String[] str = { TOKEN, timestamp, nonce };
		Arrays.sort(str); // 字典序排序
		String bigStr = str[0] + str[1] + str[2];
		// SHA1加密
		String digest = EncoderHelper.sha1(bigStr).toLowerCase();

		// 确认请求来至微信
		if (digest.equals(signature)) {
			logger.info("Get Response:" + echostr);
			response.getWriter().print(echostr);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			String req = Util.readStream(request.getInputStream());
			logger.info("Request:" + req);

			XmlObject reqObject = XmlObject.toXmlObject(req);
			String type = reqObject.get("MsgType").getText();
			IMessageHandler handler = MessageHandlerFactory.getHandler(type);
			XmlObject resObject = handler.handleMessage(reqObject);

			String res = resObject.toXmlString();
			logger.info("Response:" + res);
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
			out.print("");
		}
	}

	public void init() throws ServletException {
	}

}
