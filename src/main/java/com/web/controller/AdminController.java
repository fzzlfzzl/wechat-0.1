package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.controller.base.WebController;
import com.web.interceptor.annotation.AuthAdmin;
import com.web.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController extends WebController {

	// private static Logger logger = Logger.getLogger(AccountController.class);
	private AdminService service = new AdminService();

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginGet() throws Exception {
		ModelAndView ret = createNormalModelAndView("login");
		return ret;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginPost(String name, String pwd) throws Exception {
		if (service.login(name, pwd)) {
			return createRedirectModelAndView("index");
		} else {
			return loginGet();
		}
	}

	@AuthAdmin
	@RequestMapping(value = { "", "/", "/index" }, method = RequestMethod.GET)
	public ModelAndView index(String user, String pwd) throws Exception {
		return createNormalModelAndView("index");
	}
}
