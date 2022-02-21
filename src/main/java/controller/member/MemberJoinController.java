package controller.member;

import java.io.IOException;

import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;
import vo.MemberVO;

public class MemberJoinController  implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
//		System.out.println("회원가입컨트롤러");
			
		//01 파라미터 받기
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		String addr1=req.getParameter("addr1");
		String addr2=req.getParameter("addr2");
		int zipcode = Integer.parseInt(req.getParameter("zipcode"));
		System.out.printf("%s %s %s %s %d\n", email,pwd,addr1,addr2,zipcode);
		
		
		//02 입력값 검증(Front-Javascript로 처리가능)
		if(email.isEmpty()||pwd.isEmpty()||addr1.isEmpty()||addr1.isEmpty()
			||addr1.isEmpty()||req.getParameter("zipcode").isEmpty()
		)
		{
			req.setAttribute("MSG", "입력값이 올바르지 않습니다..");
			HttpUtil.Forward(req, resp, "이동시킬URL");
			
		}
			
		//03 서비스 작업
		MemberService service=MemberService.getInstance();
		MemberVO vo = new MemberVO(email,pwd,zipcode,addr1,addr2);
		service.MemberJoin(vo);
		
		//04 페이지 이동(View) - Redirect vs Forward
		try {
			resp.sendRedirect("Login.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
		
	}

}
