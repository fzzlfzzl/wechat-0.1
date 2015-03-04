package com.wechat.menu.impl;

import java.util.ArrayList;
import java.util.List;

import com.site.util.JsonObject;
import com.wechat.menu.IMainButton;
import com.wechat.menu.ISubButton;

public class MainButton implements IMainButton {

	private List<ISubButton> list = new ArrayList<ISubButton>();

	@Override
	public JsonObject toJsonObject() {
		return null;
	}

	public void addChild(ISubButton child) {
		list.add(child);
	}
}
