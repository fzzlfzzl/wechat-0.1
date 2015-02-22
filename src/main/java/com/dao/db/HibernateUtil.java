package com.dao.db;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.util.Util;

public class HibernateUtil {

	private static Logger logger = Logger.getLogger(HibernateUtil.class);

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			if (Util.isDevelopEnvironment()) {
				logger.info("Development Environment");
				return new Configuration().configure("hibernate.dev.cfg.xml").buildSessionFactory(
						new StandardServiceRegistryBuilder().build());
			} else {
				logger.info("Online Environment");
				return new Configuration().configure()
						.buildSessionFactory(new StandardServiceRegistryBuilder().build());
			}
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session openSession() {
		Session session = sessionFactory.openSession();
		return session;
	}

}
