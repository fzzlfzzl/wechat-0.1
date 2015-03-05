package com.web.view.impl.composite;

public class CssClass {

	private String cssClass = null;

	void setClass(String cssClass) {
		this.cssClass = cssClass;
	}

	void addClass(String cssClass) {
		if (this.cssClass == null) {
			setClass(cssClass);
		} else {
			this.cssClass = this.cssClass + " " + cssClass;
		}
	}

	boolean isNull() {
		return cssClass == null;
	}

	@Override
	public String toString() {
		return cssClass;
	}

}
