package com.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.dao.db.HibernateUtil;
import com.dao.entity.Admin;

public class AdminDao {

	@SuppressWarnings("unchecked")
	public static List<Admin> list() {
		Session session = HibernateUtil.openSession();
		List<Admin> ret = session.createQuery("from Admin").list();
		session.close();
		return ret;
	}

	public static void save(Admin admin) {
		Session session = HibernateUtil.openSession();
		try {
			session.beginTransaction();
			session.save(admin);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}

	public static Admin load(String name) {
		Session session = HibernateUtil.openSession();
		Admin admin = (Admin) session.createQuery("from Admin where name=:name")
				.setString("name", name).list().get(0);
		session.close();
		return admin;
	}
}
