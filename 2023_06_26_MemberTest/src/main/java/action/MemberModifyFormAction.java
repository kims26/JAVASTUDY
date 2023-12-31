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
	 * Servlet implementation class MemberModifyFormAction
	 */
	@WebServlet("/member/modify_form.do")
	public class MemberModifyFormAction extends HttpServlet {
		private static final long serialVersionUID = 1L;

		/**
		 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void service(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// TODO Auto-generated method stub
			
			
			int mem_idx = Integer.parseInt(request.getParameter("mem_idx"));
			
			//2.idx에 해당되는 VO 1개정보 얻어온다
			MemberVo vo = MemberDao.getInstance().selectOne(mem_idx);
			
			//3.request binding
			request.setAttribute("vo", vo);	
			

			//Dispatcher
			String forward_page = "member_modify_form.jsp";
			request.getRequestDispatcher(forward_page).forward(request, response);

		}
	}

