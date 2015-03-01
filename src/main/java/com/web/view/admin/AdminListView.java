package com.web.view.admin;

import java.util.List;

import com.web.dao.entity.Admin;
import com.web.view.View;
import com.web.view.html.HtmlTag;

public class AdminListView extends View {

	private List<Admin> list;

	public AdminListView(List<Admin> list) {
		this.list = list;
	}

	@Override
	public void render(StringBuffer sb) {
		HtmlTag ul = HtmlTag.ul();
		for (Admin admin : list) {
			HtmlTag li = HtmlTag.li();
			li.setContent(admin.getName());
			HtmlTag a = HtmlTag.a("delete-admin/" + admin.getId());
			a.setContent("delete");
			li.addChild(a);
			ul.addChild(li);
		}
		ul.render(sb);
	}
}
