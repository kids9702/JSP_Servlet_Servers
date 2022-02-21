package controller.member;

import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vo.MemberVO;

public class MemberSearchController  implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		
		//1 세션에서 접속한 계정 정보(vo) 꺼내오기
		HttpSession session=req.getSession();
		MemberVO vo =(MemberVO)session.getAttribute("vo");
		
		//2 vo를 지정된 페이지로 전달
		req.setAttribute("vo", vo);
		HttpUtil.Forward(req, resp, "/WEB-INF/View/SearchResult.jsp");
		
	}

}
