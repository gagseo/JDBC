package member.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static common.JdbcTemplate.*;
import member.exception.MemberException;
import member.model.vo.Member;

public class MemberDao {

	// 기본생성자
	public MemberDao() {

	}

	// 로그인 조회 요청 처리용
	public boolean selectLogin(Connection con, String userId, String userPwd) throws MemberException {

		String sql = "select count(userId) from member where userid = ? and userpwd = ?";

		boolean result = false;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {

			pstm = con.prepareStatement(sql);
			pstm.setString(1, userId);
			pstm.setString(2, userPwd);
			rset = pstm.executeQuery();
			if (rset.next()) {
				if (rset.getInt(1) == 1) {
					result = true;
				} else {

					throw new MemberException("로그인 실패!");
				}
			}
		} catch (SQLException e) {

			throw new MemberException(e.getMessage());

		} finally {
			close(rset, pstm);

		}

		return result;

	}

	// 아이디로 회원 조회 처리
	public Member selectMember(Connection con, String userId) throws MemberException {

		Member m = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		String sql = "select * from member where userid = ?";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			if (rset.next()) {

				m = new Member();
				m.setUserId(rset.getString(1));
				m.setUserPwd(rset.getString(2));
				m.setUserName(rset.getString(3));
				m.setGender(rset.getString(4));
				m.setAge(rset.getInt(5));
				m.setPhone(rset.getString(6));
				m.setEmail(rset.getString(7));
				m.setEtc(rset.getString(8));
				m.setEnroll_date(rset.getDate(9));
				m.setLastmodified(rset.getDate(10));

			} else {

				throw new MemberException("로그인 실패!");
			}

		} catch (SQLException e) {

			throw new MemberException(e.getMessage());

		} finally {
			close(rset, pstm);

		}

		return m;

	}

	// 회원 전체 조회 처리용 : List
	public ArrayList<Member> selectList(Connection con) throws MemberException {

		ArrayList<Member> list = new ArrayList<>();
		Statement stmt = null;
		ResultSet rset = null;

		String sql = "select * from member";

		try {

			stmt = con.createStatement();
			rset = stmt.executeQuery(sql);

			while (rset.next()) {

				Member m = new Member();
				m.setUserId(rset.getString(1));
				m.setUserPwd(rset.getString(2));
				m.setUserName(rset.getString(3));
				m.setGender(rset.getString(4));
				m.setAge(rset.getInt(5));
				m.setPhone(rset.getString(6));
				m.setEmail(rset.getString(7));
				m.setEtc(rset.getString(8));
				m.setEnroll_date(rset.getDate(9));
				m.setLastmodified(rset.getDate(10));
				list.add(m);

			}

		} catch (SQLException e) {

			throw new MemberException(e.getMessage());
		} finally {

			close(rset, stmt);
		}

		return list;
	}

	// 회원 검색 : 성별별 검색
	public List<Member> selectSerachGender(Connection con, String gender) throws MemberException {

		List<Member> result = new ArrayList<Member>();
		PreparedStatement pstm = null;
		ResultSet rset = null;

		String sql = "select * from member where gender = ?";

		try {

			pstm = con.prepareStatement(sql);
			pstm.setString(1, gender);
			rset = pstm.executeQuery();

			while (rset.next()) {

				Member m = new Member();
				m.setUserId(rset.getString(1));
				m.setUserPwd(rset.getString(2));
				m.setUserName(rset.getString(3));
				m.setGender(rset.getString(4));
				m.setAge(rset.getInt(5));
				m.setPhone(rset.getString(6));
				m.setEmail(rset.getString(7));
				m.setEtc(rset.getString(8));
				m.setEnroll_date(rset.getDate(9));
				m.setLastmodified(rset.getDate(10));
				result.add(m);

			}

		} catch (SQLException e) {
			
			throw new MemberException(e.getMessage());
			
		} finally {

			close(rset, pstm);

		}

		return result;

	}

	// 회원 검색 : 나이별 검색
	public List<Member> selectSearchAge(Connection con, int beginAge, int endAge) throws MemberException {

		List<Member> result = new ArrayList<Member>();
		PreparedStatement pstm = null;
		ResultSet rset = null;

		String sql = "select * from member where age between ? and ?";

		try {

			pstm = con.prepareStatement(sql);
			pstm.setInt(1, beginAge);
			pstm.setInt(2, endAge);
			rset = pstm.executeQuery();

			while (rset.next()) {

				Member m = new Member();
				m.setUserId(rset.getString(1));
				m.setUserPwd(rset.getString(2));
				m.setUserName(rset.getString(3));
				m.setGender(rset.getString(4));
				m.setAge(rset.getInt(5));
				m.setPhone(rset.getString(6));
				m.setEmail(rset.getString(7));
				m.setEtc(rset.getString(8));
				m.setEnroll_date(rset.getDate(9));
				m.setLastmodified(rset.getDate(10));
				result.add(m);

			}

		} catch (SQLException e) {
			throw new MemberException(e.getMessage());
		} finally {
			close(rset, pstm);

		}

		return result;

	}

	// 회원 검색 : 가입날짜별 검색
	public List<Member> selectSerachEnrollDate(Connection con, Date begin, Date end) throws MemberException {

		List<Member> result = new ArrayList<Member>();
		PreparedStatement pstm = null;
		ResultSet rset = null;

		String sql = "select * from member where enroll_date between ? and ?";

		try {

			pstm = con.prepareStatement(sql);
			pstm.setDate(1, begin);
			pstm.setDate(2, end);
			rset = pstm.executeQuery();

			while (rset.next()) {

				Member m = new Member();
				m.setUserId(rset.getString(1));
				m.setUserPwd(rset.getString(2));
				m.setUserName(rset.getString(3));
				m.setGender(rset.getString(4));
				m.setAge(rset.getInt(5));
				m.setPhone(rset.getString(6));
				m.setEmail(rset.getString(7));
				m.setEtc(rset.getString(8));
				m.setEnroll_date(rset.getDate(9));
				m.setLastmodified(rset.getDate(10));
				result.add(m);
			}
			
		} catch (SQLException e) {
			throw new MemberException(e.getMessage());
		} finally {
			close(rset, pstm);
		}
		return result;
	}

	// 이름 키워드로 회원 검색(이름에 '이' 가 들어간... 등등)
	public List<Member> selectSearchName(Connection con, String nameKeyword) throws MemberException {

		List<Member> result = new ArrayList<Member>();
		PreparedStatement pstm = null;
		ResultSet rset = null;

		String sql = "select * from member where username like '%'||?||'%'";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, nameKeyword);

			rset = pstm.executeQuery();

			while (rset.next()) {

				Member m = new Member();
				m.setUserId(rset.getString(1));
				m.setUserPwd(rset.getString(2));
				m.setUserName(rset.getString(3));
				m.setGender(rset.getString(4));
				m.setAge(rset.getInt(5));
				m.setPhone(rset.getString(6));
				m.setEmail(rset.getString(7));
				m.setEtc(rset.getString(8));
				m.setEnroll_date(rset.getDate(9));
				m.setLastmodified(rset.getDate(10));
				result.add(m);
			}
		} catch (SQLException e) {
			throw new MemberException(e.getMessage());
		} finally {

			close(rset, pstm);

		}

		return result;

	}

	// 회원 가입 처리용
	public int insertMember(Connection con, Member member) throws MemberException {

		PreparedStatement pstm = null;

		int res = 0;

		try {
			// * JDBC 5단계
			// * 1. Driver 연결
			// * 2. 계정 연결
			// * 3. Query준비
			String sql = "insert into member values(?,?,?,?,?,?,?,?,sysdate,sysdate)";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, member.getUserId());
			pstm.setString(2, member.getUserPwd());
			pstm.setString(3, member.getUserName());
			pstm.setString(4, member.getGender());
			pstm.setInt(5, member.getAge());
			pstm.setString(6, member.getPhone());
			pstm.setString(7, member.getEmail());
			pstm.setString(8, member.getEtc());
			// * 4. 실행 및 Return
			res = pstm.executeUpdate();
			if (res == 0) {
				throw new MemberException("추가된 행이 하나도 없습니다. 조건을 확인하세요.");
			}
		
		} catch (SQLException e) {
			throw new MemberException(e.getMessage());
		} finally { // * 5. DB종료
			close(pstm);
		}
		return res;
	}

	public int updateMember(Connection con, Member member) throws MemberException {
		
		int res = 0;
		PreparedStatement pstm = null;

		try {

			String sql = "UPDATE MEMBER SET USERPWD = ? where USERID = ?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, member.getUserPwd());
			pstm.setString(2, member.getUserId());

			res = pstm.executeUpdate();

		} catch (SQLException e) {
			
			throw new MemberException(e.getMessage());
			
		} finally {
			close(pstm);
		}
		return res;
	}

	// 회원 삭제용
	public int delectMember(Connection con, String userId) throws MemberException {
		
		int res = 0;
		PreparedStatement pstm = null;

		try {

			String sql = "DELETE FROM MEMBER WHERE USERID = ? ";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, userId);
			res = pstm.executeUpdate();
			if (res == 0) {
				throw new MemberException("삭제된 행이 없습니다.  ID를 확인하세요. ");
			}

		} catch (SQLException e) {
			throw new MemberException(e.getMessage());
		} finally {
			close(pstm);
		}
		return res;
	}

}
