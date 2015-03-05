package com.web.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.web.dao.entity.AccessToken;

public class AccessTokenDao extends Dao {

	public AccessTokenDao(Session session) {
		super(session);
	}

	public AccessToken get() {
		List<?> list = session.createQuery("from AccessToken").setMaxResults(1).list();
		if (list.size() == 0) {
			return null;
		}
		return (AccessToken) list.get(0);
	}

	public void saveOrUpdate(AccessToken token) {
		try {
			beginTransaction();
			token.setUpdatetime(System.currentTimeMillis());
			session.saveOrUpdate(token);
			commit();
		} catch (Exception e) {
			rollback();
			throw new RuntimeException(e);
		}
	}
}
