package com.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.test.util.DbManager;
import com.web.dao.entity.Admin;
import com.web.dao.impl.AdminDao;

public class AdminDaoTest {

	@Test
	public void adminDaoTest() {
		try {
			DbManager.rebase();
			List<Admin> orig = AdminDao.list();
			Admin admin = new Admin();
			admin.setName("test1");
			admin.setPassword("1");
			AdminDao.save(admin);
			List<Admin> cur = AdminDao.list();
			assertEquals(1, cur.size() - orig.size());
			Admin admin2 = AdminDao.load("test1");
			assertEquals(admin.getId(), admin2.getId());
			AdminDao.delete(admin);
			List<Admin> after = AdminDao.list();
			assertEquals(after.size(), orig.size());
			admin2 = AdminDao.load("test1");
			assertNull(admin2);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
