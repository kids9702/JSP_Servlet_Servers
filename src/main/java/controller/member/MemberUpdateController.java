package controller.member;

import controller.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberUpdateController  implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("회원정보수정컨트롤러");
		
	}



}
