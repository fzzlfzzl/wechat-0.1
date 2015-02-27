package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.controller.base.WebController;

@Controller
@RequestMapping("/sa")
public class SuperAdminController extends WebController {

	// private static Logger logger =
	// Logger.getLogger(SuperAdminController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginGet() throws Exception {
		ModelAndView ret = createNormalModelAndView("login");
		return ret;
	}

}
