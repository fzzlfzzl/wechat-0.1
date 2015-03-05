package com.test;

import static org.junit.Assert.fail;

import org.hibernate.Session;
import org.junit.Test;

import com.test.util.DbManager;
import com.web.dao.db.HibernateUtil;
import com.web.dao.entity.User;

public class DbTest {

	@Test
	public void rebase() {
		DbManager.rebase();
	}

	@Test
	public void sanityTest() {
		try {
			Session session = HibernateUtil.openSession();
			session.beginTransaction();
			User user = new User();
			user.setOpenId("" + System.currentTimeMillis());
			session.save(user);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
