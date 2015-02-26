package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dao.db.HibernateUtil;
import com.dao.entity.User;
import com.util.ExceptionLogger;

@Controller
@RequestMapping("/site")
public class SiteController {

	private static Logger logger = Logger.getLogger(SiteController.class);

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView ret = new ModelAndView("index");
		try {
			Session session = HibernateUtil.openSession();
			session.beginTransaction();
			User user = new User();
			user.setOpenId("openid");
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
