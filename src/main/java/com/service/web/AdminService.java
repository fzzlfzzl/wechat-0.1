package com.service.web;

import com.dao.entity.Admin;
import com.dao.impl.AdminDao;
import com.service.Const;
import com.util.Util;
import com.web.context.ApplicationContext;

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
		if (Util.sha1(pwd).equals(admin.getPassword())) {
			ApplicationContext.current().getRequest().getSession()
					.setAttribute(Const.AUTH_LOGIN, Const.AUTH_LOGIN);
			return true;
		} else {
			return false;
		}

	}

	public void logout() {
		ApplicationContext.current().getRequest().getSession().removeAttribute(Const.AUTH_LOGIN);
	}

}
