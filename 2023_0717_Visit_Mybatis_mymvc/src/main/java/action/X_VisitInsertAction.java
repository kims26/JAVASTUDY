package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VisitDao;
import vo.VisitVo;

/**
 * Servlet implementation class VisitInsertAction
 */
//@WebServlet("/visit/insert.do")
public class X_VisitInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// /visit/insert.do?name=�ڱ浿&content=�Դٰ��ϴ�&pwd=1234
		
		//1.�������ڵ� ����
		request.setCharacterEncoding("utf-8");
		
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
		response.sendRedirect("list.do");
		

	}
}







