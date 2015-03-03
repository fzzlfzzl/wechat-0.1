package com.wechat.menu.impl;

import com.util.JsonObject;
import com.wechat.menu.ISubButton;

public class ViewButton implements ISubButton {

	private String name;
	private String type = TYPE_VIEW;
	private String url;

	public ViewButton(String name, String url) {
		this.name = name;
		this.url = url;
	}

	@Override
	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.get("type").set(type);
		obj.get("name").set(name);
		obj.get("url").set(url);
		return obj;
	}

}
