package com.wechat.session;

import java.util.concurrent.ConcurrentSkipListMap;

import org.hibernate.Session;

import com.web.dao.db.HibernateUtil;

public class SessionPool {

	private static ConcurrentSkipListMap<Long, Session> map = new ConcurrentSkipListMap<Long, Session>();

	public static Session openSession() {
		long tid = Thread.currentThread().getId();
		Session session = HibernateUtil.openSession();
		map.put(tid, session);
		return session;
	}

	public static Session current() {
		long tid = Thread.currentThread().getId();
		return map.get(tid);
	}

	public static void closeSession() {
		long tid = Thread.currentThread().getId();
		current().close();
		map.remove(tid);
	}
}
