package com.example.demo_board.vo;


public class MemberVo {

	int    mem_idx;
	String mem_name;
	String mem_id;
	String mem_pwd;
	String mem_zipcode;
	String mem_addr;
	String mem_ip;
	String mem_regdate;
	String mem_grade;
	
	
	String mem_pwd_mask;
	
	
	public String getMem_pwd_mask() {
		
		int len = mem_pwd.length();
		int half = len/2;
		//            01234    len=5  half=2    
		// mem_pwd = "12345"
		
		//방법1)
		// StringBuffer sb = new StringBuffer();
		// sb.append(mem_pwd.substring(0, half));
		// for(int i=half ; i< len ;i++) {
		// 	sb.append("*");
		// }
		
		//방법2)
		StringBuffer sb1 = new StringBuffer();
        for(int i=0;i< len ;i++) {
        	
        	if(i<half)
        		sb1.append(mem_pwd.charAt(i));
        	else
        		sb1.append("*");
        }
		
				
		return sb1.toString();
	}

	public MemberVo() {
		
	}
	
	//insert	
	public MemberVo(String mem_name, String mem_id, String mem_pwd, String mem_zipcode, String mem_addr, String mem_ip,
			String mem_grade) {
		super();
		this.mem_name = mem_name;
		this.mem_id = mem_id;
		this.mem_pwd = mem_pwd;
		this.mem_zipcode = mem_zipcode;
		this.mem_addr = mem_addr;
		this.mem_ip = mem_ip;
		this.mem_grade = mem_grade;
	}
	
	//update
	public MemberVo(int mem_idx, String mem_name, String mem_id, String mem_pwd, String mem_zipcode, String mem_addr,
			String mem_ip, String mem_grade) {
		super();
		this.mem_idx = mem_idx;
		this.mem_name = mem_name;
		this.mem_id = mem_id;
		this.mem_pwd = mem_pwd;
		this.mem_zipcode = mem_zipcode;
		this.mem_addr = mem_addr;
		this.mem_ip = mem_ip;
		this.mem_grade = mem_grade;
	}

	public int getMem_idx() {
		return mem_idx;
	}
	public void setMem_idx(int mem_idx) {
		this.mem_idx = mem_idx;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pwd() {
		return mem_pwd;
	}
	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}
	public String getMem_zipcode() {
		return mem_zipcode;
	}
	public void setMem_zipcode(String mem_zipcode) {
		this.mem_zipcode = mem_zipcode;
	}
	public String getMem_addr() {
		return mem_addr;
	}
	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}
	public String getMem_ip() {
		return mem_ip;
	}
	public void setMem_ip(String mem_ip) {
		this.mem_ip = mem_ip;
	}
	public String getMem_regdate() {
		return mem_regdate;
	}
	public void setMem_regdate(String mem_regdate) {
		this.mem_regdate = mem_regdate;
	}
	public String getMem_grade() {
		return mem_grade;
	}
	public void setMem_grade(String mem_grade) {
		this.mem_grade = mem_grade;
	}
	
	
	
}
