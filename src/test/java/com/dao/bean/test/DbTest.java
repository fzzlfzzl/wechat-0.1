package com.dao.bean.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import com.dao.bean.User;
import com.dao.db.HibernateUtil;
import com.util.DbManager;

public class DbTest {

	@Before
	public void setup() {

	}

	@Test
	public void test() {
		try {
			DbManager db = new DbManager("127.0.0.1", 3306, "wechat", "root",
					"root");
			db.execute("drop table address");
			db.execute("drop table user");
			
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

	@Test
	public void testResource() {
		try {
			ClassLoader.getSystemResourceAsStream("spring-mvc.xml").available();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
}
