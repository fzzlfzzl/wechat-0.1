package com.web.interceptor.context;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplicationContext implements IApplicationContext {

	// private static Logger logger =
	// Logger.getLogger(ControllerViewInterceptor.class);
	private static ConcurrentHashMap<Long, ApplicationContext> contextMap = new ConcurrentHashMap<Long, ApplicationContext>();

	private HttpServletRequest request;
	private HttpServletResponse response;

	public static IApplicationContext current() {
		long key = Thread.currentThread().getId();
		return contextMap.get(key);
	}

	public static boolean preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long key = Thread.currentThread().getId();
		ApplicationContext context = new ApplicationContext();
		context.setRequest(request);
		context.setResponse(response);
		contextMap.put(key, context);
		return true;
	}

	public static void postHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long key = Thread.currentThread().getId();
		contextMap.remove(key);
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	private void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	private void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}