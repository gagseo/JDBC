package member.controller;

import java.sql.Date;
import java.util.Map;

import member.model.dao.MemberDao;
import member.model.service.MemberService;
import member.model.vo.Member;
import member.view.ErrorMsg;

// MVC패턴에서 C는 Controller를 의미한다.
// Controller는 사용자로부터 전달받은 요청을 Model(service, dao)에 전달하고
// Model이 처리한 결과를 View(View를 선택해서)에 전달해주는 역할을 한다.
// Controller는 Model에서 전달받은 데이터를 View에게 전달해줄 때
// 데이터를 가공 처리 및 확인처리 하거나
// View에게 전달받은 데이터를 Model에 넘길 때
// 데이터를 가공 처리 및 확인처리 한다.

public class MemberController {

	private MemberDao mdao = new MemberDao();
	private MemberService mService = new MemberService();
	private ErrorMsg em = new ErrorMsg();

	public MemberController() {

	}

	// 로그인 조회 요청 처리용
	public Map<String, Object> selectLogin(String userId, String userPwd) {

		Map<String, Object> resultMap = mService.selectLogin(userId, userPwd);
		if ((boolean) resultMap.get("isSuccess")) {
			return resultMap;
		} else {
			em.printErr(resultMap);
			resultMap.put("res", "");
		}
		return resultMap;
	}

	// 아이디로 회원 조회 처리
	public Map<String, Object> selectMember(String userId) {

		Map<String, Object> resultMap = mService.selectMember(userId);
		if ((boolean) resultMap.get("isSuccess")) {
			return resultMap;
		} else {
			em.printErr(resultMap);
			resultMap.put("res", "");
		}

		return resultMap;
	}

	// 회원 전체 조회 처리용

	public Map<String, Object> selectList() {

		Map<String, Object> res = mService.selectList();

		if ((boolean) res.get("isSuccess")) {
			return res;
		} else {
			em.printErr(res);
			res.put("res", "");
		}
		return res;

	}

	// 회원 가입 처리용
	public Map<String, Object> insertMember(Member member) {

		Map<String, Object> res = mService.insertMember(member);
		if ((boolean) res.get("isSuccess")) {
			return res;
		} else {
			em.printErr(res);
			res.put("res", "");
		}
		return res;
	}

	// 회원 정보 수정 처리 :수정 항목별로처리하기에는 경우의수가 너무 많다.
	// 하나의 객체로 처리
	public Map<String, Object> updateMember(Member member) {

		Map<String, Object> res = mService.updateMember(member);
		if ((boolean) res.get("isSuccess")) {
			return res;
		} else {
			em.printErr(res);
			res.put("res", "");
		}

		return res;
	}

	// 회원 검색 : 이름 키워드로검색(성이 김씨인 , 이름에 제 자가포함된...)
	public Map<String, Object> selectSearchName(String nameKeyword) {

		Map<String, Object> resultMap = mService.selectSearchName(nameKeyword);

		return resultMap;

	}

	// 회원 검색 : 나이별 검색
	public Map<String, Object> selectSearchAge(int beginAge, int endAge) {

		Map<String, Object> resultMap = mService.selectSearchAge(beginAge, endAge);

		return resultMap;

	}

	// 회원 검색 : 성별별 검색
	public Map<String, Object> selectSearchGender(String gender) {

		Map<String, Object> resultMap = mService.selectSerachGender(gender);

		return resultMap;
	}

	// 회원 검색 : 가입날짜별검색

	public Map<String, Object> selectSearchEnrollDate(Date begin, Date end) {

		Map<String, Object> resultMap = mService.selectSerachEnrollDate(begin, end);

		return resultMap;

	}

	// 회원 정보 삭제 처리용
	public Map<String, Object> deleteMember(String userId) {
		Map<String, Object> resultMap = mService.delectMember(userId);

		return resultMap;

	}

}
