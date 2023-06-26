package action;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import vo.MemberVo;

/**
 * Servlet implementation class MemberInsertAction
 */
@WebServlet("/member/insert.do")
public class MemberInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String mem_name =request.getParameter("mem_name");
		
		String mem_id = request.getParameter("mem_id");
		
		String mem_pwd =request.getParameter("mem_pwd");
		
		String mem_zipcode =request.getParameter("mem_zipcode");
		
		String mem_addr =request.getParameter("mem_addr");
		
		String mem_ip  =request.getRemoteAddr();
		
		String mem_grade ="¿œπ›";
		
		
		MemberVo vo =new MemberVo(mem_name,mem_pwd,mem_id,mem_zipcode,mem_addr,mem_ip,mem_grade);
		
		int res =MemberDao.getInstance().insert(vo);
		
		response.sendRedirect("list.do");
		
		

	

	}
}

