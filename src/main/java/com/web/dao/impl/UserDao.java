package com.web.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.web.dao.entity.Message;
import com.web.dao.entity.User;

public class UserDao extends Dao {

	public UserDao(Session session) {
		super(session);
	}

	public User get(String openId) {
		try {
			User user = (User) session.get(User.class, openId);
			return user;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public void save(User user) {
		beginTransaction();
		try {
			session.save(user);
			commit();
		} catch (RuntimeException e) {
			rollback();
			throw e;
		}
	}

	public void update(User user) {
		beginTransaction();
		try {
			session.update(user);
			commit();
		} catch (RuntimeException e) {
			rollback();
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> list() {
		try {
			String query = String.format("from User");
			List<User> list = (List<User>) session.createQuery(query).list();
			return list;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public void addMessage(User user, Message message) {
		beginTransaction();
		try {
			session.save(message);
			user.getMessages().add(message);
			commit();
		} catch (RuntimeException e) {
			rollback();
			throw e;
		}
	}

	public Message getMessage(User user, int idx) {
		try {
			String hql = "from Message where fromUserName = :openid";
			Query query = session.createQuery(hql);
			query.setFirstResult(idx);
			query.setMaxResults(1);
			query.setString("openid", user.getOpenId());
			return (Message) query.list().get(0);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public int getMessageSize(User user) {
		try {
			long ret = (Long) session
					.createQuery("select count(1) from Message where fromUserName=:openid")
					.setString("openid", user.getOpenId()).uniqueResult();
			return (int) ret;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Message> getMessages(User user) {
		try {
			List<?> list = session
					.createQuery("from Message where fromUserName=:openid order by createTime")
					.setString("openid", user.getOpenId()).list();
			return (List<Message>) list;
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
