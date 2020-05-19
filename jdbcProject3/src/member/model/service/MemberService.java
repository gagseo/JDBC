package member.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.HandlerBase;

import common.JdbcTemplate;
import member.exception.MemberException;
import member.model.dao.MemberDao;
import member.model.vo.Member;

//dao에서 DB연결, 연결 닫기, 트랜잭션 관리를 담당하는 코드를 분리
//컨트롤러가 요청한 내용을 처리하고 컨트롤러에게 돌려주는 역할
public class MemberService {

	private MemberDao mdao = new MemberDao();

	// 로그인 조회 요청 처리용
	public Map<String, Object> selectLogin(String userId, String userPwd) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		boolean result = false;
		Connection con = JdbcTemplate.getConnection();

		try {
			result = mdao.selectLogin(con, userId, userPwd);
			resultMap.put("isSuccess", true);
			resultMap.put("res", result);

		} catch (MemberException e) {

			resultMap.put("isSuccess", false);
			resultMap.put("res", "시스템관리자에게 문의해주세요. \n" + e.getMessage());

		} finally {
			JdbcTemplate.close(con);
		}
		return resultMap;
	}

	// 아이디로 회원 조회 처리
	public Map<String, Object> selectMember(String userId) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Member m = null;
		Connection con = JdbcTemplate.getConnection();

		try {
			m = mdao.selectMember(con, userId);
			resultMap.put("isSuccess", true);
			resultMap.put("res", m);

		} catch (MemberException e) {
			resultMap.put("isSuccess", false);
			resultMap.put("res", "시스템관리자에게 문의해주세요. \n" + e.getMessage());

		} finally {
			JdbcTemplate.close(con);
		}

		return resultMap;
	}

	// 회원 전체 조회 처리용
	public Map<String, Object> selectList() {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Connection con = JdbcTemplate.getConnection();
		String key = "";
		try {
			ArrayList<Member> list = mdao.selectList(con);
			resultMap.put("isSuccess", true);

			if (!list.isEmpty()) {
				resultMap.put("res", list);
			} else {
				resultMap.put("res", "회원이 없습니다.");
			}

		} catch (MemberException e) {
			resultMap.put("isSuccess", false);
			resultMap.put("res", "시스템관리자에게 문의해주세요. \n" + e.getMessage());
		} finally {
			JdbcTemplate.close(con);
		}

		return resultMap;

	}

	// 회원 검색 : 성별로 검색
	public Map<String, Object> selectSerachGender(String gender) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Connection con = JdbcTemplate.getConnection();
		

		try {
			List<Member> list = mdao.selectSerachGender(con, gender);
			resultMap.put("isSuccess", true);
			
			if (!list.isEmpty()) {
				
				resultMap.put("res", list);
			} else {
				resultMap.put("res", "검색된 회원이 없습니다.");
			}
		} catch (MemberException e) {
			resultMap.put("isSuccess", false);
			resultMap.put("res", "시스템관리자에게 문의해주세요. \n" + e.getMessage());
		} finally {
			JdbcTemplate.close(con);
		}

		return resultMap;

	}

	// 회원 검색 : 나이별 검색
	public Map<String, Object> selectSearchAge(int beginAge, int endAge) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Connection con = JdbcTemplate.getConnection();
		String key = "";

		List<Member> list;
		try {
			list = mdao.selectSearchAge(con, beginAge, endAge);
			resultMap.put("isSuccess", true);
			if (!list.isEmpty()) {
				resultMap.put("res", list);
			} else {
				resultMap.put("res", "회원 정보가 없습니다");
			}

		} catch (MemberException e) {
			resultMap.put("isSuccess", false);
			resultMap.put("res", "시스템관리자에게 문의해주세요. \n" + e.getMessage());
		} finally {
			JdbcTemplate.close(con);
		}

		return resultMap;

	}

	// 회원 검색 : 가입날짜별 검색
	public Map<String, Object> selectSerachEnrollDate(Date begin, Date end) {

		Map<String, Object> resultMap = new HashMap<>();
		Connection con = JdbcTemplate.getConnection();
		try {
			List<Member> list = mdao.selectSerachEnrollDate(con, begin, end);
			resultMap.put("isSuccess", true);
			if (!list.isEmpty()) {
				resultMap.put("res", list);
			} else {
				resultMap.put("res", "회원 정보가 없습니다");
			}
		} catch (MemberException e) {
			resultMap.put("isSuccess", false);
			resultMap.put("res", "시스템관리자에게 문의해주세요. \n" + e.getMessage());
		} finally {
			JdbcTemplate.close(con);
		}

		return resultMap;
	}

	// 이름 키워드로 회원 검색(이름에 '이' 가 들어간... 등등)
	public Map<String, Object> selectSearchName(String nameKeyword) {

		Map<String, Object> resultMap = new HashMap<>();
		Connection con = JdbcTemplate.getConnection();

		try {
			List<Member> list = mdao.selectSearchName(con, nameKeyword);
			resultMap.put("isSuccess", true);
			if (!list.isEmpty()) {
				resultMap.put("res", list);
			} else {
				resultMap.put("res", "회원 정보가 없습니다");
			}
		} catch (MemberException e) {
			resultMap.put("isSuccess", false);
			resultMap.put("res", "시스템관리자에게 문의해주세요. \n" + e.getMessage());
		} finally {
			JdbcTemplate.close(con);
		}

		return resultMap;

	}

	// 암호변경
	public Map<String, Object> updateMember(Member member) {
		Map<String, Object> resultMap = new HashMap<>();
		Connection con = JdbcTemplate.getConnection();
		int result = 0;

		try {
			result = mdao.updateMember(con, member);
			resultMap.put("isSuccess", true);
			if (result > 0) {
				resultMap.put("res", "수정에 성공했습니다.");
			} else {
				resultMap.put("res", "변경된 데이터가 없습니다. 데이터를 확인하세요.");
			}

			JdbcTemplate.commit(con);

		} catch (MemberException e) {
			JdbcTemplate.rollback(con);
			resultMap.put("isSuccess", false);
			resultMap.put("res", "시스템관리자에게 문의해주세요 \n" + e.getMessage());
		} finally {
			JdbcTemplate.close(con);
		}
		return resultMap;
	}

	//회원 가입
	public Map<String, Object> insertMember(Member member) {

		Map<String, Object> resultMap = new HashMap<>();
		Connection con = JdbcTemplate.getConnection();
		int result = 0;

		try {
			result = mdao.insertMember(con, member);
			resultMap.put("isSuccess", true);
			if (result > 0) {
				resultMap.put("res", "수정에 성공했습니다.");
			} else {
				resultMap.put("res", "변경된 데이터가 없습니다. 데이터를 확인하세요.");
			}

			JdbcTemplate.commit(con);

		} catch (MemberException e) {
			JdbcTemplate.rollback(con);
			resultMap.put("isSuccess", false);
			resultMap.put("res", "시스템관리자에게 문의해주세요 \n" + e.getMessage());
		} finally {
			JdbcTemplate.close(con);
		}
		return resultMap;
	}
	
	//삭제
	public Map<String, Object> delectMember(String userId){
		Map<String, Object> resultMap = new HashMap<>();
		Connection con = JdbcTemplate.getConnection();
		int result = 0;
		
		try {
			result = mdao.delectMember(con, userId);
			resultMap.put("isSuccess", true);
			if (result > 0) {
				resultMap.put("res", "삭제되었습니다.");
			} else {
				resultMap.put("res", "변경된 데이터가 없습니다. 데이터를 확인하세요.");
			}

			JdbcTemplate.commit(con);

		} catch (MemberException e) {
			JdbcTemplate.rollback(con);
			resultMap.put("isSuccess", false);
			resultMap.put("res", "시스템관리자에게 문의해주세요 \n" + e.getMessage());
		} finally {
			JdbcTemplate.close(con);
		}
		return resultMap;
		
	}
}
