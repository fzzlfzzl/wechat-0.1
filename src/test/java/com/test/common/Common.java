package com.test.common;

import org.apache.log4j.Logger;


public class Common {

	private static Logger logger = Logger.getLogger("Test");

	private static String ip = "127.0.0.1";
	private static int port = 3306;
	private static String db = "wechat";
	private static String user = "root";
	private static String pwd = "root";

	private static DbManager dbManager = new DbManager(ip, port, db, user, pwd);

	public static DbManager getDbManager() {
		return dbManager;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void dropTables() {
		try {
			dbManager.execute("drop table address");
			dbManager.execute("drop table user");
		} catch (Exception e) {

		}
	}
}
