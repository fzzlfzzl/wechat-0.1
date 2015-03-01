package com.web.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.web.dao.db.HibernateUtil;
import com.web.dao.entity.User;

public class UserDao {

	public static User load(String openId) {
		Session session = HibernateUtil.openSession();
		try {
			String query = String.format("from User where openId = '%s'", openId);
			List<?> list = session.createQuery(query).list();
			if (list.size() == 0) {
				return null;
			} else {
				return (User) list.get(0);
			}
		} finally {
			session.close();
		}
	}

	public static void save(User user) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		try {
			session.saveOrUpdate(user);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<User> list() {
		Session session = HibernateUtil.openSession();
		try {
			String query = String.format("from User");
			List<User> list = (List<User>) session.createQuery(query).list();
			return list;
		} finally {
			session.close();
		}
	}

}
