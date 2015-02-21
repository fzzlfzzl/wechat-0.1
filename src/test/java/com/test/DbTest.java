package com.test;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Test;

import com.dao.bean.User;
import com.dao.db.HibernateUtil;
import com.test.common.Common;

public class DbTest {

	@Test
	public void sanityTest() {
		try {
			Common.dropTables();
		} catch (Exception e) {
			Common.getLogger().info("Drop Table Fail, Maybe No Table");
		}
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

	// @Test
	// public void baeMysqlTest() {
	// try {
	// String ip = "sqld.duapp.com";
	// int port = 4050;
	// String table = "IeSSkUaEwdLIOuyKGUtp";
	// String user = "Scu0HwGwVF0ov4ES7SGuH6Cj";
	// String pwd = "6OqgyPLkVIXrdmh8Rl8MpZ3YFKAfZ33U";
	// DbManager manager = new DbManager(ip, port, table, user, pwd);
	// ResultSet result = manager.executeQuery("show tables");
	// while (result.next()) {
	// Common.getLogger().info(result.getString(1));
	// }
	// } catch (Exception e) {
	// fail();
	// }
	// }
}
