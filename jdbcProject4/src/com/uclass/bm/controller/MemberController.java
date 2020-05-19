package com.uclass.bm.controller;

import java.util.Map;

import com.uclass.bm.model.dao.MemberDao;
import com.uclass.bm.model.service.MemberService;
import com.uclass.bm.model.vo.Member;
import com.uclass.bm.view.ErroMsg;

public class MemberController {

	private MemberDao mDao = new MemberDao();
	private MemberService mService = new MemberService();
	private ErroMsg em = new ErroMsg();

	public MemberController() {

	}

	// 로그인 처리용
	public Map<String, Object> selectLogin(String m_id, String m_password) {

		Map<String, Object> resultMap = mService.selectLogin(m_id, m_password);

		if ((boolean) resultMap.get("isSuccess")) {
			return resultMap;
		} else {
			em.printErr(resultMap);
		}

		return resultMap;
	}
	
	//회원가입
	public Map<String, Object> insertMember(Member member){
		
		Map<String, Object> resultMap = mService.insertMember(member);
		
		if ((boolean) resultMap.get("isSuccess")) {
			return resultMap;
		} else {
			em.printErr(resultMap);
		}
		
		return resultMap;
		
	}

}
