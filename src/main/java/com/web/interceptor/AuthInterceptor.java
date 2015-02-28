package com.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.service.web.AdminService;
import com.web.auth.AuthAdmin;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	// private static Logger logger = Logger.getLogger(AuthInterceptor.class);
	private AdminService adminService = new AdminService();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {

		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			AuthAdmin auth = ((HandlerMethod) handler).getMethodAnnotation(AuthAdmin.class);
			// 默认不要验证
			if (auth == null || auth.validate() == false) {
				return true;
			}
			if (adminService.isLogin()) {
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