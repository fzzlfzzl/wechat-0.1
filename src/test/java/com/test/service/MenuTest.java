package com.test.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.service.wechat.menu.impl.ClickButton;
import com.service.wechat.menu.impl.MainButton;
import com.service.wechat.menu.impl.SubButton;
import com.service.wechat.menu.impl.ViewButton;
import com.site.util.JsonObject;

public class MenuTest {

	@Test
	public void menuTest() {
		String expect = "{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"视频\",\"url\":\"http://v.qq.com/\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]}";
		MainButton main = new MainButton();
		ClickButton click = new ClickButton("今日歌曲", "V1001_TODAY_MUSIC");
		main.addChild(click);

		SubButton sub = new SubButton("菜单");
		ViewButton view = new ViewButton("搜索", "http://www.soso.com/");
		sub.addChild(view);
		view = new ViewButton("视频", "http://v.qq.com/");
		sub.addChild(view);
		click = new ClickButton("赞一下我们", "V1001_GOOD");
		sub.addChild(click);

		main.addChild(sub);
		System.out.println(main.toJsonObject().toJsonString());

		JsonObject expectObj = JsonObject.toJsonObject(expect);

		assertEquals(main.toJsonObject().toJsonString(), expectObj.toJsonString());

	}
}
