package com.uclass.bm.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.uclass.bm.exception.BMException;
import com.uclass.bm.model.vo.Member;

public class MemberDao {

	public MemberDao() {

	}


	public boolean selectLogin(Connection con, String m_id, String m_password) throws BMException {

		String sql = "select * from tb_member where m_id = ? and m_password = ?";
		boolean result = false;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, m_id);
			pstm.setString(2, m_password);

			rset = pstm.executeQuery();
			if (rset.next()) {
				if (rset.getInt(1) == 1) {
					result = true;
				}else {
					throw new BMException("로그인 실패");
				}
			}

		} catch (SQLException e) {
			
			throw new BMException(e.getMessage());
		
		} finally {
			
			try {
				rset.close();
				pstm.close();
			} catch (SQLException e) {
				
				throw new BMException(e.getMessage());
			}
			
		}

		return result;

	}

	public int insertMember(Connection con, Member m) throws BMException {

		String sql = "insert into tb_member values(?,?,1001,?,sysdate)";

		PreparedStatement pstm = null;

		int res = 0;

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, m.getM_id());
			pstm.setString(2, m.getM_password());
			pstm.setString(3, m.getM_tell());

			res = pstm.executeUpdate();
			
			if (res == 0) {
				throw new BMException("추가된 행이 없습니다.");
			}
			
		} catch (SQLException e) {
			
			throw new BMException(e.getMessage());
			
		} finally {

			try {
				pstm.close();
				
			} catch (SQLException e) {
				
				throw new BMException(e.getMessage());
			}
		}
		
		return res;
	

	}
	
	

}
