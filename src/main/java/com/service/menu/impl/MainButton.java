package com.service.menu.impl;

import java.util.ArrayList;
import java.util.List;

import com.service.menu.IMainButton;
import com.service.menu.ISubButton;
import com.util.JsonObject;

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
