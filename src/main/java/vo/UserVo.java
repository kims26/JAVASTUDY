package vo;

public class UserVo {

	
	int u_idx;
	String name;
	String nickname;
	String email;
	String password;
	String addr;
	String phone_number;
	String profilelmage;
	

	
	public UserVo( String name, String nickname, String email, String password, String addr,
			String phone_number) {
		super();
		
		this.name = name;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.addr = addr;
		this.phone_number = phone_number;
	
	}
	
	

	public UserVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	


	public int getU_idx() {
		return u_idx;
	}
	public void setU_idx(int u_idx) {
		this.u_idx = u_idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getProfilelmage() {
		return profilelmage;
	}
	public void setProfilelmage(String profilelmage) {
		this.profilelmage = profilelmage;
	}
	
	
	
	
}
