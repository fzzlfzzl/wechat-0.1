package com.test;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.dao.db.HibernateUtil;
import com.dao.entity.Message;
import com.dao.entity.User;
import com.dao.impl.UserDao;
import com.test.common.Common;

public class DbTest {

	@Test
	public void sanityTest() {
		try {
			Common.getDbManager().dropTables();
			Session session = HibernateUtil.openSession();
			session.beginTransaction();
			User user = new User();
			user.setOpenId("openid");
			session.save(user);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void messageTest() {
		try {
			int userid = 0;
			int messageid = 0;
			String openid = "openid";
			String content = "content";
			Common.getDbManager().dropTables();
			{
				// 写入user
				Session session = HibernateUtil.openSession();
				session.beginTransaction();
				User user = new User();
				user.setOpenId(openid);
				session.save(user);
				session.getTransaction().commit();
				session.close();
				userid = user.getId();
			}
			{
				// 获取user
				Session session = HibernateUtil.openSession();
				User user = (User) session.load(User.class, userid);
				assertEquals(user.getId(), userid);
				session.close();
			}
			{
				// 写入message
				Session session = HibernateUtil.openSession();
				session.beginTransaction();

				User user = (User) session.load(User.class, userid);
				Message message = new Message();
				message.setContent(content);
				message.setOpenId(user.getOpenId());
				user.setLastMessage(message);
				session.save(user);

				session.getTransaction().commit();
				session.close();
				messageid = message.getId();

			}
			{
				// 获取message
				Session session = HibernateUtil.openSession();
				Message message = (Message) session.load(Message.class, messageid);
				assertNotNull(message);
				assertEquals(content, message.getContent());
				session.close();
			}
			{
				// 通过user获取message
				Session session = HibernateUtil.openSession();
				User user = (User) session.load(User.class, userid);
				Message message = user.getLastMessage();
				assertEquals(messageid, message.getId());
				assertEquals(content, message.getContent());
				session.close();
			}
			{
				// 删除message，通过user更新
				Session session = HibernateUtil.openSession();
				session.beginTransaction();
				User user = (User) session.load(User.class, userid);
				session.delete(user.getLastMessage());
				user.setLastMessage(null);
				session.getTransaction().commit();
				session.close();
				assertNull(user.getLastMessage());
			}
			{
				// 通过原有的messageid拿不到
				Session session = HibernateUtil.openSession();
				Message message = (Message) session.get(Message.class, messageid);
				assertNull(message);
				session.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void userDaoTest() {
		try {
			Common.getDbManager().dropTables();
			User user1 = new User();
			user1.setOpenId("1");
			UserDao.save(user1);
			User user2 = new User();
			user2.setOpenId("2");
			UserDao.save(user2);
			List<User> list = UserDao.list();
			assertEquals(list.size(), 2);
			assertEquals(list.get(0).getId(), user1.getId());
			assertEquals(list.get(0).getOpenId(), user1.getOpenId());
			assertEquals(list.get(1).getId(), user2.getId());
			assertEquals(list.get(1).getOpenId(), user2.getOpenId());

			User user = UserDao.load("2");
			assertEquals(user.getId(), user2.getId());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
