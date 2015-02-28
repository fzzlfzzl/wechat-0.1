package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.controller.base.WebController;
import com.web.auth.AuthUtil;
import com.web.html.HtmlTag;
import com.web.html.HtmlArray;

@Controller
@RequestMapping("/account")
public class AccountController extends WebController {

	// private static Logger logger = Logger.getLogger(AccountController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginGet() throws Exception {
		ModelAndView ret = createNormalModelAndView("login");
		HtmlArray arr = new HtmlArray();
		HtmlTag input = HtmlTag.inputText("user");
		arr.add(input);
		input = HtmlTag.inputPassword("pwd");
		arr.add(input);
		input = HtmlTag.submit("go");
		arr.add(input);
		ret.addObject("input", arr);
		return ret;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginPost(String user, String pwd) throws Exception {
		if (user.equals("admin") && pwd.equals("111111")) {
			AuthUtil.login();
			return createRedirectModelAndView("site/index");
		} else {
			return loginGet();
		}
	}
}
