package com.service.wechat.menu;

import java.util.ArrayList;
import java.util.List;

import com.service.wechat.WechatHelper;
import com.service.wechat.menu.impl.ButtonMenu;
import com.service.wechat.menu.impl.MainButton;
import com.service.wechat.menu.impl.SubButton;
import com.service.wechat.message.handler.impl.AddressMessageHandler;
import com.site.util.HttpClient;
import com.site.util.JsonObject;

public class MenuManager {

	private static List<IMenu> menuList = null;

	public static void initMenu() {
		MainButton main = new MainButton();
		SubButton sub = new SubButton();
		
		IMenu menu = null;
		menu = new ButtonMenu(new AddressMessageHandler());
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
