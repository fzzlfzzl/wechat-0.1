package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbManager {

	private static String driver = "com.mysql.jdbc.Driver";
	private Connection conn = null;

	public DbManager(String ip, int port, String table, String user,
			String password) {
		String url = String.format("jdbc:mysql://%s:%d/%s", ip, port, table);
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public boolean execute(String sql) {
		try {
			Statement stmt = conn.createStatement();
			return stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
