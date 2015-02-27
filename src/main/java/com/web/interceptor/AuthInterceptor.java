package com.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.web.auth.Auth;
import com.web.auth.AuthUtil;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	// private static Logger logger = Logger.getLogger(AuthInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			Auth auth = ((HandlerMethod) handler).getMethodAnnotation(Auth.class);
			// 默认不要验证
			if (auth == null || auth.validate() == false) {
				return true;
			}
			if (AuthUtil.isLogin()) {
				return true;
			}
			String redirectUrl = request.getContextPath() + "/account/login";
			response.sendRedirect(redirectUrl);
			return false;
		} else {
			return true;
		}
	}

}