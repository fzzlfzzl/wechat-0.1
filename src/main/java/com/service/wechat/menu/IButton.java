package com.service.wechat.menu;

import com.site.util.JsonObject;

public interface IButton {

	public static String TYPE_CLICK = "click";
	public static String TYPE_VIEW = "view";

	public JsonObject toJsonObject();
}
