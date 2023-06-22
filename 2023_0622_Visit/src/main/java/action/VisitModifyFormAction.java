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
	 * Servlet implementation class VisitModifyFormAction
	 */
	@WebServlet("/visit/modify_form.do")
	public class VisitModifyFormAction extends HttpServlet {
		private static final long serialVersionUID = 1L;

		/**
		 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void service(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// TODO Auto-generated method stub
			int idx = Integer.parseInt(request.getParameter("idx"));
			
			//2.idx에 해당되는 VO 1개정보 얻어온다
			VisitVo vo = VisitDao.getInstance().selectOne(idx);
			
			//3.request binding
			request.setAttribute("vo", vo);	
			
			

			//Dispatcher
			String forward_page = "visit_modify_form.jsp";
			request.getRequestDispatcher(forward_page).forward(request, response);

		}
	}

