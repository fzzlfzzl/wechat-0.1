package com.web.html;

import java.util.ArrayList;
import java.util.List;

import com.view.View;

public class HtmlArray extends View {

	private List<HtmlTag> tags = new ArrayList<HtmlTag>();

	public void add(HtmlTag tag) {
		tags.add(tag);
	}

	@Override
	public void render(StringBuffer sb) {
		for (HtmlTag tag : tags) {
			tag.render(sb);
		}
	}
}
