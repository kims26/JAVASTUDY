package action.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestMapping;
import annotation.ResponseBody;
import dao.VisitDao;
import vo.VisitVo;

public class VisitController {

	@RequestMapping("/visit/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response) {

		//  /visit/list.do
		//  /visit/list.do?search=all&search_text=
		//  /visit/list.do?search=name&search_text=길동
		//  /visit/list.do?search=content&search_text=수정
		//  /visit/list.do?search=name_content&search_text=길동
		
		//2.parameter받기
		String search 		=	request.getParameter("search");
		String search_text	=	request.getParameter("search_text");
		
		if(search==null)
			search = "all";
		
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
			
		
		//Data가져오기
		List<VisitVo> list = VisitDao.getInstance().selectList(map);
		
		//request binding
		request.setAttribute("list", list);
				
		
		return "visit_list.jsp";//forward정보
	}
	
	//입력폼 띄우기
	@RequestMapping("/visit/insert_form.do")
	public String insert_form(HttpServletRequest request, HttpServletResponse response) {

		return "visit_insert_form.jsp";
	}
	
	//글올리기
	@RequestMapping("/visit/insert.do")
	public String insert(HttpServletRequest request, HttpServletResponse response) {

		// /visit/insert.do?name=박길동&content=왔다갑니다&pwd=1234
		
		//2.parameter수신
		String name    	= request.getParameter("name");
		
		//     content  =  "동해물과\r\n백두산이\r\n"  :   \n => <br> 
		String content 	= request.getParameter("content").replaceAll("\n", "<br>");
		
		String pwd		= request.getParameter("pwd");
		
		
		//3.ip구하기
		String ip		= request.getRemoteAddr();
		
		//4.VisitVo포장
		VisitVo vo = new VisitVo(name, content, pwd, ip);
		
		//5.DB insert
		int res = VisitDao.getInstance().insert(vo);
		
		//6.목록보기 이동
		//response.sendRedirect("list.do");
		// FrontController가 list.do로 sendRedirect("list.do")시킨다
		return "redirect:list.do"; 
	}
	
	//비밀번호 체크
	@RequestMapping(value="/visit/check_pwd.do", produces="text/json;charset=utf-8;")
	@ResponseBody
	public String check_pwd(HttpServletRequest request, HttpServletResponse response) {

		// /visit/check_pwd.do?idx=5&c_pwd=1234
				
		//2.parameter수신
		int    idx 		= Integer.parseInt(request.getParameter("idx"));
		String c_pwd 	= request.getParameter("c_pwd");
		
		//3.idx에 해당되는 게시물 1건을 얻어온다
		VisitVo vo = VisitDao.getInstance().selectOne(idx);
		
		//4.비밀번호 같은지 여부 체크(게시물비번==내가입력한비번)
		boolean bResult = vo.getPwd().equals(c_pwd);
		
		
		//결과문서 생성(JSON){ "result" : true }
		String json = String.format("{ \"result\" : %b }", bResult);
		
		//결과전송
		//response.setContentType("text/json; charset=utf-8;");
		//response.getWriter().print(json);
		
		
		return json;
	}
	
	
	
	
	
	
}
