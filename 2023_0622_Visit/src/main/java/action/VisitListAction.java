package action;	
	import java.io.IOException;
import java.util.List;

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
	@WebServlet("/visit/list.do")
	public class VisitListAction extends HttpServlet {
		private static final long serialVersionUID = 1L;

		/**
		 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void service(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			// TODO Auto-generated method stub
			List<VisitVo> list = VisitDao.getInstance().selectList(); 
			
			request.setAttribute("list", list);
	
			//Dispatcher
			String forward_page = "visit_list.jsp";
			request.getRequestDispatcher(forward_page).forward(request, response);

		}
	}

