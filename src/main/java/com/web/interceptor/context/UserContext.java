package com.web.interceptor.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserContext implements IUserContext {

	// private static Logger logger =
	// Logger.getLogger(ControllerViewInterceptor.class);

	private HttpServletRequest request;
	private HttpServletResponse response;

	public static IUserContext current() {
		return UserContextPool.get();
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}