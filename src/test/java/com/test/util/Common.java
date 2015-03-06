package com.test.util;

import com.site.util.Util;
import com.web.dao.entity.Message;
import com.web.dao.entity.User;

public class Common {

	public static Message createMessage() {
		Message message = new Message();
		message.setContent("createMessage" + Util.random() % 100);
		message.setCreateTime(System.currentTimeMillis() / 1000);
		return message;
	}

	public static User createUser() {
		User user = new User();
		user.setOpenId(Util.uuid());
		return user;
	}

}
