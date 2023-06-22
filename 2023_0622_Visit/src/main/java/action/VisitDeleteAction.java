package action;



	import java.io.IOException;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;


import dao.VisitDao;

	/**
	 * Servlet implementation class VisitDeleteAction
	 */
	@WebServlet("/visit/delete.do")
	public class VisitDeleteAction extends HttpServlet {
		private static final long serialVersionUID = 1L;

		/**
		 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void service(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			
			//2.DB delete
			int res = VisitDao.getInstance().delete(idx);
			
			//3.목록보기
			response.sendRedirect("list.do");

		

		}
	}

