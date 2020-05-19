package com.jdbc.test01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Mtest05 {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pstm = null;
		Scanner sc = new Scanner(System.in);

		try {
			// 1. driver 연결
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 계정연결
			String user = "bm";
			String password = "test1";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, user, password);

			// 3. 쿼리준비
			System.out.println("아이디 : ");
			String id = sc.nextLine();
			System.out.println("비밀번호 : ");
			String pw = sc.nextLine();
			System.out.println("등급코드 : ");
			int grade = sc.nextInt();
			sc.nextLine();
			System.out.println("휴대폰번호 : ");
			String tell = sc.nextLine();

			// 아이디로 사용자의 m_password, m_grade, m_tell 을 바꾸는 쿼리 작성
			String sql = "update tb_member set m_password = ?" + ", m_grade = ?" + ", m_tell = ?" + "where m_id = ?";

			// PreparedStatement : 미리 준비하는 statement
			// 미리 쿼리를 컨버팅 해 놓는다.
			pstm = con.prepareStatement(sql);
			pstm.setString(1, pw);
			pstm.setInt(2, grade);
			pstm.setString(3, tell);
			pstm.setString(4, id);

			// 4. 쿼리 실행 및 반환
			int res = pstm.executeUpdate();
			if (res > 0) {
				System.out.println("수정되었습니다.");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 5. DB 종료
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
