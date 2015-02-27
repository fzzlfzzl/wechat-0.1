package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.service.Const;

@Controller
@RequestMapping("/account")
public class AccountController {

	private static Logger logger = Logger.getLogger(AccountController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginGet(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView ret = new ModelAndView("login");
		return ret;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginPost(HttpServletRequest request, HttpServletResponse response,
			String user, String pwd) throws Exception {
		if (user.equals("admin") && pwd.equals("111111")) {
			request.getSession().setAttribute(Const.SES_LOGIN, Const.SES_LOGIN);
			response.sendRedirect(request.getContextPath() + "/site/index");
			return null;
		} else {
			ModelAndView ret = new ModelAndView("login");
			return ret;
		}
	}
}
