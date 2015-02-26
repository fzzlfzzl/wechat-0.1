package com.test.common;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class DbManager {

	private static Logger logger = Logger.getLogger(DbManager.class);

	private static String driver = "com.mysql.jdbc.Driver";
	private Connection conn = null;
	
	

	public DbManager(String ip, int port, String table, String user, String password) {
		String url = String.format("jdbc:mysql://%s:%d/%s", ip, port, table);
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void dropTables() {
		boolean finish = false;
		while (!finish) {
			finish = true;
			List<String> tables = getTables();
			for (String table : tables) {
				String sql = String.format("drop table %s", table);
				if (!execute(sql)) {
					finish = false;
				}
			}
		}
	}

	public List<String> getTables() {
		ArrayList<String> list = new ArrayList<String>();
		try {
			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = md.getTables(null, "%", "%", null);
			while (rs.next()) {
				list.add(rs.getString("TABLE_NAME"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public boolean execute(String sql) {
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			stmt.close();
			return true;
		} catch (SQLException e) {
			if (needCatchException(e)) {
				logger.warn(String.format("Execute Sql Fail: %s (%s)", sql, e.getLocalizedMessage()));
				return false;
			}
			throw new RuntimeException(e);
		}
	}

	private boolean needCatchException(SQLException e) {
		return e.getSQLState().equals("23000") || e.getSQLState().equals("42000");
	}

	public ResultSet executeQuery(String sql) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet ret = stmt.executeQuery(sql);
			stmt.close();
			return ret;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
