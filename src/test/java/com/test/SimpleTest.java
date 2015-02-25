package com.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.InputStream;

import org.hibernate.Session;
import org.junit.Test;

import com.dao.db.HibernateUtil;

public class SimpleTest {

	@Test
	public void testResource() {
		try {
			InputStream is = ClassLoader.getSystemResourceAsStream("hibernate.cfg.xml");
			assertNotNull(is);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testBuildSessionFactory() {
		try {
			Session session = HibernateUtil.openSession();
			assertNotNull(session);
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
