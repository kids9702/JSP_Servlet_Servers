package controller.auth;

import java.io.IOException;

import controller.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutProcController implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {

			//현재 접속중인 계정의 세션객체 꺼내오기
			HttpSession session = req.getSession();
			//세션 해제
			session.invalidate();
			//View로 이동(Login.jsp 로 이동)
			try {
				
				resp.sendRedirect("/Login.jsp");
				 
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
	}

}
