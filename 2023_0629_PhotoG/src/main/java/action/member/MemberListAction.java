package action.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import vo.MemberVo;

/**
 * Servlet implementation class MemberListAction
 */
@WebServlet("/member/list.do")
public class MemberListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//회원목록 가져오기
		List<MemberVo> list = MemberDao.getInstance().selectList();
		
		//request binding
		request.setAttribute("list", list);
		
		//Dispatcher
		String forward_page = "member_list.jsp";
		request.getRequestDispatcher(forward_page).forward(request, response);

	}
}