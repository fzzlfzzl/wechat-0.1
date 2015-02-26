package com.service.state;

import java.util.concurrent.ConcurrentSkipListMap;

import com.dao.entity.User;
import com.dao.impl.UserDao;

public class UserStatePool {

	private static ConcurrentSkipListMap<String, IUserState> map = new ConcurrentSkipListMap<String, IUserState>();

	public static IUserState getUserState(String openId) {
		// 1. 先在缓存里找
		IUserState ret = map.get(openId);
		if (null != ret) {
			return ret;
		}
		// 2. 然后是数据库
		User user = UserDao.load(openId);
		if (null != user) {
			ret = new UserState(user);
			map.put(openId, ret);
			return ret;
		}
		// 3. 没有就新建一个
		user = new User();
		user.setOpenId(openId);
		ret = new UserState(user);
		map.put(openId, ret);
		return ret;
	}
}
