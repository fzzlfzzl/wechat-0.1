package com.service.wechat.menu;

import java.util.List;

public interface IMenu {

	public static final String TYPE_CLICK = "click";
	public static final String TYPE_VIEW = "view";

	public static final String NAME_ADDRESS = "地址";
	public static final String NAME_ORDER = "订餐";

	public String getName();

	public String getEventKey();

	public String getType();

	public void addChild(IMenu menu);

	public List<IMenu> getChildren();
}