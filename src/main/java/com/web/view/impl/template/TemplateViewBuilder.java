package com.web.view.impl.template;

public class TemplateViewBuilder {

	private String html;

	TemplateViewBuilder(String html) {
		this.html = html;
	}

	public TemplateViewBuilder setParam(String key, Object value) {
		html = html.replaceAll("\\$" + key, value.toString());
		return this;
	}

	public TemplateView build() {
		return new TemplateView(html);
	}
}
