package com.service.menu;

import java.util.List;

import com.util.XmlObject;

public interface IMenu {

	public static final String TYPE_CLICK = "click";

	public static final String ID_ADDRESS = "ADDRESS";
	public static final String ID_ORDER = "ORDER";
	public static final String ID_INFO = "INFO";

	public String getName();

	public String getKey();

	public String getType();

	public void addChild(IMenu menu);

	public List<IMenu> getChildren();

	void handle(XmlObject req, XmlObject res);
}
