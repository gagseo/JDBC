package com.uclass.bm.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.uclass.bm.exception.BMException;
import com.uclass.bm.model.dao.RentBookDao;
import com.uclass.common.JDBCTemplate;

public class RentBookService {

	private RentBookDao rbd = new RentBookDao();

	public Map<String, Object> updateExtendRentBook(int rb_idx) {

		Connection con = JDBCTemplate.getConnection();
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {

			int result = rbd.updateExtendRentBook(con, rb_idx);
			rbd.insertUpdateHistory(con, rb_idx);

			resultMap.put("isSuccess", true);
			resultMap.put("res", "도서 연장신청이 완료되었습니다");
			JDBCTemplate.commit(con);

		} catch (BMException e) {

			JDBCTemplate.rollback(con);
			resultMap.put("isSuccess", false);
			resultMap.put("res", "시스템 관리자에게 문의 해주세요. \n" + e.getMessage());
		} finally {

			JDBCTemplate.close(con);
		}

		return resultMap;

	}

	public Map<String, Object> updateReturnRentBook(int rm_idx, int rb_idx, int rb_state) {

		Map<String, Object> resultMap = new HashMap<>();
		Connection con = JDBCTemplate.getConnection();

		try {
			rbd.updateReturnRentBook(con, rm_idx, rb_idx, rb_state);
			resultMap.put("isSuccess", true);
			resultMap.put("res", "반납이 완료되었습니다.");
			JDBCTemplate.commit(con);

		} catch (BMException e) {
			JDBCTemplate.rollback(con);
			resultMap.put("isSuccess", false);
			resultMap.put("res", "시스템관리자에게 문의하세요. \n" + e.getMessage());
		} finally {
			JDBCTemplate.close(con);
		}
		return resultMap;

	}

}
