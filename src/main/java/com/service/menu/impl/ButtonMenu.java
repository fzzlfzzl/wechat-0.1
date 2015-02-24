package com.service.menu.impl;

import java.util.ArrayList;
import java.util.List;

import com.service.menu.IMenu;
import com.service.message.handler.IMenuMessageHandler;

public class ButtonMenu implements IMenu {

	IMenuMessageHandler handler = null;
	private ArrayList<IMenu> list = new ArrayList<IMenu>();

	public ButtonMenu(IMenuMessageHandler handler) {
		this.handler = handler;
	}

	@Override
	public String getName() {
		return handler.getName();
	}

	@Override
	public String getEventKey() {
		return handler.getEventKey();
	}

	@Override
	public String getType() {
		return IMenu.TYPE_CLICK;
	}

	@Override
	public void addChild(IMenu menu) {
		list.add(menu);
	}

	@Override
	public List<IMenu> getChildren() {
		return list;
	}

}
