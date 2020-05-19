package com.uclass.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	private static JDBCTemplate jdt = null;

	private static class JDBCTemlpateHolder {

		private static final JDBCTemplate jdtInstance = new JDBCTemplate();

	}

	private JDBCTemplate() {

	}

	public static JDBCTemplate getInstance() {

		// if (jdt == null) {
		// jdt = new JDBCTemplate();
		// }
		//
		// return jdt;

		// 위에 if문과 다른점은 안정성이 높다.
		// 관리를 JVM이 알아서 한다.
		// 싱글톤 패턴
		return JDBCTemlpateHolder.jdtInstance;

	}

	public Connection getConnection() {

		Connection con = null;
		String url = "";
		String user = "";
		String password = "";
		String driver = "";
		FileInputStream fis = null;

		try {

			Properties props = new Properties();
			fis = new FileInputStream("db.properties");
			props.load(fis);
			driver = props.getProperty("driver").trim();
			url = props.getProperty("url").trim();
			user = props.getProperty("user").trim();
			password = props.getProperty("password").trim();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}

	public void close(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void commit(Connection con) {

		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void rollback(Connection con) {

		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close(ResultSet rs, Statement stmt, Connection con) {
		try {
			if (rs != null && stmt != null && con != null) {
				rs.close();
				stmt.close();
				con.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void close(ResultSet rs, Statement stmt) {
		try {
			if (rs != null && stmt != null) {
				rs.close();
				stmt.close();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
