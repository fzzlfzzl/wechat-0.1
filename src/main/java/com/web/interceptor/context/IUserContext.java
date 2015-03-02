package com.web.interceptor.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IUserContext {

	public HttpServletRequest getRequest();

	public HttpServletResponse getResponse();

}