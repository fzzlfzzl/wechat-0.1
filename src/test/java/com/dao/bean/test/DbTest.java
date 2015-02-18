package com.dao.bean.test;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import com.dao.bean.User;
import com.dao.db.HibernateUtil;
import com.util.DbManager;

public class DbTest {

	@Before
	public void setup() {
		DbManager db = new DbManager("127.0.0.1", 3306, "wechat", "root",
				"root");
		db.execute("drop table address");
		db.execute("drop table user");
	}

	@Test
	public void test() {
		try {
			Session session = HibernateUtil.openSession();
			assertNotNull(session);

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
}
