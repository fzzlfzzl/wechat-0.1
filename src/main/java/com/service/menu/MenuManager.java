package com.service.menu;

import java.util.ArrayList;
import java.util.List;

import com.service.WechatHelper;
import com.service.menu.impl.ButtonMenu;
import com.service.message.handler.impl.AddressClickEventMessageHandler;
import com.util.HttpClient;
import com.util.JsonObject;

public class MenuManager {

	private static List<IMenu> menuList = null;

	public static void initMenu() {
		IMenu menu = null;
		menu = new ButtonMenu(new AddressClickEventMessageHandler());
		menuList.add(menu);
	}

	public synchronized static List<IMenu> getMenuList() {
		if (null == menuList) {
			menuList = new ArrayList<IMenu>();
			initMenu();
		}
		return menuList;
	}

	public static void registMenu() {
		List<IMenu> list = getMenuList();
		JsonObject obj = new JsonObject();
		try {
			for (int i = 0; i < list.size(); i++) {
				obj.get("button").get(i).get("type").set(list.get(i).getType());
				obj.get("button").get(i).get("name").set(list.get(i).getName());
				obj.get("button").get(i).get("key").set(list.get(i).getEventKey());
			}
			HttpClient client = new HttpClient(WechatHelper.getRegistMenuUrl());
			String res = client.post(obj.toJsonString());
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
