package com.jdbc.test01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Mtest04 {

	public static void main(String[] args) {

		Connection con = null;
		Statement stmt = null;

		Scanner sc = new Scanner(System.in);

		try {
			// 1. 드라이버 연결
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 계정 연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "bm";
			String password = "test1";
			con = DriverManager.getConnection(url, user, password);

			// 3. 쿼리 준비
			stmt = con.createStatement();
			
			String sql = "delete from tb_member where m_id = 'aa'";

			// 4. 실행 및 리턴
			int res = stmt.executeUpdate(sql);
			if (res > 0) {
				System.out.println("삭제 성공");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		// 4. 실행 및 리턴

		// 5. DB 종료

	}

}
