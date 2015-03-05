package com.web.service;

import com.service.wechat.Const.Auth;
import com.site.util.Util;
import com.web.dao.entity.Admin;
import com.web.dao.impl.AdminDao;
import com.web.interceptor.context.UserContext;

public class AdminService {

	public boolean isLogin() {
		if (Auth.LOGIN.equals(UserContext.current().getRequest().getSession()
				.getAttribute(Auth.LOGIN))) {
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
			UserContext.current().getRequest().getSession().setAttribute(Auth.LOGIN, Auth.LOGIN);
			return true;
		} else {
			return false;
		}

	}

	public void logout() {
		UserContext.current().getRequest().getSession().removeAttribute(Auth.LOGIN);
	}

}
