package com.web.service;

import java.util.List;

import com.util.Util;
import com.web.dao.entity.Admin;
import com.web.dao.impl.AdminDao;
import com.web.interceptor.context.UserContext;
import com.wechat.Const;

public class SuperAdminService {

	public boolean isLogin() {
		if (Const.AUTH_SA_LOGIN.equals(UserContext.current().getRequest().getSession()
				.getAttribute(Const.AUTH_SA_LOGIN))) {
			return true;
		}
		return false;
	}

	public boolean login(String user, String pwd) {
		if (user.equals("sa") && pwd.equals("111111")) {
			UserContext.current().getRequest().getSession()
					.setAttribute(Const.AUTH_SA_LOGIN, Const.AUTH_SA_LOGIN);
			return true;
		}
		return false;
	}

	static void logout() {
		UserContext.current().getRequest().getSession().removeAttribute(Const.AUTH_SA_LOGIN);
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

	public void deleteAdmin(int id) {
		AdminDao.delete(id);
	}

	public void deleteAdmin(Admin admin) {
		deleteAdmin(admin.getId());
	}
}
