package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Vector;

import dao.BoardDAO;
import dao.MemberDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vo.BoardVO;
import vo.ReplyVO;

public class BoardService {

	//멤버변수 추가
	BoardDAO dao;
	
	//싱글톤 패턴 시작
	private static BoardService instance = new BoardService();
	private BoardService() {
		dao=BoardDAO.getInstance();
	};
	public static BoardService getInstance() {
		if(instance==null) {
			instance=new BoardService();
		}
		return instance;
	}
	//싱글톤 패턴 끝
	
	
	//게시물 읽어오기(start,end)
	public Vector<BoardVO> getBoardList(int start, int end){
		return dao.getBoardList(start,end);
		
	}
	
	//전체 게시물 개수 가져오기
	public int getTotalCount() {
		return dao.getTotalCount();
	}
	
	//게시물 Post
	public void BoardPost(HttpServletRequest req) {
		dao.BoardPost(req);	
	}
	
	//게시물 Read
	public BoardVO getBoardVO(int num) {
		return dao.getBoardVO(num);
		
	}
	
	//게시물 update
	public void BoardUpdate(BoardVO vo) {
		dao.BoardUpdate(vo);
	}
	
	//게시물 delete
	public void BoardDelete(int num) {
		dao.BoardDelete(num);
	}
	
//	//파일 download
	public void Download(HttpServletRequest req, HttpServletResponse resp)
	{
		//파일 경로 설정
		String savedir = BoardDAO.SAVEFOLDER;
		HttpSession session = req.getSession();
		BoardVO vo = (BoardVO)session.getAttribute("BoardVO");
		String filename = vo.getFilename();
		String filepath=savedir+File.separator+vo.getEmail()+File.separator+filename;
		System.out.println(filepath);
		try {
		//파일명 UTF-8으로 인코딩(한글파일)
		filename=URLEncoder.encode(filename,"utf-8").replaceAll("\\+", "%20");
				
			
		//MIME 타입설정
		resp.setContentType("application/octet-stream");
		//헤더 설정
		resp.setHeader("Content-Disposition", "attachment; fileName= "+filename);

		byte[] buff = new byte[4096];
		FileInputStream fin = new FileInputStream(filepath);//파일 -> 서버 방향의 스트림
		ServletOutputStream bout = resp.getOutputStream(); //서버 -> 클라이언트(브라우저) 방향의 스트림
				
		System.out.println("!!!!!!!!!!!!!!!");
		int read;
		while(true)
		{
			read = fin.read(buff,0,buff.length);
			if(read==-1) {
				break;
			}
			bout.write(buff,0,read);
		}
		bout.flush();
		bout.close();
		fin.close();
				
		System.out.println("다운로드 완료!");
				
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public void ReplyPost(ReplyVO vo) {
		dao.ReplyPost(vo);
	}
	
	public Vector<ReplyVO> getReplyList(int num) {
		return dao.getReplyList(num);
	}
	
	
	

}
