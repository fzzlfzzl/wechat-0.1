package com.wechat.menu.impl;

import com.site.util.JsonObject;
import com.wechat.menu.ISubButton;

public class ClickButton implements ISubButton {

	private String name;
	private String type = TYPE_CLICK;
	private String key;

	public ClickButton(String name, String key) {
		this.name = name;
		this.key = key;
	}

	@Override
	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.get("type").set(type);
		obj.get("name").set(name);
		obj.get("key").set(key);
		return obj;
	}

}
