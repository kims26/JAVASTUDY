package com.example.demo_visit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo_visit.dao.VisitDao;
import com.example.demo_visit.vo.VisitVo;

@Controller
public class VisitController {
    

    @Autowired
    HttpServletRequest request;
    
    @Autowired
    VisitDao visitDao;

    public VisitController(VisitDao visitDao) {
        this.visitDao = visitDao;
    }
    

    //검색기능 추가

    @RequestMapping("/visit/list.do")
    public String list(@RequestParam(name="search",defaultValue = "all") String search,
                       String search_text,     
                       Model model){

        //검색조건을 담을 Map
		Map<String, String> map = new HashMap<String, String>();
		
		if(search.equals("name_content")) {
			//이름 + 내용
			map.put("name", search_text);
			map.put("content", search_text);
		}else if(search.equals("name")) {
			//이름
			map.put("name", search_text);
		}else if(search.equals("content")) {
			//내용
			map.put("content", search_text);
			
		}else if(search.equals("regdate")) {
			//작성일자
			map.put("regdate", search_text);
		}

        List<VisitVo> list = visitDao.selectConditionList(map);

        //결과적으로 request binding
        model.addAttribute("list", list);

        return "visit/visit_list";
    }

    //입력폼 띄우기
    @RequestMapping("/visit/insert_form.do")
	public String insert_form(){

		return "visit/visit_insert_form";
	}

    //글올리기
	@RequestMapping("/visit/insert.do")
	public String insert(VisitVo vo) {

		// /visit/insert.do?name=박길동&content=왔다갑니다&pwd=1234
		
        String content = vo.getContent().replaceAll("\n", "<br>");
        vo.setContent(content);

		//3.ip구하기
		String ip		= request.getRemoteAddr();
		vo.setIp(ip);
		
		//5.DB insert
		int res = visitDao.insert(vo);
		
		//6.목록보기 이동
		return "redirect:list.do"; 
	}

    @RequestMapping(value="/visit/check_pwd.do", 
                    produces="application/json;charset=utf-8;")
	@ResponseBody
	public Map<String,Boolean> check_pwd(int idx,String c_pwd){


        VisitVo vo = visitDao.selectOne(idx);
        //4.비밀번호 같은지 여부 체크(게시물비번==내가입력한비번)
		boolean bResult = vo.getPwd().equals(c_pwd);

        //결과를 JSON전송
        Map<String,Boolean> map = new HashMap<String,Boolean>();
        map.put("result", bResult);

        return map;
    }

    @RequestMapping("/visit/delete.do")
	public String delete(int idx) {
        //DB delete
		int res = visitDao.delete(idx);
		
		return "redirect:list.do";
	}
	
	
	@RequestMapping("/visit/modify_form.do")
	public String modify_form(int idx,Model model) {
		
		//수정원본데이터 가져오기
		VisitVo vo = visitDao.selectOne(idx);
		
		//DB data <br> -> textarea \n
		String content = vo.getContent().replaceAll("<br>", "\n");
		vo.setContent(content);
		
		
		model.addAttribute("vo", vo);
		
		return "visit/visit_modify_form";
	}
	
	@RequestMapping("/visit/modify.do")
	public String method_name(VisitVo vo) {

		
		
		//     content  =  "동해물과\r\n백두산이\r\n"  :   \n => <br> 
		String content 	= vo.getContent().replaceAll("\n", "<br>");
		vo.setContent(content);
		
		//3.ip구하기
		String ip		= request.getRemoteAddr();
        vo.setIp(ip);
		
		//5.DB update
		int res = visitDao.update(vo);
		
		//6.목록보기 이동
		
		return "redirect:list.do";
	}




}
