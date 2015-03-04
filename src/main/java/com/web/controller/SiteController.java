package com.web.controller;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.site.util.ExceptionLogger;
import com.web.controller.base.WebController;
import com.web.dao.db.HibernateUtil;

@Controller
@RequestMapping("/site")
public class SiteController extends WebController {

	private static Logger logger = Logger.getLogger(SiteController.class);

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() throws Exception {
		ModelAndView ret = createNormalModelAndView("index");
		try {
			Session session = HibernateUtil.openSession();
			session.close();
			ret.addObject("result", "succ");
		} catch (Exception e) {
			logger.error(new ExceptionLogger(e));
			ret.addObject("result", "err");
		}
		return ret;
	}
}
