package com.controller.base;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.context.ApplicationContext;
import com.web.context.IApplicationContext;

public class WebController {

	protected String thisController() {
		RequestMapping rm = this.getClass().getAnnotation(RequestMapping.class);
		if (rm == null) {
			throw new RuntimeException("No RequestMapping");
		}
		String[] value = rm.value();
		if (value.length != 1) {
			throw new RuntimeException("Invalid Mapping");
		}
		String controller = value[0].substring(1);
		return controller;
	}

	protected String thisView(String view) {
		String controller = thisController();
		String ret = String.format("%s/%s", controller, view);
		return ret;
	}

	protected ModelAndView createNormalModelAndView(String view) {
		if (isSelfView(view)) {
			return new ModelAndView(thisView(view));
		} else {
			return new ModelAndView(view);
		}
	}

	protected ModelAndView createRedirectModelAndView(String view) {
		IApplicationContext context = ApplicationContext.current();
		String url = null;
		if (isSelfView(view)) {
			url = String.format("%s/%s/%s", context.getRequest().getContextPath(), thisController(), view);

		} else {
			url = String.format("%s/%s", context.getRequest().getContextPath(), view);
		}
		try {
			context.getResponse().sendRedirect(url);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	protected ModelAndView createForwardGetModelAndView(String view) {
		String url = String.format("forward:%s", view);
		return new ModelAndView(url);
	}

	private boolean isSelfView(String view) {
		return view.indexOf("/") <= 0;
	}
}
