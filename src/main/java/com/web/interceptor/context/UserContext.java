package com.web.interceptor.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

public class UserContext {

	// private static Logger logger =
	// Logger.getLogger(ControllerViewInterceptor.class);

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Session session;

	public UserContext(HttpServletRequest request, HttpServletResponse response, Session session) {
		this.request = request;
		this.response = response;
		this.session = session;
	}

	public static UserContext current() {
		return UserContextPool.current();
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Session getSession() {
		return session;
	}
}