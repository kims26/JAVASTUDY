package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SungTBDao;
import vo.SungVo;

/**
 * Servlet implementation class SungListAction
 */
@WebServlet("/sung/list.do")
public class SungListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<SungVo> list = SungTBDao.getInstance().selectList(); 
		
		//request binding
		request.setAttribute("list", list);

		//Dispatcher
		String forward_page = "sung_list.jsp";
		request.getRequestDispatcher(forward_page)
		       .forward(request, response);

	}
}