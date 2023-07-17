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
		//  /visit/list.do?search=name&search_text=�浿
		//  /visit/list.do?search=content&search_text=����
		//  /visit/list.do?search=name_content&search_text=�浿
		
		//2.parameter�ޱ�
		String search 		=	request.getParameter("search");
		String search_text	=	request.getParameter("search_text");
		
		if(search==null)
			search = "all";
		
		//�˻������� ���� Map
		Map<String, String> map = new HashMap<String, String>();
		
		if(search.equals("name_content")) {
			//�̸� + ����
			map.put("name", search_text);
			map.put("content", search_text);
		}else if(search.equals("name")) {
			//�̸�
			map.put("name", search_text);
		}else if(search.equals("content")) {
			//����
			map.put("content", search_text);
			
		}else if(search.equals("regdate")) {
			//�ۼ�����
			map.put("regdate", search_text);
		}
			
		
		//Data��������
		List<VisitVo> list = VisitDao.getInstance().selectList(map);
		
		//request binding
		request.setAttribute("list", list);
				
		
		return "visit_list.jsp";//forward����
	}
	
	//�Է��� ����
	@RequestMapping("/visit/insert_form.do")
	public String insert_form(HttpServletRequest request, HttpServletResponse response) {

		return "visit_insert_form.jsp";
	}
	
	//�ۿø���
	@RequestMapping("/visit/insert.do")
	public String insert(HttpServletRequest request, HttpServletResponse response) {

		// /visit/insert.do?name=�ڱ浿&content=�Դٰ��ϴ�&pwd=1234
		
		//2.parameter����
		String name    	= request.getParameter("name");
		
		//     content  =  "���ع���\r\n��λ���\r\n"  :   \n => <br> 
		String content 	= request.getParameter("content").replaceAll("\n", "<br>");
		
		String pwd		= request.getParameter("pwd");
		
		
		//3.ip���ϱ�
		String ip		= request.getRemoteAddr();
		
		//4.VisitVo����
		VisitVo vo = new VisitVo(name, content, pwd, ip);
		
		//5.DB insert
		int res = VisitDao.getInstance().insert(vo);
		
		//6.��Ϻ��� �̵�
		//response.sendRedirect("list.do");
		// FrontController�� list.do�� sendRedirect("list.do")��Ų��
		return "redirect:list.do"; 
	}
	
	//��й�ȣ üũ
	@RequestMapping(value="/visit/check_pwd.do", produces="text/json;charset=utf-8;")
	@ResponseBody
	public String check_pwd(HttpServletRequest request, HttpServletResponse response) {

		// /visit/check_pwd.do?idx=5&c_pwd=1234
				
		//2.parameter����
		int    idx 		= Integer.parseInt(request.getParameter("idx"));
		String c_pwd 	= request.getParameter("c_pwd");
		
		//3.idx�� �ش�Ǵ� �Խù� 1���� ���´�
		VisitVo vo = VisitDao.getInstance().selectOne(idx);
		
		//4.��й�ȣ ������ ���� üũ(�Խù����==�����Է��Ѻ��)
		boolean bResult = vo.getPwd().equals(c_pwd);
		
		
		//������� ����(JSON){ "result" : true }
		String json = String.format("{ \"result\" : %b }", bResult);
		
		//�������
		//response.setContentType("text/json; charset=utf-8;");
		//response.getWriter().print(json);
		
		
		return json;
	}
	
	
	
	
	
	
}
