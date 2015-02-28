package com.service.web;

import java.util.List;

import com.dao.entity.Admin;
import com.dao.impl.AdminDao;
import com.service.Const;
import com.util.Util;
import com.web.context.ApplicationContext;

public class SuperAdminService {

	public boolean validate(String user, String pwd) {
		if (user.equals("sa") && pwd.equals("111111"))
			return true;
		return false;
	}

	public boolean isLogin() {
		if (Const.AUTH_SA_LOGIN.equals(ApplicationContext.current().getRequest().getSession()
				.getAttribute(Const.AUTH_SA_LOGIN))) {
			return true;
		}
		return false;
	}

	public void login() {
		ApplicationContext.current().getRequest().getSession()
				.setAttribute(Const.AUTH_SA_LOGIN, Const.AUTH_SA_LOGIN);
	}

	static void logout() {
		ApplicationContext.current().getRequest().getSession().removeAttribute(Const.AUTH_SA_LOGIN);
	}

	public List<Admin> getAdminList() {
		return AdminDao.list();
	}

	public void addAdmin(String name, String pwd) {
		Admin admin = new Admin();
		admin.setName(name);
		admin.setPassword(Util.sha1(pwd));
		AdminDao.save(admin);
	}
}
