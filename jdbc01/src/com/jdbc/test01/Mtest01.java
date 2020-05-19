package com.jdbc.test01;

import java.sql.Connection;
import java.util.Date;

import javax.sound.midi.Soundbank;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mtest01 {

	public static void main(String[] args) {

		// jdbc 5단계

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			// 1. driver 연결
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 계정연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "bm";
			String password = "test1";
			con = DriverManager.getConnection(url, user, password);

			// 3. query 준비
			stmt = con.createStatement();
			String sql = "select * from tb_member";

			// 4. 실행 및 리턴
			rs = stmt.executeQuery(sql);
			while (rs.next()) {

				String id = rs.getString(1);
				String pw = rs.getString(2);
				int grade = rs.getInt(3);
				String tel = rs.getString(4);
				Date rd = rs.getDate(5);

				System.out.println(id);
				System.out.println(pw);
				System.out.println(grade);
				System.out.println(tel);
				System.out.println(rd);

			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			try {
				// 5. DB종료
				rs.close();
				stmt.close();
				con.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

}
