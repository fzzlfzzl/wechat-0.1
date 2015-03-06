package com.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.controller.base.WebController;
import com.web.dao.entity.User;
import com.web.dao.impl.UserDao;
import com.web.interceptor.annotation.AuthAdmin;
import com.web.interceptor.context.UserContextPool;
import com.web.service.AdminService;
import com.web.view.View;
import com.web.view.site.admin.MessageListView;
import com.web.view.site.admin.UserListView;

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
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public ModelAndView dft(String user, String pwd) throws Exception {
		return createRedirectModelAndView("index");
	}

	@AuthAdmin
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(String user, String pwd) throws Exception {
		UserDao dao = new UserDao(UserContextPool.current().getSession());
		List<User> userList = dao.list();
		View view = new UserListView(userList);
		ModelAndView ret = createNormalModelAndView("index");
		ret.addObject("userList", view);
		return ret;
	}

	@AuthAdmin
	@RequestMapping(value = "/user/{openid}", method = RequestMethod.GET)
	public ModelAndView user(@PathVariable String openid) throws Exception {
		UserDao dao = new UserDao(UserContextPool.current().getSession());
		User user = dao.get(openid);
		View view = new MessageListView(dao.getMessages(user));
		ModelAndView ret = createNormalModelAndView("user");
		ret.addObject("messageList", view);
		ret.addObject("openid", openid);
		return ret;
	}
}
