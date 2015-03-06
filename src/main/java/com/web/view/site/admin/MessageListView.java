package com.web.view.site.admin;

import java.util.List;

import com.web.dao.entity.Message;
import com.web.view.View;
import com.web.view.impl.composite.HtmlTag;

public class MessageListView extends View {

	private List<Message> list = null;

	public MessageListView(List<Message> list) {
		this.list = list;
	}

	private View th() {
		HtmlTag tr = HtmlTag.tr();
		HtmlTag th = null;
		String[] content = { "time", "msgtype", "content", "eventkey", "key" };
		for (String s : content) {
			th = HtmlTag.th();
			th.setContent(s);
			tr.addChild(th);
		}
		return tr;
	}

	private View tr(Message m) {
		HtmlTag tr = HtmlTag.tr();
		HtmlTag td = null;
		String[] content = { m.getCreateTimeString(), m.getMsgType(), m.getContent(), m.getEvent(),
				m.getEventKey() };
		for (String s : content) {
			td = HtmlTag.td();
			td.setContent(s);
			tr.addChild(td);
		}
		return tr;
	}

	@Override
	public void render(StringBuffer sb) {
		HtmlTag table = HtmlTag.table();
		table.addChild(th());
		for (Message m : list) {
			table.addChild(tr(m));
		}
		table.render(sb);
	}

}
