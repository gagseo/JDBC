package com.uclass.bm.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.uclass.bm.exception.BMException;
import com.uclass.bm.model.dao.MemberDao;
import com.uclass.bm.model.vo.Member;
import com.uclass.common.JDBCTemplate;

public class MemberService {

	private MemberDao mDao = new MemberDao();

	// 로그인
	public Map<String, Object> selectLogin(String m_id, String m_password) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean result = false;
		Connection con = JDBCTemplate.getConnection();

		try {
			result = mDao.selectLogin(con, m_id, m_password);
			resultMap.put("isSuccess", true);
			resultMap.put("res", result);

		} catch (BMException e) {
			resultMap.put("isSuccess", false);
			resultMap.put("res", "시스템관리자에게 문의해주세요. \n" + e.getMessage());
		} finally {
			JDBCTemplate.close(con);
		}

		return resultMap;

	}

	// 회원가입
	public Map<String, Object> insertMember(Member member) {

		Map<String, Object> resultMap = new HashMap<>();
		Connection con = JDBCTemplate.getConnection();
		int result = 0;

		try {
			result = mDao.insertMember(con, member);
			resultMap.put("isSuccess", true);
			resultMap.put("res", result);
		} catch (BMException e) {
			resultMap.put("isSuccess", false);
			resultMap.put("res", "시스템관리자에게 문의해주세요. \n" + e.getMessage());
		} finally {
			JDBCTemplate.close(con);
		}

		return resultMap;

	}

}
