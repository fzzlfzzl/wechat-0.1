package com.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.controller.base.WebController;
import com.web.dao.entity.Admin;
import com.web.interceptor.annotation.AuthSuperAdmin;
import com.web.service.SuperAdminService;
import com.web.view.View;
import com.web.view.site.admin.AdminListView;

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
		if (service.login(user, pwd)) {
			return createRedirectModelAndView("index");
		} else {
			return loginGet();
		}
	}

	@AuthSuperAdmin
	@RequestMapping(value = { "/index", "", "/" }, method = RequestMethod.GET)
	public ModelAndView index() throws Exception {
		ModelAndView ret = createNormalModelAndView("index");
		List<Admin> list = service.getAdminList();
		View view = new AdminListView(list);
		ret.addObject("adminListView", view);
		return ret;
	}

	@AuthSuperAdmin
	@RequestMapping(value = "/create-admin", method = RequestMethod.GET)
	public ModelAndView createAdminGet() throws Exception {
		ModelAndView ret = createNormalModelAndView("create-admin");
		return ret;
	}

	@AuthSuperAdmin
	@RequestMapping(value = "/create-admin", method = RequestMethod.POST)
	public ModelAndView createAdminPost(String name, String pwd) throws Exception {
		service.addAdmin(name, pwd);
		return createRedirectModelAndView("index");
	}

	@AuthSuperAdmin
	@RequestMapping(value = "/delete-admin/{id}", method = RequestMethod.GET)
	public ModelAndView deleteAdmin(@PathVariable Integer id) throws Exception {
		service.deleteAdmin(id);
		return createRedirectModelAndView("index");
	}

}
