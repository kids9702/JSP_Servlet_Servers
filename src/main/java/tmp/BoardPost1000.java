package tmp;

import dao.MemberDAO;
import java.sql.*;

public class BoardPost1000 {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3330/shopdb";
		String id = "root";
		String pw = "1234";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,id,pw);
			
			for(int i=1;i<=1000;i++) {
			pstmt=conn.prepareStatement("insert into board_tbl values(null,?,?,?,?,?,?,?,?)");
			pstmt.setString(1,"b"+i+"@naver.com");
			pstmt.setString(2,"제목"+i);
			pstmt.setString(3,"내용"+i);
			pstmt.setString(4,"2022-02-03");
			pstmt.setString(5,"127.0.0.1");
			pstmt.setInt(6,0);
			pstmt.setString(7,"NONE");
			pstmt.setInt(8,0);
			pstmt.executeUpdate();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{pstmt.close();}catch(Exception e){}
			try{conn.close();}catch(Exception e){}
		}

	}

}
