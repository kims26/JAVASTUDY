package action;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SungTBDao;
import vo.SungVo;

/**
 * Servlet implementation class SungModifyAction
 */
@WebServlet("/sung/modify.do")
public class SungModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String name = request.getParameter("name");
		
		// TODO Auto-generated method stub

		int kor=0;
		int eng=0;
		int mat=0;
		
		try {
			//  " 99" 
			kor = Integer.parseInt(request.getParameter("kor"));
		} catch (Exception e) {
			// TODO: handle exception
			//log ���ܼ� ���߿� ���� üũ
		}
		
		try {
			//  " 99" 
			eng = Integer.parseInt(request.getParameter("eng"));
		} catch (Exception e) {
			// TODO: handle exception
			//log ���ܼ� ���߿� ���� üũ
		}
		
		try {
			//  " 99" 
			mat = Integer.parseInt(request.getParameter("mat"));
		} catch (Exception e) {
			// TODO: handle exception
			//log ���ܼ� ���߿� ���� üũ
		}
		
		
		//3.SungVo ����
		SungVo  vo = new SungVo(idx,name, kor, eng, mat);
		
		//4.DB Insert
		// res : ó���� �����ȯ => ����(1)  ����(0)
		int res = SungTBDao.getInstance().update(vo);
		
		//5.��Ϻ���� �̵�
		response.sendRedirect("list.do");
		

	}

	
}

