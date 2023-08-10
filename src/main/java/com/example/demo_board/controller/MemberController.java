package com.example.demo_board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo_board.dao.MemberDao;
import com.example.demo_board.vo.MemberVo;


@Controller
public class MemberController {

    @Autowired
    HttpServletRequest request;

    @Autowired
	HttpSession session;

	MemberDao memberDao;

	@Autowired
	public MemberController(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@RequestMapping("/member/list.do")
	public String list(Model model) {

		List<MemberVo> list = memberDao.selectList();
		
		//request binding
		model.addAttribute("list", list);
		
		return "member/member_list";
	}
	
	@RequestMapping("/member/insert_form.do")
	public String insert_form() {

		return "member/member_insert_form";
	}
	
	@RequestMapping("/member/insert.do")
	public String insert(MemberVo vo) {

		
		String mem_ip		=	request.getRemoteAddr();
		vo.setMem_ip(mem_ip);
		
		//5.DB insert
		int res =	memberDao.insert(vo);
		if(res==0){}
		
		
		return "redirect:login_form.do";
	}
	
	@RequestMapping("/member/login_form.do")
	public String login_form() {

		return "member/member_login_form";
	}
	
	@RequestMapping("/member/login.do")
	public String login(String mem_id,String mem_pwd ,
	                    @RequestParam(name="url",defaultValue = "")   String url,
	                    RedirectAttributes ra) {

		
		MemberVo user = memberDao.selectOneFromId(mem_id);
		
		if(user==null) {
			
			ra.addAttribute("reason","fail_id");
			return "redirect:login_form.do";

		}
		
		if(user.getMem_pwd().equals(mem_pwd)==false) {
			
			ra.addAttribute("reason","fail_pwd");
			ra.addAttribute("mem_id",mem_id);
			// login_form.do?reason=fail_pwd&mem_id=one
			return "redirect:login_form.do";
		}
		
		session.setAttribute("user", user);
		
		//돌아갈주소가 없으면 메인페이지로 이동
		if(url.isEmpty()){
			return "redirect:../board/list.do";
		}

		//url이 있으면 그 페이지로 이동해라
		return "redirect:" + url;


		
	}
	
	@RequestMapping("/member/logout.do")
	public String logout() {

		session.removeAttribute("user");
		
		return "redirect:../board/list.do";
	}
	
	@RequestMapping(value="/member/check_id.do",produces="application/json;charset=utf-8;")
	@ResponseBody
	public Map<String,Boolean> check_id(String mem_id) {
		
		
		MemberVo  vo = memberDao.selectOneFromId(mem_id);
		
		boolean bResult;
		if(vo==null)
			bResult = true;
		else
			bResult = false;
		
		Map <String,Boolean>map = new HashMap<String,Boolean>();
		map.put("result", bResult);

		return map;
	}
	
	
	@RequestMapping("/member/modify_form.do")
	public String modify_form(int mem_idx,Model model) {

		MemberVo vo = memberDao.selectOneFromIdx(mem_idx);
		
		//request binding
		model.addAttribute("vo", vo);
		
		return "member/member_modify_form";
	}
	
	@RequestMapping("/member/modify.do")
	public String modify(MemberVo vo) {

		//3.ip
		String mem_ip		= request.getRemoteAddr();
		vo.setMem_ip(mem_ip);
		
		//5.DB update
		int res = memberDao.update(vo);
		if(res==0){}
				
		return "redirect:list.do";
	}
	
	@RequestMapping("/member/delete.do")
	public String delete(int mem_idx) {

        //2.DB delete
		int res = memberDao.delete(mem_idx);
		if(res==0){}
		
		return "redirect:list.do";
	}
	
}
