package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import controller.auth.LoginProcController;
import controller.auth.LogoutProcController;
import controller.board.BoardDeleteController;
import controller.board.BoardDeleteReqController;
import controller.board.BoardDownloadController;
import controller.board.BoardListController;
import controller.board.BoardPostController;
import controller.board.BoardReadController;
import controller.board.BoardReplylistController;
import controller.board.BoardReplypostController;
import controller.board.BoardUpdateController;
import controller.board.BoardUpdateReqController;
import controller.home.HomeForwardingController;
import controller.member.MemberDeleteController;
import controller.member.MemberJoinController;
import controller.member.MemberListController;
import controller.member.MemberSearchController;
import controller.member.MemberUpdateController;
import controller.notice.NoticeListController;
import controller.notice.NoticePostController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig(
		fileSizeThreshold=1024*1024*10,	//10MB
		maxFileSize=1024*1024*50,		//50Mb
		maxRequestSize=1024*1024*100	//100Mb
		//location="c:/upload"
		)


public class FrontController extends HttpServlet{

	//URL 저장용도
	Map <String,Controller> list = null;
	
	@Override
	public void init() throws ServletException {
		//컨트롤러 URL-SubController 저장
		list=new HashMap();
		
		//Member관련 URL 
		list.put("/MemberJoin.do", new MemberJoinController());
		list.put("/MemberList.do", new MemberListController());
		list.put("/MemberSearch.do", new MemberSearchController());
		list.put("/MemberUpdate.do", new MemberUpdateController());
		list.put("/MemberDelete.do", new MemberDeleteController());
		
		//Home URL 
		list.put("/Home.do",new HomeForwardingController());
		
		
		//Board관련 URL
		list.put("/Board/list.do", new BoardListController());
		list.put("/Board/post.do", new BoardPostController());
		list.put("/Board/read.do", new BoardReadController());
		list.put("/Board/update.do",new BoardUpdateController());
		list.put("/Board/updateReq.do",new BoardUpdateReqController() );
		list.put("/Board/deleteReq.do", new BoardDeleteReqController());
		list.put("/Board/delete.do", new BoardDeleteController());
		list.put("/Board/download.do",new BoardDownloadController());
		list.put("/Board/replypost.do", new BoardReplypostController());
		list.put("/Board/replylist.do", new BoardReplylistController());
		
		//Notice관련 URL
		list.put("/Notice/list.do", new NoticeListController());
		list.put("/Notice/post.do", new NoticePostController());
		
		//Auth관련 URL
		list.put("/LoginProc.do", new LoginProcController());
		list.put("/LogoutProc.do", new LogoutProcController());
		
	}
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("FrontController TEST");
		String url=req.getRequestURI();
		System.out.println("URI : " + url);
		//컨트롤러 꺼내기 
		Controller subcontroller = list.get(url);
		
		//서브컨트롤러 Execute함수실행
		subcontroller.Execute(req, resp);
		
	}



	

	
		
}
