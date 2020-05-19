package com.uclass.bm.model.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.uclass.bm.exception.BMException;
import com.uclass.bm.model.dao.MemberDao;
import com.uclass.bm.model.vo.Member;
import com.uclass.common.BmLogFactory;
import com.uclass.common.JDBCTemplate;

public class MemberService {

	private MemberDao mDao = new MemberDao();
	private JDBCTemplate jdt = JDBCTemplate.getInstance();
	private Logger log = BmLogFactory.getInstance().getLogger(MemberService.class);

	// 로그인
	public Map<String, Object> selectLogin(String m_id, String m_password) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Member m = new Member();
		Connection con = jdt.getConnection();

		try {
			m = mDao.selectLogin(con, m_id, m_password);
			resultMap.put("isSuccess", true);
			resultMap.put("res", m);

		} catch (BMException e) {
			resultMap.put("isSuccess", false);
			resultMap.put("res", "시스템관리자에게 문의해주세요. \n" + e.getMessage());
			log.debug(e.getMessage());
		} finally {
			jdt.close(con);
		}

		return resultMap;

	}

	// 회원가입
	public Map<String, Object> insertMember(Member member) {

		Map<String, Object> resultMap = new HashMap<>();
		Connection con = jdt.getConnection();
		int result = 0;

		try {
			result = mDao.insertMember(con, member);
			if (result > 0) {
				resultMap.put("isSuccess", true);
				jdt.commit(con);
			}

		} catch (BMException e) {
			jdt.rollback(con);
			resultMap.put("isSuccess", false);
			resultMap.put("res", "시스템관리자에게 문의해주세요. \n" + e.getMessage());
			log.debug(e.getStackTrace().toString());
		} finally {
			jdt.close(con);
		}

		return resultMap;

	}

}
