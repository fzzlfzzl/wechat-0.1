package com.web.view.template;

public class TemplateViewExpression {

	private String html;

	private TemplateViewExpression(String html) {
		this.html = html;
	}

	public TemplateViewBuilder createBuilder() {
		return new TemplateViewBuilder(html);
	}

	public static TemplateViewExpression compile(String html) {
		return new TemplateViewExpression(html);
	}
}
