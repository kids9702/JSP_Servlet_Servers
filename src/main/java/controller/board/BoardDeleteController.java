package controller.board;

import java.io.IOException;

import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardService;
import vo.BoardVO;

public class BoardDeleteController implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		
		
			//파라미터
			
			//입력값
		
			//서비스
			BoardService service = BoardService.getInstance();
			HttpSession session = req.getSession();
			BoardVO boardvo =(BoardVO)session.getAttribute("BoardVO");
			int num = boardvo.getNum();
			service.BoardDelete(num);
			
			
			//이동(읽고있는 페이지로 이동~)
			try {
				String nowPage = req.getParameter("nowPage");
				String start = req.getParameter("start");
				String end=req.getParameter("end");
				String url="/Board/list.do?nowPage="+nowPage+"&start="+start+"&end="+end+"&num="+num;
				resp.sendRedirect(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
		
		
	}

}
