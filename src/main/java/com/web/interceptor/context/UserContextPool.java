package com.web.interceptor.context;

import java.util.concurrent.ConcurrentHashMap;

public class UserContextPool {

	private static ConcurrentHashMap<Long, UserContext> contextMap = new ConcurrentHashMap<Long, UserContext>();

	public static UserContext current() {
		long key = Thread.currentThread().getId();
		return contextMap.get(key);
	}

	public static boolean put(UserContext context) {
		long key = Thread.currentThread().getId();
		contextMap.put(key, context);
		return true;
	}

	public static void remove() throws Exception {
		long key = Thread.currentThread().getId();
		contextMap.remove(key);
	}
}
