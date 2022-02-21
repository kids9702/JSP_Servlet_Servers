package service;

import dao.MemberDAO;
import vo.MemberVO;

public class MemberService {
	
	//DAO연결용 참조변수
	MemberDAO dao=null;
	
	//싱글톤 패턴 시작
	private static MemberService instance = new MemberService();
	private MemberService() {
		dao=MemberDAO.getInstance();	
	};
	public static MemberService getInstance() {
		if(instance==null) {
			instance=new MemberService();
		}
		return instance;
	}
	//싱글톤 패턴 끝
	
	//회원가입함수
	public boolean MemberJoin(MemberVO vo) {
		return dao.MemberJoin(vo);	
	}
	//회원조회함수
	public MemberVO MemberSearch(String email) {
		return dao.MemberSearch(email);
		
	}
	
	
	//회원삭제함수
	//회원수정함수
	
	

}
