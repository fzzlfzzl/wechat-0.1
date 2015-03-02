package com.web.interceptor.context;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserContextPool {

	private static ConcurrentHashMap<Long, UserContext> contextMap = new ConcurrentHashMap<Long, UserContext>();

	public static IUserContext get() {
		long key = Thread.currentThread().getId();
		return contextMap.get(key);
	}

	public static boolean put(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		long key = Thread.currentThread().getId();
		UserContext context = new UserContext();
		context.setRequest(request);
		context.setResponse(response);
		contextMap.put(key, context);
		return true;
	}

	public static void remove() throws Exception {
		long key = Thread.currentThread().getId();
		contextMap.remove(key);
	}
}
