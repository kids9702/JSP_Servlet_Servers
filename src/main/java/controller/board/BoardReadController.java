package controller.board;

import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardService;
import vo.BoardVO;

public class BoardReadController implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		
		//1파라미터 받기
		int num = Integer.parseInt(req.getParameter("num"));
		String nowPage = req.getParameter("nowPage");
		int start = Integer.parseInt(req.getParameter("start"));
		int end = Integer.parseInt(req.getParameter("end"));
		
		//2입력값 검증
		
		//3서비스 실행
		BoardService service = BoardService.getInstance();
		BoardVO vo = service.getBoardVO(num);
		
		//4이동
		HttpSession session = req.getSession();
		session.setAttribute("BoardVO", vo);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("start", start);
		req.setAttribute("end", end);
		HttpUtil.Forward(req, resp, "/WEB-INF/View/board/read.jsp");
		
	}



}
