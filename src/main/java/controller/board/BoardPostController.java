package controller.board;

import java.io.IOException;

import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;



public class BoardPostController implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String flag = req.getParameter("flag"); 
		if(flag.equals("true")) //폼에 아직 입력을 안했을떄(처음접근)
		{
			//View이동
			HttpUtil.Forward(req, resp, "/WEB-INF/View/board/post.jsp");
		
		}
		else //폼에 입력다 한다음 Post처리요청
		{		
			//입력값검증
			
			//서비스실행
			BoardService service = BoardService.getInstance();
			service.BoardPost(req);
			
			
			try {
				resp.sendRedirect("/Board/list.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		
	}

}
