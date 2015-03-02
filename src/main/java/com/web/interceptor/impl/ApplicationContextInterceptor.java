package com.web.interceptor.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.web.interceptor.context.UserContextHandler;

public class ApplicationContextInterceptor extends HandlerInterceptorAdapter {

	// private static Logger logger =
	// Logger.getLogger(ControllerViewInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		UserContextHandler.preHandle(request, response);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		UserContextHandler.postHandle(request, response);
	}

}