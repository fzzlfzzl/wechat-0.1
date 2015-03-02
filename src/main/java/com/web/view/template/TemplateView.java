package com.web.view.template;

import com.web.view.View;

public class TemplateView extends View {

	private String html;

	public TemplateView(String html) {
		this.html = html;
	}

	@Override
	public void render(StringBuffer sb) {
		sb.append(html);
	}

}
