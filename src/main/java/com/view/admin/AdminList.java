package com.view.admin;

import java.util.List;

import com.dao.entity.Admin;
import com.view.View;
import com.web.html.HtmlTag;

public class AdminList extends View {

	private List<Admin> list;

	public AdminList(List<Admin> list) {
		this.list = list;
	}

	@Override
	public void render(StringBuffer sb) {
		HtmlTag ul = HtmlTag.ul();
		for (Admin admin : list) {
			HtmlTag li = HtmlTag.li();
			li.setContent(admin.getName());
			ul.addChild(li);
		}
		ul.render(sb);
	}
}
