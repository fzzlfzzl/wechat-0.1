package com.web.interceptor.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.web.interceptor.annotation.AuthAdmin;
import com.web.interceptor.annotation.AuthSuperAdmin;
import com.web.service.AdminService;
import com.web.service.SuperAdminService;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	// private static Logger logger = Logger.getLogger(AuthInterceptor.class);
	private AdminService adminService = new AdminService();
	private SuperAdminService saService = new SuperAdminService();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			return true;
		}
		if (!authAdmin(request, response, handler)) {
			redirect(request, response, "/admin/login");
			return false;
		}
		if (!authSuperAdmin(request, response, handler)) {
			redirect(request, response, "/sa/login");
			return false;
		}
		return true;
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
		String redirectUrl = request.getContextPath() + url;
		response.sendRedirect(redirectUrl);
	}

	private boolean authAdmin(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws IOException {
		AuthAdmin auth = ((HandlerMethod) handler).getMethodAnnotation(AuthAdmin.class);
		// 默认不要验证
		if (auth == null || auth.validate() == false) {
			return true;
		}
		if (adminService.isLogin()) {
			return true;
		}
		return false;
	}

	private boolean authSuperAdmin(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws IOException {
		AuthSuperAdmin auth = ((HandlerMethod) handler).getMethodAnnotation(AuthSuperAdmin.class);
		// 默认不要验证
		if (auth == null || auth.validate() == false) {
			return true;
		}
		if (saService.isLogin()) {
			return true;
		}
		return false;
	}
}
