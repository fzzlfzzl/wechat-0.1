package com.web.interceptor.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.web.dao.db.HibernateUtil;
import com.web.interceptor.context.UserContext;
import com.web.interceptor.context.UserContextPool;

public class UserContextInterceptor extends HandlerInterceptorAdapter {

	// private static Logger logger =
	// Logger.getLogger(UserContextInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		Session session = HibernateUtil.openSession();
		UserContext context = new UserContext(request, response, session);
		UserContextPool.put(context);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {
		UserContextPool.current().getSession().close();
		UserContextPool.remove();
	}

}