package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.service.WeChatService;

@Controller
@RequestMapping("/wechat")
public class WechatController {

	private static Logger logger = Logger.getLogger(WechatController.class);

	@RequestMapping(value = "/service", method = RequestMethod.GET)
	public void serviceGet(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			WeChatService.doGet(request, response);
		} catch (Exception e) {
			logger.warn(e);
		}
	}

	@RequestMapping(value = "/service", method = RequestMethod.POST)
	public void servicePost(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			WeChatService.doPost(request, response);
		} catch (Exception e) {
			logger.warn(e);
		}
	}
}
