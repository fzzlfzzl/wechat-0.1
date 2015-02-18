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

	/**
	 * Constructor of the object.
	 */
	public WechatCallbackApi() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("接收请求:" + request.getQueryString());
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");

		String[] str = { TOKEN, timestamp, nonce };
		Arrays.sort(str); // 字典序排序
		String bigStr = str[0] + str[1] + str[2];
		// SHA1加密
		String digest = EncoderHelper.sha1(bigStr).toLowerCase();

		// 确认请求来至微信
		if (digest.equals(signature)) {
			logger.info("发送响应:" + echostr);
			response.getWriter().print(echostr);
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
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

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
	}

}
