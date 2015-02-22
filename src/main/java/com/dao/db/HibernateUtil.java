package com.dao.db;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.w3c.dom.Document;

import com.util.Util;
import com.util.XmlObject;

public class HibernateUtil {

	private static Logger logger = Logger.getLogger(HibernateUtil.class);

	private static String defaultPath = "hibernate.cfg.xml";
	private static SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			if (Util.isDevelopEnvironment()) {
				logger.info("Development Environment");
				configuration.configure(getDevelopConfig());
			} else {
				logger.info("Online Environment");
				configuration.configure(defaultPath);
			}
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
					configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		} catch (Exception e) {
			logger.error("Build Session Factory Fail");
			throw new RuntimeException(e);
		}
	}

	private static Document getDevelopConfig() throws Exception {
		String url = "jdbc:mysql://127.0.0.1:3306/wechat";
		String user = "root";
		String pwd = "root";
		InputStream is = ClassLoader.getSystemResourceAsStream(defaultPath);
		XmlObject obj = XmlObject.toXmlObject(is);
		XmlObject sessionFactory = obj.get("session-factory");
		int length = sessionFactory.getLength("property");
		for (int i = 0; i < length; i++) {
			XmlObject property = sessionFactory.get("property", i);
			String name = property.getAttribute("name");
			if (name.equals("connection.url")) {
				property.setText(url);
			} else if (name.equals("connection.username")) {
				property.setText(user);
			} else if (name.equals("connection.password")) {
				property.setText(pwd);
			}
		}
		logger.info(obj.toXmlString());
		return XmlObject.Convert.toDocument(obj);
	}

	public static Session openSession() {
		Session session = sessionFactory.openSession();
		return session;
	}

}
