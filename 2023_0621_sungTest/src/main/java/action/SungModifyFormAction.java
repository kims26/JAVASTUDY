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
 * Servlet implementation class SungModifyFormAction
 */
@WebServlet("/sung/modify_form.do")
public class SungModifyFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// /sung/modify_form.do?idx=20
		
		//1.parameter받기
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		//2.idx에 해당되는 VO 1개정보 얻어온다
		SungVo vo = SungTBDao.getInstance().selectOne(idx);
		
		//3.request binding
		request.setAttribute("vo", vo);		
				

		//Dispatcher
		String forward_page = "sung_modify_form.jsp";
		request.getRequestDispatcher(forward_page).forward(request, response);

	}
}