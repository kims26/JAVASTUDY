package action.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import annotation.RequestMapping;
import annotation.ResponseBody;
import dao.MemberDao;
import vo.MemberVo;

public class MemberController {

	public MemberController() {
		// TODO Auto-generated constructor stub
		//System.out.println("--MemberController()--");
	}
	
	@RequestMapping("/member/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response) {

		//회원목록 가져오기
		List<MemberVo> list = MemberDao.getInstance().selectList();
		
		//request binding
		request.setAttribute("list", list);
		
		return "member_list.jsp";
	}
	
	@RequestMapping("/member/insert_form.do")
	public String insert_form(HttpServletRequest request, HttpServletResponse response) {

		return "member_insert_form.jsp";
	}
	
	@RequestMapping("/member/insert.do")
	public String insert(HttpServletRequest request, HttpServletResponse response) {

		//2.parameter받기
		String mem_name		=	request.getParameter("mem_name");
		String mem_id		=	request.getParameter("mem_id");
		String mem_pwd		=	request.getParameter("mem_pwd");
		String mem_zipcode	=	request.getParameter("mem_zipcode");
		String mem_addr		=	request.getParameter("mem_addr");
		
		//3.ip받기
		String mem_ip		=	request.getRemoteAddr();
		
		//4.포장
		MemberVo  vo = new MemberVo(mem_name, mem_id, mem_pwd, mem_zipcode, mem_addr, mem_ip, "일반");
		
		//5.DB insert
		int res = MemberDao.getInstance().insert(vo);
		
		
		return "redirect:login_form.do";
	}
	
	@RequestMapping("/member/login_form.do")
	public String login_form(HttpServletRequest request, HttpServletResponse response) {

		return "member_login_form.jsp";
	}
	
	@RequestMapping("/member/login.do")
	public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {

		//2.parameter받기
		String  mem_id	= 	request.getParameter("mem_id");
		String  mem_pwd	= 	request.getParameter("mem_pwd");
		
		String  url		=   request.getParameter("url");
		
		if(url==null) url="";
		
		
		//3.mem_id해당되는 데이터 1건 정보 얻어온다
		MemberVo user = MemberDao.getInstance().selectOne(mem_id);
		
		//아이디 틀린경우
		if(user==null) {
			
			response.sendRedirect("login_form.do?reason=fail_id&url=" + url);
			
		}
		
		//비빌번호 틀린경우
		if(user.getMem_pwd().equals(mem_pwd)==false) {
			
			response.sendRedirect("login_form.do?reason=fail_pwd&mem_id=" + mem_id + "&url=" + url);
			
		}
		
		//로그인 처리
		HttpSession session = request.getSession();
		//세션에 로그인한 user를 넣는다
		session.setAttribute("user", user);
		
		
		if(url.isEmpty()==false) {
			
			return "redirect:" + url;
		}
		
		
		return "redirect:../product/list.do";
	}
	
	@RequestMapping("/member/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response) {

		//로그아웃 처리(세션에서 user지우기)
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		
		return "redirect:../product/list.do";
	}
	
	@RequestMapping(value="/member/check_id.do",produces="text/json;charset=utf-8;")
	@ResponseBody
	public String check_id(HttpServletRequest request, HttpServletResponse response) {

		//2.parameter받기
		String mem_id = request.getParameter("mem_id");
		
		//3.MemberVo정보 얻어오기(mem_id이용)
		MemberVo  vo = MemberDao.getInstance().selectOne(mem_id);
		
		//4.결과값얻어온후 JSON포장
		//boolean bResult = (vo==null);
		
		boolean bResult;
		if(vo==null)//사용중인 아이디가 없다
			bResult = true;
		else
			bResult = false;
		
		String json = String.format("{ \"result\" : %b}", bResult);
				
		return json;
	}
	
	
	@RequestMapping("/member/modify_form.do")
	public String modify_form(HttpServletRequest request, HttpServletResponse response) {
		
		int mem_idx = Integer.parseInt(request.getParameter("mem_idx"));
		//수정할 원본데이터 얻어온다
		MemberVo vo = MemberDao.getInstance().selectOne(mem_idx);
		
		//request binding
		request.setAttribute("vo", vo);
		
		return "member_modify_form.jsp";
	}
	
	@RequestMapping("/member/modify.do")
	public String modify(HttpServletRequest request, HttpServletResponse response) {

		//2.parameter받기
		int mem_idx			= Integer.parseInt(request.getParameter("mem_idx"));
		String mem_name 	= request.getParameter("mem_name");
		String mem_id 		= request.getParameter("mem_id");
		String mem_pwd 		= request.getParameter("mem_pwd");
		String mem_zipcode 	= request.getParameter("mem_zipcode");
		String mem_addr 	= request.getParameter("mem_addr");
		String mem_grade 	= request.getParameter("mem_grade");
		
		
		//3.ip
		String mem_ip		= request.getRemoteAddr();
		
		//4.수정데이터 포장
		MemberVo vo = new MemberVo(mem_idx, mem_name, mem_id, mem_pwd, mem_zipcode, mem_addr, mem_ip, mem_grade);
		
		//5.DB update
		int res = MemberDao.getInstance().update(vo);
				
		return "redirect:list.do";
	}
	
	@RequestMapping("/member/delete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response) {

		//1.parameter받기
		int mem_idx = Integer.parseInt(request.getParameter("mem_idx"));
		
		
        //2.DB delete
		int res = MemberDao.getInstance().delete(mem_idx);
		
		return "redirect:list.do";
	}
	
}
