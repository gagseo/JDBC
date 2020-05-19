package member.view;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import member.controller.MemberController;
import member.model.vo.Member;

// MVC 패턴에 VIEW에 해당한다.
// VIEW는 사용자에게 데이터를 보내주는 형태, 혹은 모습을 의미한다.
// 사용자의 요청을 Controller에게 전달해주는 역할도 한다.
public class Menu {

	private MemberController mController = new MemberController();
	private Scanner sc = new Scanner(System.in);
	private Map<String, Object> loginMap = null;
	private boolean loginOK = false;

	public void startMeun() {

		do {

			System.out.println("로그인 하세요.");
			System.out.print("관리자 아이디 : ");
			String userId = sc.nextLine();
			System.out.print("패스워드 : ");
			String userPwd = sc.nextLine();
			loginMap = mController.selectLogin(userId, userPwd);
			if ((boolean) loginMap.get("isSuccess")) {
				loginOK = (boolean) loginMap.get("res");
			}
		} while (!loginOK);

		displayMenu();

		System.out.println("회원관리프로그램을 종료합니다.");
		return;
	}

	public void displayMenu() {

		int no;

		do {

			System.out.println("****회원관리프로그램****");
			System.out.println("1. 회원 전체 조회");
			System.out.println("2. 아이디로 조회");
			System.out.println("3. 새 회원 등록");
			System.out.println("4. 회원 암호 수정");
			System.out.println("5. 회원 삭제");
			System.out.println("6. 회원 검색");
			System.out.println("7.끝내기");
			System.out.println("번호 선택 : ");
			no = sc.nextInt();
			sc.nextLine();

			switch (no) {
			case 1:
				// Controller에서 전체 회원을 조회하는 메서드를 호출하여
				// 반환받은 결과를 하단의 m에 넣어주세요.
				Map<String, Object> lMap = mController.selectList();
				System.out.println(lMap);
				break;
			case 2:
				System.out.println("조회할 회원 아이디 : ");
				Map<String, Object> listm = mController.selectMember(sc.nextLine());
				// Controller에서 회원 조회하는 메서드 호출
				System.out.println("회원 정보 출력 확인 ------");
				System.out.println(listm);

				break;
			case 3:
				// Controller에서 회원을 추가하는 메서드를 호출하여
				// 메서드의 결과를 하단 res에 넣어준다.
				 System.out.println(mController.insertMember(inputMember()).get("res"));
			
				break;
			case 4:
				Member member = new Member();
				System.out.println("수정할 아이디 입력 : ");
				member.setUserId(sc.nextLine());
				System.out.println("수정할 암호 입력 : ");
				member.setUserPwd(sc.nextLine());
				// 컨트롤러에서 아이디로 비밀번호를 수정하는 메서드를
				// 호출한 다음 다음 메서드가 반환하는 결과를 하단의 Ures에 담으세요.
				System.out.println(mController.updateMember(member).get("res"));
				break;
			
			case 5:
				System.out.println("삭제할 회원 아이디 : ");
				String userId = sc.nextLine();
				// Controller에서 회원을 아이디로 삭제하는 메서드를
				// 호출한 다음 결과 값을 하단의 Dres에 담아주세요.
				System.out.println(mController.deleteMember(userId).get("res"));
				break;
			case 6:
				searchMeun();
				break;
			case 7:
				System.out.println("정말로 끝내시겠습니까? (예:y/아니오:n)");
				if (sc.next().toUpperCase().charAt(0) == 'Y') {
					return;
				}
				break;

			default:
				System.out.println("잘못입력 하셨습니다. 다시 입력하세요.");

			}

		} while (true);
	}

	public void searchMeun() {

		int no;
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		do {
			System.out.println("****회원 검색 메뉴****");
			System.out.println("1. 이름으로 검색");
			System.out.println("2. 나이로 검색");
			System.out.println("3. 성별로 검색");
			System.out.println("4. 가입날짜별 검색");
			System.out.println("5. 이전메뉴로 이동");
			System.out.println("번호 선택 : ");
			no = sc.nextInt();
			sc.nextLine();

			resultMap = null;
			
			switch (no) {
			case 1:
				System.out.println("검색할 이름 : ");
				String nameKeyword = sc.nextLine();
				// Controller에서 이름으로 검색하는 메서드를 호출 한 뒤
				// 결과 값을 하단의 mList에 넣어주세요
				resultMap = mController.selectSearchName(nameKeyword);
				System.out.println(resultMap.get("res"));
				break;
			case 2:
				System.out.println("검색할 나이의 시작값 : ");
				int beginAge = sc.nextInt();
				sc.nextLine();
				System.out.println("검색할 나이의 끝값 : ");
				int endAge = sc.nextInt();
				sc.nextLine();
				// Controller에서 최소나이와 최대나이 사이의 회원을 검색하는
				// 메서드를 호출한 뒤 결과 값을 하단의 mList에 넣어주세요.
				resultMap = mController.selectSearchAge(beginAge, endAge);
				System.out.println(resultMap.get("res"));
				break;
			case 3:
				System.out.println("검색할 회원 성별[남:M/여:F] : ");
				String gender = sc.next().toUpperCase();
				// Controller에서 성별로 회원을 검색하는 메서드를 호출 한 뒤
				// 반환 값을 mList에 넣어주세요.
				resultMap = mController.selectSearchGender(gender);
				System.out.println(resultMap.get("res"));
				break;
			case 4:
				System.out.println("검색할 가입 날짜 시작날짜[yyyy-MM-dd] : ");
				String bd = sc.nextLine();

				System.out.println("검색할 가입날짜 끝날짜[yyyy-MM-dd] : ");
				String ed = sc.nextLine();

				// Controller에서 가입날짜의 범위로 회우너을 조회하는 메서드를 호출 한 후
				// 결과 값을 하단의 mList에 넣어주세요.
				resultMap = mController.selectSearchEnrollDate(Date.valueOf(bd), Date.valueOf(ed));
				System.out.println(resultMap.get("res"));
				break;
			case 5:

				return;

			default:
				System.out.println("잘못입력하였습니다. 다시 입력하세요.");

			}
			
		} while (true);
	}

	public void printList(List<Member> mList) {
		System.out.println("조회된 회원 수" + mList.size());
		for (Member m : mList) {
			System.out.println(m);
		}
	}

	public Member inputMember() {
		Member m = new Member();

		System.out.println("****회원 정보를 입력하세요****");

		System.out.println("회원 아이디 : ");
		m.setUserId(sc.nextLine());

		System.out.println("회원 암호 : ");
		m.setUserPwd(sc.nextLine());

		System.out.println("회원 이름 : ");
		m.setUserName(sc.nextLine());

		System.out.println("성별[남:M/여:F] : ");
		m.setGender(sc.nextLine());

		System.out.println("회원 나이 : ");
		m.setAge(sc.nextInt());
		sc.nextLine();

		System.out.println("회원 전화번호 : ");
		m.setPhone(sc.nextLine());

		System.out.println("회원 이메일 : ");
		m.setEmail(sc.nextLine());

		System.out.println("기타 하고 싶은 말 : ");
		m.setEtc(sc.nextLine());

		return m;
	}

}
