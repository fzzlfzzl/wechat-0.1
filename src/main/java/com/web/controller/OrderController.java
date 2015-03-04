package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.controller.base.WebController;

@Controller
@RequestMapping("/order")
public class OrderController extends WebController {

	// private static Logger logger = Logger.getLogger(AccountController.class);

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(String openid) throws Exception {
		ModelAndView ret = createNormalModelAndView("index");
		ret.addObject("openid", openid);
		return ret;
	}
}
