package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.controller.base.WebController;
import com.service.web.AdminService;
import com.web.html.HtmlArray;
import com.web.html.HtmlTag;

@Controller
@RequestMapping("/admin")
public class AdminController extends WebController {

	// private static Logger logger = Logger.getLogger(AccountController.class);
	private AdminService service = new AdminService();

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
	public ModelAndView loginPost(String name, String pwd) throws Exception {
		if (service.login(name, pwd)) {
			return createRedirectModelAndView("site/index");
		} else {
			return loginGet();
		}
	}

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ModelAndView index(String user, String pwd) throws Exception {
		return createNormalModelAndView("index");
	}
}
