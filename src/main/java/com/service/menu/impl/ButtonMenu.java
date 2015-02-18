package com.service.menu.impl;

import java.util.ArrayList;
import java.util.List;

import com.service.menu.IMenu;
import com.service.menu.IMenuHandler;
import com.util.XmlObject;

public class ButtonMenu implements IMenu {

	private String id;
	private String name;
	private IMenuHandler handler;
	private ArrayList<IMenu> list = new ArrayList<IMenu>();

	public ButtonMenu(String id, String name, IMenuHandler handler) {
		this.id = id;
		this.name = name;
		this.handler = handler;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getKey() {
		return id;
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

	@Override
	public void handle(XmlObject req, XmlObject res) {
		handler.handle(req, res);
	}

}
