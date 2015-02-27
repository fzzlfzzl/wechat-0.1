package com.web.auth;

import com.service.Const;
import com.web.context.ApplicationContext;

public class AuthUtil {

	public static boolean isLogin() {
		if (Const.AUTH_LOGIN.equals(ApplicationContext.current().getRequest().getSession()
				.getAttribute(Const.AUTH_LOGIN))) {
			return true;
		}
		return false;
	}

	public static void login() {
		ApplicationContext.current().getRequest().getSession().setAttribute(Const.AUTH_LOGIN, Const.AUTH_LOGIN);
	}

	public static void logout() {
		ApplicationContext.current().getRequest().getSession().removeAttribute(Const.AUTH_LOGIN);
	}

	public static boolean isSaLogin() {
		if (Const.AUTH_SA_LOGIN.equals(ApplicationContext.current().getRequest().getSession()
				.getAttribute(Const.AUTH_SA_LOGIN))) {
			return true;
		}
		return false;
	}

	public static void saLogin() {
		ApplicationContext.current().getRequest().getSession().setAttribute(Const.AUTH_SA_LOGIN, Const.AUTH_SA_LOGIN);
	}

	public static void saLogout() {
		ApplicationContext.current().getRequest().getSession().removeAttribute(Const.AUTH_SA_LOGIN);
	}
}
