package com.web.view.site.admin;

import java.util.List;

import com.web.dao.entity.User;
import com.web.view.View;
import com.web.view.impl.template.TemplateViewExpression;

public class UserListView extends View {

	private List<User> list = null;
	private String tpl = "<li><a href='admin/$openid'>$openid</a></li>";
	private TemplateViewExpression exp = TemplateViewExpression.compile(tpl);

	public UserListView(List<User> list) {
		this.list = list;
	}

	@Override
	public void render(StringBuffer sb) {
		sb.append("<ul>");
		for (User user : list) {
			View view = exp.createBuilder().setParam("openid", user.getOpenId()).build();
			view.render(sb);
		}
		sb.append("</ul>");
	}

}
