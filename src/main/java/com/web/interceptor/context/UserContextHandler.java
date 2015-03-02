package com.web.interceptor.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserContextHandler {

	public static boolean preHandle(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserContextPool.put(request, response);
		return true;
	}

	public static void postHandle(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserContextPool.remove();
	}

}