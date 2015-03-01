package com.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.service.WechatService;
import com.util.ExceptionLogger;

@Controller
@RequestMapping("/wechat")
public class WechatController {

	private static Logger logger = Logger.getLogger(WechatController.class);

	@RequestMapping(value = "/service", method = RequestMethod.GET)
	public void serviceGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			WechatService.doGet(request, response);
		} catch (Exception e) {
			logger.error(new ExceptionLogger(e));
		}
	}

	@RequestMapping(value = "/service", method = RequestMethod.POST)
	public void servicePost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			WechatService.doPost(request, response);
		} catch (Exception e) {
			logger.error(new ExceptionLogger(e));
		}
	}
}
