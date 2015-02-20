package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dao.bean.Address;
import com.dao.bean.User;
import com.dao.db.HibernateUtil;

@Controller
@RequestMapping("/site")
public class SiteController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView ret = new ModelAndView("index");
		try {
			Session session = HibernateUtil.openSession();
			session.beginTransaction();
			User user = new User();
			Address addr = new Address();
			addr.setAddress("" + System.currentTimeMillis());
			user.getAddressList().add(addr);
			session.getTransaction().commit();
			session.close();
			ret.addObject("result", "succ");
		} catch (Exception e) {
			ret.addObject("result", "err");
		}
		return ret;
	}
}
