package com.service.menu.impl;

import java.util.ArrayList;
import java.util.List;

import com.service.menu.IMenu;
import com.service.message.IMessage;
import com.service.message.impl.ClickEventMessage;

public class ButtonMenu implements IMenu {

	private ClickEventMessage message;
	private ArrayList<IMenu> list = new ArrayList<IMenu>();

	public ButtonMenu(IMessage message) {
		if (!(message instanceof ClickEventMessage)) {
			throw new RuntimeException("Not ClickEventMessage");
		}
		this.message = (ClickEventMessage) message;
	}

	@Override
	public String getName() {
		return message.getName();
	}

	@Override
	public String getEventKey() {
		return message.getEventKey();
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
