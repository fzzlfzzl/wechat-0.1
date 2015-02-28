package com.web.html;

import java.util.ArrayList;
import java.util.List;

public class HtmlArray implements IRenderable {

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

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		render(sb);
		return sb.toString();
	}

}
