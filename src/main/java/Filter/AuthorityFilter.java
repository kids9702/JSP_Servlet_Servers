package Filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vo.MemberVO;

public class AuthorityFilter implements Filter{

	//0 : ANON
	//1	: USER
	//2 : ADMIN
	Map<String,Integer> RoleMap = new HashMap();
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//공지사항 페이지 권한
		RoleMap.put("/Notice/list.do", 0);	//익명/일반/관리자모두
		RoleMap.put("/Notice/post.do", 2);	//관리자만
		
		//자유게시판 페이지 권한
		
		//
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		//req에 있는 Session 꺼내오기
		HttpServletRequest request = (HttpServletRequest)req;
		HttpSession session = request.getSession();
		
		//Role꺼내오기
		MemberVO vo=(MemberVO)session.getAttribute("vo");
		if(vo!=null)
		{
			String tmp=vo.getRole();
			int myrole=0;
			if(tmp.equals("ROLE_ADMIN")) {
				myrole=2;
			}else if(tmp.equals("ROLE_USER")) {
				myrole=1;
			}else {
				myrole=0;
			}
		
			//현재읽고 있는 URL을 가져오기
			String url = request.getRequestURI();
			int pagerole=0;
			if(RoleMap.get(url)==null) {
				pagerole=0;
			}else {
				pagerole=RoleMap.get(url);
			}
			
			//접근 금지 처리
			if(pagerole>=1 && myrole==0) {
				throw new ServletException("익명계정 권한으로는 접근이 불가능합니다..");
			}
			else if(pagerole==2&&myrole<2) {
				throw new ServletException("관리자만 접근할 수 있는 페이지입니다..");
			}
		
		
		}
		
		
		
		
		chain.doFilter(req, resp);
		System.out.println("필터끝!");
		
	}

}
