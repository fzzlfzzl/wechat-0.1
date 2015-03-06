package com.web.view.impl.composite;

import java.util.ArrayList;
import java.util.List;

import com.web.view.View;

public class HtmlTag extends CompositeView {

	private String tag;
	private String type;
	private String id;
	private CssClass css = new CssClass();
	private String href;
	private String name;
	private String value;
	private String target;
	private String content;

	private List<View> children = new ArrayList<View>();

	protected void setTag(String tag) {
		this.tag = tag;
	}

	public void setClass(String cssClass) {
		css.setClass(cssClass);
	}

	public void addClass(String cssClass) {
		css.addClass(cssClass);
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public void addChild(View child) {
		this.children.add(child);
	}

	/**
	 * @param sb
	 */

	private void renderTagHead(StringBuffer sb) {
		String head = String.format("<%s", tag);
		sb.append(head);
		renderAttribute(sb, "type", type);
		renderAttribute(sb, "id", id);
		renderClass(sb);
		renderAttribute(sb, "href", href);
		renderAttribute(sb, "target", target);
		renderAttribute(sb, "name", name);
		renderAttribute(sb, "value", value);
		sb.append(">");
	}

	private void renderChildren(StringBuffer sb) {
		for (View child : children) {
			child.render(sb);
		}
	}

	private void renderTagTail(StringBuffer sb) {
		String tail = String.format("</%s>", tag);
		sb.append(tail);
	}

	private void renderClass(StringBuffer sb) {
		if (css.isNull())
			return;
		String append = String.format(" class='%s'", css);
		sb.append(append);
	}

	private void renderAttribute(StringBuffer sb, String key, String value) {
		if (value == null) {
			return;
		}
		String append = String.format(" %s='%s'", key, value);
		sb.append(append);
	}

	private void renderContent(StringBuffer sb) {
		if (content == null)
			return;
		sb.append(content);
	}

	@Override
	public void render(StringBuffer sb) {
		valid();
		renderTagHead(sb);
		renderContent(sb);
		renderChildren(sb);
		renderTagTail(sb);
	}

	private void valid() {
		if (null == tag) {
			throw new RuntimeException("Tag Not Set");
		}
	}

	/**
	 * @return
	 */

	public static Html html() {
		return new Html();
	}

	public static class Html extends HtmlTag {
		Html() {
			setTag("html");
		}
	}

	public static Head head() {
		return new Head();
	}

	public static class Head extends HtmlTag {
		Head() {
			setTag("head");
		}
	}

	public static Body body() {
		return new Body();
	}

	public static class Body extends HtmlTag {
		Body() {
			setTag("body");
		}
	}

	public static Div div() {
		return new Div();
	}

	public static class Div extends HtmlTag {
		Div() {
			setTag("div");
		}
	}

	public static Input input() {
		return new Input();
	}

	public static class Input extends HtmlTag {
		Input() {
			setTag("input");
		}
	}

	public static InputText inputText(String name) {
		InputText ret = new InputText();
		ret.setName(name);
		return ret;
	}

	public static class InputText extends HtmlTag {
		InputText() {
			setTag("input");
			setType("text");
		}
	}

	public static InputPassword inputPassword(String name) {
		InputPassword ret = new InputPassword();
		ret.setName(name);
		return ret;
	}

	public static class InputPassword extends HtmlTag {
		InputPassword() {
			setTag("input");
			setType("password");
		}
	}

	public static Submit submit(String value) {
		Submit ret = new Submit();
		ret.setValue(value);
		return ret;
	}

	public static class Submit extends HtmlTag {
		Submit() {
			setTag("input");
			setType("submit");
		}
	}

	public static A a(String href) {
		A ret = new A();
		ret.setHref(href);
		return ret;
	}

	public static class A extends HtmlTag {
		A() {
			setTag("a");
		}
	}

	public static Ul ul() {
		Ul ret = new Ul();
		return ret;
	}

	public static class Ul extends HtmlTag {
		Ul() {
			setTag("ul");
		}
	}

	public static Li li() {
		Li ret = new Li();
		return ret;
	}

	public static class Li extends HtmlTag {
		Li() {
			setTag("li");
		}
	}

	public static Table table() {
		Table ret = new Table();
		return ret;
	}

	public static class Table extends HtmlTag {
		Table() {
			setTag("table");
		}
	}

	public static Tr tr() {
		Tr ret = new Tr();
		return ret;
	}

	public static class Tr extends HtmlTag {
		Tr() {
			setTag("tr");
		}
	}

	public static Td td() {
		Td ret = new Td();
		return ret;
	}

	public static class Td extends HtmlTag {
		Td() {
			setTag("td");
		}
	}

	public static Th th() {
		Th ret = new Th();
		return ret;
	}

	public static class Th extends HtmlTag {
		Th() {
			setTag("th");
		}
	}
}
