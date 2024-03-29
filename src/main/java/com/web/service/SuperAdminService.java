package com.web.service;

import java.util.List;

import com.service.wechat.WechatHelper;
import com.service.wechat.Const.Auth;
import com.service.wechat.menu.MenuManager;
import com.site.util.Util;
import com.web.dao.entity.Admin;
import com.web.dao.impl.AdminDao;
import com.web.interceptor.context.UserContext;

public class SuperAdminService {

	public boolean isLogin() {
		if (Auth.SA_LOGIN.equals(UserContext.current().getRequest().getSession().getAttribute(Auth.SA_LOGIN))) {
			return true;
		}
		return false;
	}

	public boolean login(String user, String pwd) {
		if (user.equals("sa") && pwd.equals("111111")) {
			UserContext.current().getRequest().getSession().setAttribute(Auth.SA_LOGIN, Auth.SA_LOGIN);
			return true;
		}
		return false;
	}

	static void logout() {
		UserContext.current().getRequest().getSession().removeAttribute(Auth.SA_LOGIN);
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

	public void deleteAdmin(long id) {
		AdminDao.delete(id);
	}

	public void deleteAdmin(Admin admin) {
		deleteAdmin(admin.getId());
	}

	public void registMenu() {
		WechatHelper.registMenu(MenuManager.getRequest());
	}
}
