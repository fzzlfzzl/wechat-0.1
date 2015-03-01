package com.web.service;

import com.service.Const;
import com.util.Util;
import com.web.dao.entity.Admin;
import com.web.dao.impl.AdminDao;
import com.web.interceptor.context.ApplicationContext;

public class AdminService {

	public boolean isLogin() {
		if (Const.AUTH_LOGIN.equals(ApplicationContext.current().getRequest().getSession()
				.getAttribute(Const.AUTH_LOGIN))) {
			return true;
		}
		return false;
	}

	public boolean login(String name, String pwd) {
		Admin admin = AdminDao.load(name);
		if (admin == null) {
			return false;
		}
		if (Util.sha1(pwd).equals(admin.getPassword())) {
			ApplicationContext.current().getRequest().getSession().setAttribute(Const.AUTH_LOGIN, Const.AUTH_LOGIN);
			return true;
		} else {
			return false;
		}

	}

	public void logout() {
		ApplicationContext.current().getRequest().getSession().removeAttribute(Const.AUTH_LOGIN);
	}

}
