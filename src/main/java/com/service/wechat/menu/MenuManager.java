package com.service.wechat.menu;

import com.service.wechat.WechatHelper;
import com.service.wechat.menu.impl.ClickButton;
import com.service.wechat.menu.impl.MainButton;
import com.service.wechat.menu.impl.SubButton;
import com.service.wechat.message.handler.impl.OrderMessageHandler;
import com.site.util.HttpClient;

public class MenuManager {

	private static MainButton main = null;

	public static void initMenu() {
		SubButton sub = new SubButton("二级菜单");
		main.addChild(sub);

		ClickButton click = new ClickButton(OrderMessageHandler.NAME, OrderMessageHandler.EVENT_KEY);
		sub.addChild(click);
	}

	public synchronized static String getRequest() {
		if (null == main) {
			main = new MainButton();
			initMenu();
		}
		return main.toJsonObject().toJsonString();
	}

	public static void registMenu() {
		HttpClient client = new HttpClient(WechatHelper.getRegistMenuUrl());
		String res = client.post(getRequest());
		System.out.println(res);
	}
}
