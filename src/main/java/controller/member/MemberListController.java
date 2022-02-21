package controller.member;

import controller.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberListController  implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("회원전체조회컨트롤러");
		
	}

}
