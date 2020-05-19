package com.uclass.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.uclass.bm.exception.BMException;
import com.uclass.bm.model.dao.BookDao;
import com.uclass.bm.model.dao.MemberDao;
import com.uclass.bm.model.vo.Member;
import com.uclass.common.JDBCTemplate;

public class BmTest {

	private JDBCTemplate jdt = JDBCTemplate.getInstance();
	Connection con = null;
	MemberDao md = new MemberDao();
	BookDao bd = new BookDao();

	// TDD 테스트주도개발
	public BmTest() {

	}

	// @Before : 테스트가 실행되기 전에 수행되어야 한느 메서드용 어노테이션
	// @Test : 테스트를 담당하는 메서드용 어노테이션
	// @After : 테스트가 끝난 뒤 후속처리를 담당하는 메서드용 어노테이션

	@Before
	public void beforeTest() {

		System.out.println("******테스트 시작합니다.******");
		con = jdt.getConnection();

	}

	@Test
	public void testMethod() {

		Connection con = jdt.getConnection();
		MemberDao md = new MemberDao();

		try {
			// 테스트통과조건
			// assertNotNull(a);
			// 객체 a가 null이 아님을 확인한다.
			// assertArrayEquals(a,b);
			// 배열 a와 b가 일치함을 확인한다.
			// assertTrue(a)
			// 조건 a가 참인가를 확인한다.
			// assertEquals(a,b);
			// 객체 a와 b가 일치함을 확인한다.

			Member m = new Member();
			//오류가 나는 이유 : MemberDao에 selectLogin 메서드의 매개변수가 boolean이다.
			//강사님 코드
			//Member m2 = md.selectLogin(con, "aaa", "8456");

			assertEquals(m, md.selectLogin(con, "aaa", "8456"));
			//assertEquals(m2, md.selectLogin(con, "aaa", "8456"));

			// assertNotNull(md.selectLogin(con, "bb", "8456"));
			System.out.println(md.selectLogin(con, "aaa", "8456"));
		} catch (BMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testMethod2() {

		System.out.println("두번째 테스트 메서드입니다.");

	}

	@After
	public void afterTest() {

		jdt.rollback(con);
		jdt.close(con);

		System.out.println("******테스트 끝났습니다.******");

	}
}
