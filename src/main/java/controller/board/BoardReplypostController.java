package controller.board;

import controller.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardService;
import vo.BoardVO;
import vo.MemberVO;
import vo.ReplyVO;

public class BoardReplypostController implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		//1 파라미터 받아오기
			String comment = req.getParameter("comment");
			
			HttpSession session = req.getSession();
			MemberVO membervo = (MemberVO) session.getAttribute("vo");
			BoardVO boardvo = (BoardVO) session.getAttribute("BoardVO");
			
			ReplyVO replyvo = new ReplyVO(
					boardvo.getNum(),
					membervo.getEmail(),
					comment
					);
		//2 입력값 검증
		
		//3 Service 처리
		BoardService service = BoardService.getInstance();
		service.ReplyPost(replyvo);
		//4 View 이동
		
	}

}
