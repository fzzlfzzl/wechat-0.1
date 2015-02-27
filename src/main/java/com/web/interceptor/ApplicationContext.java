package com.web.interceptor;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ApplicationContext extends HandlerInterceptorAdapter {

	// private static Logger logger =
	// Logger.getLogger(ControllerViewInterceptor.class);
	private static ConcurrentHashMap<Long, Context> contextMap = new ConcurrentHashMap<Long, ApplicationContext.Context>();

	public static Context getContext() {
		long key = Thread.currentThread().getId();
		return contextMap.get(key);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		long key = Thread.currentThread().getId();
		Context context = new Context(request, response);
		contextMap.put(key, context);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		long key = Thread.currentThread().getId();
		contextMap.remove(key);
	}

	public static class Context {
		private HttpServletRequest request;
		private HttpServletResponse response;

		public Context(HttpServletRequest request, HttpServletResponse response) {
			this.request = request;
			this.response = response;
		}

		public HttpServletRequest getRequest() {
			return this.request;
		}

		public HttpServletResponse getResponse() {
			return this.response;
		}
	}
}