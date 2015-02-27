package com.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.service.Const;
import com.web.auth.Auth;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	// private static Logger logger = Logger.getLogger(AuthInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {

		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			Auth auth = ((HandlerMethod) handler).getMethodAnnotation(Auth.class);
			// 默认不要验证
			if (auth == null || auth.validate() == false) {
				return true;
			}
			if (hasLogin(request, response)) {
				return true;
			}
			String redirectUrl = request.getContextPath() + "/account/login";
			response.sendRedirect(redirectUrl);
			return false;
		} else {
			return true;
		}
	}

	private boolean hasLogin(HttpServletRequest request, HttpServletResponse response) {
		if (Const.SES_LOGIN.equals(request.getSession().getAttribute(Const.SES_LOGIN))) {
			return true;
		}
		return false;
	}
}