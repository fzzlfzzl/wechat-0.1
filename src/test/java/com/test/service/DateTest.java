package com.test.service;

import org.hibernate.Session;

import com.web.dao.db.HibernateUtil;
import com.web.dao.entity.Message;
import com.web.dao.entity.User;
import com.web.dao.impl.UserDao;

public class DateTest {

	public static void main(String[] args) {
		Session session = HibernateUtil.openSession();
		UserDao dao = new UserDao(session);
		User user = dao.list().get(0);
		Message message = dao.getMessage(user, 0);
		System.out.println(message.getCreateTimeString());
	}
}
