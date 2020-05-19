package com.uclass.bm.view;

import java.util.Scanner;

import com.uclass.bm.controller.BookController;
import com.uclass.bm.model.vo.Member;

public class FrontMenu {

	private Scanner sc = new Scanner(System.in);
	MemberMenu mm = new MemberMenu();
	BookMenu bm = new BookMenu();

	public void frontMenu() {

		do {
			System.out.println("***************Welcome*************");
			System.out.println("0. 회원가입");
			System.out.println("1. Login");
			System.out.println("2. 프로그램 종료");
			System.out.print("입력 : ");
			int no = sc.nextInt();
			sc.nextLine();

			switch (no) {
			case 0:
				mm.joinMenu();
				break;
			case 1:
				displayMenu(mm.LoginMenu());
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
			
			case 1: bm.bookMenu(m);

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

}
