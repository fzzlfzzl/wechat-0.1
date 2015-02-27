package com.web.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IApplicationContext {

	public HttpServletRequest getRequest();

	public HttpServletResponse getResponse();

}