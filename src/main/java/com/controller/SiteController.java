package com.controller;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.controller.base.WebController;
import com.dao.db.HibernateUtil;
import com.dao.entity.User;
import com.util.ExceptionLogger;
import com.web.auth.Auth;

@Controller
@RequestMapping("/site")
public class SiteController extends WebController {

	private static Logger logger = Logger.getLogger(SiteController.class);

	@Auth
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() throws Exception {
		ModelAndView ret = createNormalModelAndView("index");
		try {
			Session session = HibernateUtil.openSession();
			session.beginTransaction();
			User user = new User();
			user.setOpenId("" + System.currentTimeMillis());
			session.save(user);
			session.getTransaction().commit();
			session.close();
			ret.addObject("result", "succ");
		} catch (Exception e) {
			logger.error(new ExceptionLogger(e));
			ret.addObject("result", "err");
		}
		return ret;
	}
}
