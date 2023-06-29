package action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;

/**
 * Servlet implementation class MemberDeleteAction
 */
@WebServlet("/member/delete.do")
public class MemberDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
        // /member/delete.do?mem_idx=3
		//1.parameter받기
		int mem_idx = Integer.parseInt(request.getParameter("mem_idx"));
		
		
        //2.DB delete
		int res = MemberDao.getInstance().delete(mem_idx);
		
		//3.목록보기
		response.sendRedirect("list.do");
		
		
		
	
	}
}




