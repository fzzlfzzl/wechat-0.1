package com.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.hibernate.Session;
import org.junit.Test;

import com.test.util.DbManager;
import com.web.dao.db.HibernateUtil;
import com.web.dao.entity.Message;
import com.web.dao.entity.User;

public class SessionTest {

	@SuppressWarnings("unused")
	@Test
	public void sessionTest() {
		try {
			long messageId = 0;
			long messageId2 = 0;
			DbManager.rebase();
			{
				// 保存后修改，不保存直接commit
				Session session = HibernateUtil.openSession();
				session.beginTransaction();
				Message message = new Message();
				message.setOpenId("" + System.currentTimeMillis());
				session.save(message);
				message.setOpenId("2_" + System.currentTimeMillis());
				session.getTransaction().commit();
				session.close();
				messageId = message.getMsgId();
				// save后commit前就insert了，commit前有修改在commit时会update
			}
			{
				// 连续两次读取
				Session session = HibernateUtil.openSession();
				Message message = (Message) session.get(Message.class, messageId);
				Message message2 = (Message) session.get(Message.class, messageId);
				assertEquals(message, message2);
				String id1 = message.getOpenId();
				String id2 = message.getOpenId();
				assertEquals(id1, id2);
				session.close();
				// 结论：不使用字段就不会真正读取，在session存在时同一个对象只读取一次
			}
			{
				// 两个session读一个文件，时间上不重合
				Session session = HibernateUtil.openSession();
				Message message1 = (Message) session.get(Message.class, messageId);
				String id = message1.getOpenId();
				session.close();
				session = HibernateUtil.openSession();
				Message message2 = (Message) session.get(Message.class, messageId);
				String id2 = message2.getOpenId();
				session.close();
				assertEquals(message1.getMsgId(), message2.getMsgId());
				// 在session存在时没有使用过那么在session关闭后就不能使用了
			}
			{
				// 两个session读一个文件，时间上重合
				Session session1 = HibernateUtil.openSession();
				Message message1 = (Message) session1.get(Message.class, messageId);
				String id = message1.getOpenId();
				Session session2 = HibernateUtil.openSession();
				Message message2 = (Message) session2.get(Message.class, messageId);
				String id2 = message2.getOpenId();
				session1.close();
				session2.close();
				assertEquals(message1.getMsgId(), message2.getMsgId());
				// 在session存在时没有使用过那么在session关闭后就不能使用了
			}
			{
				// 读两次，一个修改，看另一个
				Session session = HibernateUtil.openSession();
				session.beginTransaction();
				Message message = (Message) session.get(Message.class, messageId);
				Message message2 = (Message) session.get(Message.class, messageId);
				message.setContent("content");
				assertEquals(message2.getContent(), message.getContent());
				session.getTransaction().commit();
				session.close();
				// 在同样的session里，id相同的代理是一个
			}
			{
				// 在两个session的两个事务中修改同一个对象
				Session session = HibernateUtil.openSession();
				session.beginTransaction();
				Session session2 = HibernateUtil.openSession();
				session2.beginTransaction();
				Message message = (Message) session.get(Message.class, messageId);
				Message message2 = (Message) session2.get(Message.class, messageId);
				message.setContent("A_" + message.getContent());
				message2.setContent("B_" + message2.getContent());
				session.saveOrUpdate(message);
				session2.saveOrUpdate(message2);
				session.getTransaction().commit();
				session.close();
				session2.getTransaction().commit();
				session2.close();
				// 后者的更新将前者覆盖了
			}
			// {
			// // 和上例一样，不同的是用String暂存起来
			// Session session = HibernateUtil.openSession();
			// session.beginTransaction();
			// Session session2 = HibernateUtil.openSession();
			// session2.beginTransaction();
			// session.createQuery("update Message m set m.content='A'+m.content where m.id=:id")
			// .setInteger("id", messageId).executeUpdate();
			// session.getTransaction().commit();
			// session.close();
			// session2.getTransaction().commit();
			// session2.close();
			// }
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void userDaoTest() {
		try {
			DbManager.rebase();
			{
				// 做数据
				Session session = HibernateUtil.openSession();
				session.beginTransaction();
				User user = new User();
				user.setOpenId("openid");
				for (int i = 0; i < 10; i++) {
					Message message = new Message();
					message.setContent("" + i);
					user.getMessages().add(message);
					session.save(message);
				}
				session.save(user);
				session.getTransaction().commit();
				session.close();
				// 在非cascade.all的情况下总是报错，试了多次也没能让按需更新
			}
			{
				// 在一个session读写
				Session session = HibernateUtil.openSession();
				session.getTransaction().begin();
				User user = (User) session.get(User.class, "openid");
				assertEquals(user.getMessages().get(0).getContent(), "0");

				Message message = new Message();
				message.setContent("asdf");
				user.getMessages().add(message);

				session.update(user);
				session.save(message);
				session.getTransaction().commit();
				session.close();
				// 即使在一个session里，还是傻傻的全更新
			}
			{
				// 在一个session读出来，在另一个session写进去
				Session session = HibernateUtil.openSession();
				User user = (User) session.get(User.class, "openid");
				assertEquals(user.getMessages().get(0).getContent(), "0");
				session.close();

				Message message = new Message();
				message.setContent("0000");
				user.getMessages().add(message);

				session = HibernateUtil.openSession();
				session.beginTransaction();
				session.saveOrUpdate(user);
				session.save(message);
				session.getTransaction().commit();
				session.close();
				// 会傻傻的再写一遍
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
