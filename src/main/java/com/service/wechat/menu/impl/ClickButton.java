package com.service.wechat.menu.impl;

import com.service.wechat.menu.IButton;
import com.site.util.JsonObject;

public class ClickButton implements IButton {

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
