package controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import controller.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardService;
import vo.BoardVO;
import vo.ReplyVO;

public class BoardReplylistController implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		//1 파라미터 받아오기(게시물 번호)
		HttpSession session = req.getSession();
		BoardVO boardvo = (BoardVO) session.getAttribute("BoardVO");
		
		//2 입력값 검증
		
		//3 Service 처리(reply_tbl에 게시물 번호에 해당되는 행들을 가지고 와야 함)
		BoardService service = BoardService.getInstance();
		Vector<ReplyVO> list = service.getReplyList(boardvo.getNum());
		
		try {
			PrintWriter out = resp.getWriter();
			
			for(int i=0;i<list.size();i++) {
				out.print("<div class=\"row m-4\">");
				out.print("<div class=\"col-1\"  ><img style=\"width:60px;height:60px;border-radius:25%;\" src=\"/resources/img/reply.png\"></div>");
				out.print("<div class=\"col-11\" >");
				out.print("<div style=\"font-weight:500;font-size:20px;margin-bottom:5px;\">"+list.get(i).getWriter()+"</div>");
				out.print("<div>"+list.get(i).getComment()+"</div>");
				out.print("</div>");
				out.print("</div>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//4 View 이동

	}

}
