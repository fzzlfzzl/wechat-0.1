package com.web.view.site.sa;

import java.util.List;

import com.web.dao.entity.Admin;
import com.web.view.View;
import com.web.view.impl.composite.HtmlTag;
import com.web.view.impl.template.TemplateViewExpression;

public class AdminListView extends View {

	private static String html = "<li>$name<a href='delete-admin/$id'>delete</a></li>";
	private static TemplateViewExpression exp = TemplateViewExpression.compile(html);
	private List<Admin> list;

	public AdminListView(List<Admin> list) {
		this.list = list;
	}

	@Override
	public void render(StringBuffer sb) {
		HtmlTag ul = HtmlTag.ul();
		for (Admin admin : list) {
			View li = exp.createBuilder().setParam("name", admin.getName())
					.setParam("id", admin.getId()).build();
			ul.addChild(li);
		}
		ul.render(sb);
	}
}
