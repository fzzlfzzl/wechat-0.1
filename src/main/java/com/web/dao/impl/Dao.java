package com.web.dao.impl;

import org.hibernate.Session;

public class Dao {

	protected Session session = null;
	private boolean isActive = false;

	public Dao(Session session) {
		this.session = session;
		this.isActive = session.getTransaction().isActive();
	}

	public void beginTransaction() {
		if (!isActive) {
			session.beginTransaction();
		}
	}

	public void commit() {
		if (!isActive) {
			session.getTransaction().commit();
		}
	}

	public void rollback() {
		if (!isActive) {
			session.getTransaction().rollback();
		}
	}

}
