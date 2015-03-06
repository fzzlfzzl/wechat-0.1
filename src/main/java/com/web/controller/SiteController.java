package com.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.site.util.ExceptionLogger;
import com.web.controller.base.WebController;
import com.web.dao.db.HibernateUtil;
import com.web.dao.entity.Message;
import com.web.view.View;
import com.web.view.site.admin.MessageListView;

@Controller
@RequestMapping("/site")
public class SiteController extends WebController {

	private static Logger logger = Logger.getLogger(SiteController.class);

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() throws Exception {
		ModelAndView ret = createNormalModelAndView("index");
		try {
			Session session = HibernateUtil.openSession();
			@SuppressWarnings("unchecked")
			List<Message> list = session.createQuery("from Message").list();
			View view = new MessageListView(list);
			ret.addObject("result", "succ");
			ret.addObject("text", "中文"); 
			ret.addObject("messageList", view);
			session.close();
		} catch (Exception e) {
			logger.error(new ExceptionLogger(e));
			ret.addObject("result", "err");
		}
		return ret;
	}
}
