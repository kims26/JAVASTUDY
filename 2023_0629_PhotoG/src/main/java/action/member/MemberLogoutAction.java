package action.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberLogoutAction
 */
@WebServlet("/member/logout.do")
public class MemberLogoutAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		//�α׾ƿ� ó��(���ǿ��� user�����)
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		
		
		//���������� �̵�
		response.sendRedirect("../photo/list.do");
		

	}
}