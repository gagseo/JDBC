package com.uclass.bm.view;

import java.util.Map;
import java.util.Scanner;

import com.uclass.bm.controller.MemberController;
import com.uclass.bm.model.dao.MemberDao;
import com.uclass.bm.model.vo.Member;

public class FrontMenu {

	private Scanner sc = new Scanner(System.in);
	private MemberController mController = new MemberController();
	private MemberDao mDao = new MemberDao();

	
	public void frontMenu() {

		do {
			System.out.println("***************Welcome*************");
			System.out.println("0. 회원가입");
			System.out.println("1. Login");
			System.out.println("2. 프로그램 종료");
			System.out.print("입력 : ");
			int no = sc.nextInt();
			sc.nextLine();

			String m_id = "";
			String m_password = "";
			String m_tell = "";

			switch (no) {
			case 0:
				System.out.println("아이디 : ");
				m_id = sc.nextLine();
				System.out.println("패스워드 : ");
				m_password = sc.nextLine();
				System.out.println("전화번호 : ");
				m_tell = sc.nextLine();

				Map<String, Object> iMap = mController.insertMember(inputMember());

				break;
			case 1:
				System.out.println("아이디 : ");
				m_id = sc.nextLine();
				System.out.println("패스워드 : ");
				m_password = sc.nextLine();

				Map<String, Object> lMap = mController.selectLogin(m_id, m_password);

				break;
			case 2:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("정확한 값을 입력해주세요.");
				break;
			}
		} while (true);
	}

	public void displayMenu(Member m) {
		
		do {
			System.out.println("원하는 메뉴를 선택해주세요.");
			System.out.println("1. 도서 메뉴");
			System.out.println("2. MyPage");
			System.out.println("3. LogOut");
			System.out.print("입력 : ");
			int no = sc.nextInt();

			switch (no) {
			case 1:

				break;
			case 2:

				break;
			case 3:

				return;
			default:
				System.out.println("정확한 번호를 입력해주세요.");
				break;
			}

		} while (true);
	}
	
	public Member inputMember() {
		
		Member m = new Member();
		
		System.out.println("*****회원정보를입력하세요*****");
		System.out.println("아이디 : ");
		m.setM_id(sc.nextLine());;
		System.out.println("비밀번호 : ");
		m.setM_password(sc.nextLine());
		System.out.println("전화번호 : ");
		m.setM_tell(sc.nextLine());
		
		
		return m;
		
		
		
		
	}
}
