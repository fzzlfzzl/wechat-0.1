package com.service.menu;

import java.util.ArrayList;
import java.util.List;

import com.service.WechatService;
import com.service.menu.impl.ButtonMenu;
import com.service.message.impl.AddressClickEventMessage;
import com.test.tools.JsonObject;
import com.util.HttpClient;

public class MenuManager {

	private static List<IMenu> menuList = null;

	public static void initMenu() {
		IMenu menu = null;
		menu = new ButtonMenu(new AddressClickEventMessage(null));
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
			HttpClient client = new HttpClient(WechatService.getRegistMenuUrl());
			String res = client.post(obj.toJsonString());
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
