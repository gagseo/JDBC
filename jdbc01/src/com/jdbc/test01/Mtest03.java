package com.jdbc.test01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Mtest03 {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pstm = null;
		Scanner sc = new Scanner(System.in);

		// jdbc 5단계
		
		try {
			// 1. driver 연결
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 계정 연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "bm";
			String password = "test1";
			con = DriverManager.getConnection(url, user, password);

			// 3. 쿼리 준비
			
			System.out.println("변경할 비밀번호 : ");
			String pw = sc.nextLine();
			System.out.println("비밀번호를 변경할 아이디 : ");
			String id = sc.nextLine();
			String sql = "update tb_member set m_password = ?" + " where m_id = ?";

			pstm = con.prepareStatement(sql);
			pstm.setString(1, pw);
			pstm.setString(2, id);
			
			// 4. 실행 및 리턴
			int res = pstm.executeUpdate();
			if (res > 0) {
				System.out.println("변경성공");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {// 5. DB 종료
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
