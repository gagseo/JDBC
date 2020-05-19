package com.uclass.bm.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.uclass.bm.controller.RentBookController;
import com.uclass.bm.model.vo.Member;

public class BookMenu {

	Scanner sc = new Scanner(System.in);
	private RentBookController rbc = new RentBookController();

	public void bookMenu(Member m) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		do {

			System.out.println("***도서검색***");
			System.out.println("1. 도서명 검색");
			System.out.println("2. 작가 검색");
			System.out.println("3. 이전메뉴로");
			System.out.print("번호 : ");
			int no = sc.nextInt();
			sc.nextLine();

			String b_title = "";
			String b_author = "";
			
			resultMap = null;

			switch (no) {
			case 1:
				System.out.print("검색할 도서명을 입력하세요 : ");
				b_title = sc.nextLine();
				break;
			case 2:
				System.out.print("검색할 작가명을 입력하세요 : ");
				b_author = sc.nextLine();
				break;
			case 3:
				return;

			default:
				System.out.println("번호를 정확히 입력해주세요.");

			}

			if (((Map) resultMap.get("res")).size() > 0) {

				System.out.println("대출할 도서가 있으신가요? [Y/N]");

				if (sc.next().toUpperCase().equals("Y")) {
					System.out.println("대출할 도서번호를 입력해주세요 [여러 권일경우 ,로 구분]: ");
					sc.nextLine();
					String rbNo = sc.nextLine();

				}
			}
		} while (true);
	}

}
