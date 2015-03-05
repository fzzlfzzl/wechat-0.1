package com.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.service.wechat.WechatService;

@Controller
@RequestMapping("/wechat")
public class WechatController {

	@RequestMapping(value = "/service", method = RequestMethod.GET)
	public void serviceGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		WechatService.doGet(request, response);

	}

	@RequestMapping(value = "/service", method = RequestMethod.POST)
	public void servicePost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		WechatService.doPost(request, response);
	}
}
