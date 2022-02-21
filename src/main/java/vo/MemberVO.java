package vo;

public class MemberVO {
	private String email;
	private String pwd;
	private int zipcode;
	private String addr1;
	private String addr2;
	private String role;
	
	public MemberVO(String email, String pwd, int zipcode, String addr1, String addr2) 
	{
		this.email = email;
		this.pwd = pwd;
		this.zipcode = zipcode;
		this.addr1 = addr1;
		this.addr2 = addr2;
		
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public String getPwd() {
		return pwd;
	}
	public int getZipcode() {
		return zipcode;
	}
	public String getAddr1() {
		return addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public String getRole() {
		return role;
	}
	
	

}
