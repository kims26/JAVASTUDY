package action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VisitDao;
import vo.VisitVo;

/**
 * Servlet implementation class VisitListAction
 */
//@WebServlet("/visit/list.do")
public class X_VisitListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//  /visit/list.do
		//  /visit/list.do?search=all&search_text=
		//  /visit/list.do?search=name&search_text=�浿
		//  /visit/list.do?search=content&search_text=����
		//  /visit/list.do?search=name_content&search_text=�浿
		
		//1.�������ڵ�
		request.setCharacterEncoding("utf-8");
		
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

		//Dispatcher
		String forward_page = "visit_list.jsp";
		request.getRequestDispatcher(forward_page).forward(request, response);

	}
}