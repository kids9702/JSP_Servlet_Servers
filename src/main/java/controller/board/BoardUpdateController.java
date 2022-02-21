package controller.board;

import java.io.IOException;

import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardService;
import vo.BoardVO;

public class BoardUpdateController implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String flag=req.getParameter("flag");
		if(flag!=null)	//update.jsp 폼띄우기
		{
			HttpUtil.Forward(req, resp, "/WEB-INF/View/board/update.jsp");
			System.out.println("!!!!!!!!!!!!!!!!!!!!");
		}
		else //update버튼 누른경우
		{
			//파라미터
			
			String subject = req.getParameter("subject");
			String content = req.getParameter("content");
			
			//입력값
			if(subject.isEmpty()||content.isEmpty()) {
				//잘못된 입력 메시지 처리
				//Updateform페이지로 이동..
			}
			//서비스
			BoardService service = BoardService.getInstance();
			HttpSession session = req.getSession();
			BoardVO boardvo =(BoardVO)session.getAttribute("BoardVO");
			BoardVO vo = new BoardVO(boardvo.getNum(),subject,content);
			service.BoardUpdate(vo);
			
			//이동(읽고있는 페이지로 이동~)
			try {
				int num = boardvo.getNum();
				String nowPage = req.getParameter("nowPage");
				String start = req.getParameter("start");
				String end=req.getParameter("end");
				String url="/Board/read.do?nowPage="+nowPage+"&start="+start+"&end="+end+"&num="+num;
				resp.sendRedirect(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
