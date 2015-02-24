package com.test;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Test;

import com.dao.bean.Message;
import com.dao.bean.User;
import com.dao.db.HibernateUtil;
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

			// {
			// Session session = HibernateUtil.openSession();
			// session.beginTransaction();
			// Message message = (Message) session.load(Message.class,
			// messageid);
			// session.delete(message);
			// session.getTransaction().commit();
			// session.close();
			// }

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
