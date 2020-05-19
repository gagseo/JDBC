package com.uclass.bm.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.uclass.bm.exception.BMException;
import com.uclass.common.JDBCTemplate;

public class RentBookDao {
	
	private JDBCTemplate jdt = JDBCTemplate.getInstance();

	public int updateExtendRentBook(Connection con, int rb_idx) throws BMException {
		int result = 0;
		PreparedStatement pstm = null;

		String sql = "update tb_rent_book set rb_state = 1202, rb_return_date = rb_return_date + 7, rb_extention_cnt = rb_extention_cnt + 1 where rb_idx = "
				+ rb_idx;

		try {
			pstm = con.prepareStatement(sql);
			result = pstm.executeUpdate();

		} catch (SQLException e) {
			throw new BMException(e.getMessage());
		} finally {
			jdt.close(pstm);
		}

		return result;

	}

	public int insertUpdateHistory(Connection con, int rb_idx) throws BMException {
		int result = 0;
		PreparedStatement pstm = null;
		String sql = "insert into tb_rent_history select S_RH_IDX.NEXTVAL, a.* from(select rm_idx, rb_idx, b_bno, rb_state, sysdate from tb_rent_book where rb_idx = ?) a";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, rb_idx);
			result = pstm.executeUpdate();

		} catch (SQLException e) {
			throw new BMException(e.getMessage());
		} finally {
			jdt.close(pstm);
		}

		return result;
	}

	public void updateReturnRentBook(Connection con, int rm_idx, int rb_idx, int rb_state) throws BMException {

		CallableStatement cstm = null;
		String sql = "{call P_UPDATE_STATE(?,?)}";

		try {

			cstm = con.prepareCall(sql);
			cstm.setInt(1, rb_idx);
			cstm.setInt(2, rb_state);

		} catch (SQLException e) {
			throw new BMException(e.getMessage());
		} finally {
			jdt.close(cstm);
		}

	}
}
