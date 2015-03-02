package com.web.view.template;

public class TemplateViewBuilder {

	private String html;

	TemplateViewBuilder(String html) {
		this.html = html;
	}

	public TemplateViewBuilder setParam(String key, String value) {
		html = html.replaceAll("\\$" + key, value);
		return this;
	}

	public TemplateView build() {
		return new TemplateView(html);
	}
}
