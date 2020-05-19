package member.view;

import java.util.Map;

public class ErrorMsg {

	public void printErr(Map<String, Object> errMsg) {
		
		System.out.println("\n\n\n\n******에러메시지창******");
		System.out.println(errMsg);
		System.out.println("\n\n\n\n");
	}
}
