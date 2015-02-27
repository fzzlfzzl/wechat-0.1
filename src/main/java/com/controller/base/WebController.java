package com.controller.base;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class WebController {

	protected String thisView(String view) {
		RequestMapping rm = this.getClass().getAnnotation(RequestMapping.class);
		if (rm == null) {
			throw new RuntimeException("No RequestMapping");
		}
		String[] value = rm.value();
		if (value.length != 1) {
			throw new RuntimeException("Invalid Mapping");
		}
		String controller = value[0].substring(1);
		String ret = String.format("%s/%s", controller, view);
		return ret;
	}

	protected ModelAndView createNormalModelAndView(String view) {
		if (isSelfView(view)) {
			return new ModelAndView(view);
		} else {
			return new ModelAndView(thisView(view));
		}
	}

	protected ModelAndView createRedirectModelAndView(String view) {
		if (isSelfView(view)) {
			return new ModelAndView("redirect:" + view);
		} else {
			return null;
		}
	}

	private boolean isSelfView(String view) {
		return view.indexOf("/") > 0;
	}
}
