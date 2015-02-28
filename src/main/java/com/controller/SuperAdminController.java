package com.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.controller.base.WebController;
import com.dao.entity.Admin;
import com.service.web.SuperAdminService;
import com.view.View;
import com.view.admin.AdminList;

@Controller
@RequestMapping("/sa")
public class SuperAdminController extends WebController {

	// private static Logger logger =
	// Logger.getLogger(SuperAdminController.class);
	private SuperAdminService service = new SuperAdminService();

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginGet() throws Exception {
		ModelAndView ret = createNormalModelAndView("login");
		return ret;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginPost(String user, String pwd) throws Exception {
		if (service.validate(user, pwd)) {
			service.login();
			return createRedirectModelAndView("index");
		} else {
			return loginGet();
		}
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() throws Exception {
		ModelAndView ret = createNormalModelAndView("index");
		List<Admin> list = service.getAdminList();
		View view = new AdminList(list);
		ret.addObject("adminList", view);
		return ret;
	}

	@RequestMapping(value = "/create-admin", method = RequestMethod.GET)
	public ModelAndView createAdminGet() throws Exception {
		ModelAndView ret = createNormalModelAndView("create-admin");
		return ret;
	}

	@RequestMapping(value = "/create-admin", method = RequestMethod.POST)
	public ModelAndView createAdminPost(String name, String pwd) throws Exception {
		service.addAdmin(name, pwd);
		return index();
	}
}
