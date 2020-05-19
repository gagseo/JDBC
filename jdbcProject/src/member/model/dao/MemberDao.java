package member.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import member.exception.MemberException;
import member.model.vo.Member;

public class MemberDao {

	String user = "bm";
	String password = "test1";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String od = "oracle.jdbc.driver.OracleDriver";

	// 기본생성자
	public MemberDao() {

	}

	// 로그인 조회 요청 처리용
	public boolean selectLogin(String userId, String userPwd) throws MemberException {

		String sql = "select count(userId) from member where userid = ? and userpwd = ?";

		boolean result = false;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
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
			return result;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				rset.close();
				pstm.close();
				con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return false;

	}

	// 아이디로 회원 조회 처리
	public Member selectMember(String userId) {

		Member m = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		String sql = "select * from member where userid = ?";

		try {

			Class.forName(od);
			con = DriverManager.getConnection(url);
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

			}
			return m;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return m;

	}

	// 회원 전체 조회 처리용 : List
	public ArrayList<Member> selectList() throws MemberException {

		ArrayList<Member> list = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rset = null;

		String sql = "select * from member";

		try {
			Class.forName(od);
			con = DriverManager.getConnection(url, user, password);
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

			if (list.size() == 0) {
				throw new MemberException("회원이 없습니다.");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				rset.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return list;
	}

	// 회원 검색 : 성별별 검색
	public List<Member> selectSerachGender(String gender) throws MemberException {

		List<Member> result = new ArrayList<Member>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		String sql = "select * from member where gender = ?";

		try {

			Class.forName(od);
			con = DriverManager.getConnection(url, user, password);
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

			if (result.size() == 0) {
				throw new MemberException("회원이 없습니다.");
			}

			return result;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				rset.close();
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return result;

	}

	// 회원 검색 : 나이별 검색
	public List<Member> selectSearchAge(int beginAge, int endAge) throws MemberException {

		List<Member> result = new ArrayList<Member>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		String sql = "select * from member where age between ? and ?";

		try {
			Class.forName(od);
			con = DriverManager.getConnection(url, user, password);
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

			if (result.size() == 0) {
				throw new MemberException("회원이 없습니다.");
			}

			return result;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				rset.close();
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return result;

	}

	// 회원 검색 : 가입날짜별 검색
	public List<Member> selectSerachEnrollDate(Date begin, Date end) throws MemberException {

		List<Member> result = new ArrayList<Member>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		String sql = "select * from member where enroll_date between ? and ?";

		try {
			Class.forName(od);
			con = DriverManager.getConnection(url, user, password);
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

			if (result.size() == 0) {
				throw new MemberException("회원이 없습니다.");
			}

			return result;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				rset.close();
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return result;

	}
	
	//이름 키워드로 회원 검색(이름에 '이' 가 들어간... 등등)
	public List<Member> selectSearchName(String nameKeyword) throws MemberException{
		
		List<Member> result = new ArrayList<Member>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		String sql = "select * from member where username like '%'||?||'%'";

		try {
			Class.forName(od);
			con = DriverManager.getConnection(url, user, password);
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

			if (result.size() == 0) {
				throw new MemberException("회원이 없습니다.");
			}
			
			return result;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				rset.close();
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return result;
		
	}
	
	  // 회원 가입 처리용
	   public int insertMember(Member member) throws MemberException {

	      Connection con = null;
	      PreparedStatement pstm = null;

	      int res = 0;

	      try {
	         // * JDBC 5단계
	         // * 1. Driver 연결
	         Class.forName(od);
	         // * 2. 계정 연결
	         con = DriverManager.getConnection(url, user, password);
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
	         return res;
	        
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally { // * 5. DB종료
	         try {
	            pstm.close();
	            con.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return res;
	   }

	   
	   public int updateMember(Member member) throws MemberException {
	      int res = 0;

	      Connection con = null;
	      PreparedStatement pstm = null;

	      try {
	         Class.forName(od);
	         con = DriverManager.getConnection(url, user, password);
	         String sql = "UPDATE MEMBER SET USERPWD = ? where USERID = ?";
	         pstm = con.prepareStatement(sql);
	         pstm.setString(1, member.getUserPwd());
	         pstm.setString(2, member.getUserId());

	         res = pstm.executeUpdate();
	         if (res == 0) {
	            throw new MemberException("변경된 행이 하나도 없습니다. 조건을 확인하세요.");
	         }
	         return res;
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            pstm.close();
	            con.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return res;
	   }

	   // 회원 삭제용
	   public int delectMember(String userId) throws MemberException {
	      int res = 0;
	      Connection con = null;
	      PreparedStatement pstm = null;

	      try {
	         Class.forName(od);
	         con = DriverManager.getConnection(url, user, password);
	         String sql = "DELETE FROM MEMBER WHERE USERID = ? ";
	         pstm = con.prepareStatement(sql);
	         pstm.setString(1, userId);
	         res = pstm.executeUpdate();
	         if (res == 0) {
	            throw new MemberException("삭제된 행이 없습니다.  ID를 확인하세요. ");
	         }
	         return res;

	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            pstm.close();
	            con.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return res;
	   }

}
