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
        // /visit/modify_form.do?idx=33
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		//수정원본데이터 가져오기
		VisitVo vo = VisitDao.getInstance().selectOne(idx);
		
		//DB data <br> -> textarea \n
		String content = vo.getContent().replaceAll("<br>", "\n");
		vo.setContent(content);
		
		
		request.setAttribute("vo", vo);
				
		
		//Dispatcher
		String forward_page = "visit_modify_form.jsp";
		request.getRequestDispatcher(forward_page).forward(request, response);

	}
}