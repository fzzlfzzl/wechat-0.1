package com.test.dao;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Test;

import com.test.common.Common;
import com.web.dao.db.HibernateUtil;
import com.web.dao.entity.Message;
import com.web.dao.entity.User;
import com.web.dao.impl.UserDao;
import com.wechat.session.SessionPool;

public class UserDaoTest {

	@Test
	public void userDaoTest() {
		Common.getDbManager().rebase();
		SessionPool.openSession();
		UserDao dao = new UserDao(SessionPool.current());
		try {
			{
				// create and read
				User user = new User();
				user.setOpenId("1");
				dao.save(user);

				User user2 = dao.load("1");
				assertEquals(user, user2);
			}
			{
				// get nonexist
				User user = dao.get("2");
				assertNull(user);
			}
			{
				// load nonexist
				try {
					dao.load("2");
					fail();
				} catch (Exception e) {
				}
			}
			{
				// update nonexist
				User user = new User();
				user.setOpenId("2");
				try {
					dao.update(user);
					fail();
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			SessionPool.closeSession();
		}
	}

	@Test
	public void messageTest() {
		try {
			int messageid = 0;
			// int messageid2 = 0;
			String openId = "" + System.currentTimeMillis();
			String content = "content";
			Common.getDbManager().rebase();
			SessionPool.openSession();
			UserDao dao = new UserDao(SessionPool.current());
			{
				// 写入user
				User user = new User();
				user.setOpenId(openId);
				dao.save(user);
			}
			{
				// 写入message
				User user = dao.load(openId);
				Message message = new Message();
				message.setContent(content);
				message.setOpenId(user.getOpenId());
				dao.addMessage(user, message);
				messageid = message.getId();
			}
			{
				// 获取message
				User user = dao.load(openId);
				Message message = dao.getMessage(user, 0);
				assertNotNull(message);
				assertEquals(content, message.getContent());
				assertEquals(message.getId(), messageid);
			}
			{
				// 加入第二个message
				Message message = new Message();
				User user = dao.load(openId);
				dao.addMessage(user, message);
			}
			{
				// 查询出两个message
				User user = dao.load(openId);
				assertEquals(dao.getMessageSize(user), 2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			SessionPool.closeSession();
		}
	}
}
