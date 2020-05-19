package com.uclass.bm.view;

import java.util.Scanner;

import com.uclass.bm.controller.MemberController;
import com.uclass.bm.controller.RentBookController;
import com.uclass.bm.model.vo.Member;

public class MemberMenu {

	Scanner sc = new Scanner(System.in);
	MemberController mc = new MemberController();
	RentBookController rbc = new RentBookController();
	PrintInfo pi = new PrintInfo();

	public void joinMenu() {

		System.out.print("아이디 : ");
		String userId = sc.next();
		System.out.print("비밀번호 : ");
		String userPw = sc.next();
		System.out.print("전화번호 : ");
		String userTell = sc.next();

		// 코드작성
		System.out.println("회원가입을 축하드립니다.");

	}

	public Member LoginMenu() {

		boolean loginOK = false;
		String id = "";
		Member m = null;

		do {
			System.out.print("아이디 :");
			String userId = sc.next();
			System.out.print("패스워드 : ");
			String userPwd = sc.next();

			if (m != null) {
				loginOK = true;
				id = m.getM_id();
				System.out.println("\n" + id + "님 환영합니다.\n");
			} else {
				System.out.println("아이디나 비밀번호가 틀렸습니다.");
				System.out.println();
			}

		} while (!loginOK);

		return m;
	}

	public void myPage(Member m) {

		do {
			System.out.println("*******MyPage********");
			System.out.println("1. 내 정보 확인하기");
			System.out.println("2. 회원정보변경");
			System.out.println("3. 대출내역확인");
			System.out.println("0. 이전 메뉴로 돌아가기");
			int no = sc.nextInt();

			switch (no) {
			case 1:

				break;
			case 2:

				break;
			case 3:

				break;
			case 0:
				return;
			default:
				break;
			}
		} while (true);
	}

	public void updateInfo(String id) {

		System.out.println("***회원정보변경***");
		System.out.println("변경을 원하지 않는 항목은 X를 입력하세요.");
		System.out.print("비밀번호변경 : ");
		System.out.print("휴대폰번호 변경 : ");

	}

	public void myRentInfo(String id) {

		System.out.println("******대출내역******");

		while (true) {

			System.out.println("상세보기 [대출번호 or 0(종료) 입력]: ");

			System.out.println("********상세정보********");

			System.out.println("**********************");
		}
	}

	public void rentExtend(Member m) {

		System.out.println("******연장하기******");

		while (true) {

			System.out.println("상세보기 [대출번호 or 0(종료) 입력]: ");
			int no = sc.nextInt();

			System.out.println("********상세정보********");

			System.out.println("1. 도서 반납 ");
			System.out.println("2. 도서 연장 ");
			System.out.println("번호를 선택하세요 : ");
			no = sc.nextInt();

			switch (no) {
			case 1:
				System.out.print("반납할 도서의 대출도서코드를 입력하세요:");

				break;
			case 2:
				System.out.print("연장할 도서의 대출도서코드를 입력하세요:");
				int rno2 = sc.nextInt();

				break;

			default:
				break;
			}

			System.out.println("**********************");
		}

	}
	
	
}