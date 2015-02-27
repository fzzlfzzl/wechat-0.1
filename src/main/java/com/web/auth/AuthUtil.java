package com.web.auth;

import com.service.Const;
import com.web.context.ApplicationContext;

public class AuthUtil {

	public static boolean isLogin() {
		if (Const.SES_LOGIN
				.equals(ApplicationContext.current().getRequest().getSession().getAttribute(Const.SES_LOGIN))) {
			return true;
		}
		return false;
	}

	public static void login() {
		ApplicationContext.current().getRequest().getSession().setAttribute(Const.SES_LOGIN, Const.SES_LOGIN);
	}

	public static void logout() {
		ApplicationContext.current().getRequest().getSession().removeAttribute(Const.SES_LOGIN);

	}
}
