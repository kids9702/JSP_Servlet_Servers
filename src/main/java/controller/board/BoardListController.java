package controller.board;

import java.util.Vector;

import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;
import vo.BoardVO;
import vo.MemberVO;

public class BoardListController implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		// 파라미터 가져오기(게시물 시작위치, 읽어들일 개수)
		
				int start = 0;		//시작게시물 위치
				int end = 10;		//시작 위치로부터 읽을 총 게시물 수
				
				// 페이지네이션 버튼 파라미터
				String s = req.getParameter("start");
				String e = req.getParameter("end");
				String n = req.getParameter("nowPage");
				
				int nowPage=1;
				
				// 입력값 검증
				
				if(n!=null) {
					nowPage=Integer.parseInt(n);	//nowPage
					start = Integer.parseInt(s);	//start
					end = Integer.parseInt(e);		//end
				}
				
				// 서비스 실행
				BoardService service = BoardService.getInstance();
				Vector<BoardVO> list = service.getBoardList(start,end);
				int tcnt = service.getTotalCount();
				
				// View 이동
				req.setAttribute("list", list);
				req.setAttribute("tcnt", tcnt);
				HttpUtil.Forward(req, resp, "/WEB-INF/View/board/list.jsp?nowPage=" + nowPage);
		
		
	}

}
