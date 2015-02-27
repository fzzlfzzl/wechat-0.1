package com.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.web.context.ApplicationContext;

public class ApplicationContextInterceptor extends HandlerInterceptorAdapter {

	// private static Logger logger =
	// Logger.getLogger(ControllerViewInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		ApplicationContext.preHandle(request, response);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		ApplicationContext.postHandle(request, response);
	}

}