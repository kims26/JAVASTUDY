package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import vo.UserVo;

/**
 * Servlet implementation class UserLoginAction
 */
@WebServlet("/user/login.do")
public class UserLoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String emali = request.getParameter("emali");
		String password = request.getParameter("password");
		
		
		UserVo user = UserDao.getInstance().selectOne(emali);
		
		if(user == null) {
			response.sendRedirect("login_form.do?reason=fail_eamil");
			return;
			
		}
		if(user.getPassword().equals(password) == false) {
			
			response.sendRedirect("login_form.do?reason=fail_password&email="+ emali);
			return;
		}
		
		HttpSession session = request.getSession();
		//세션에 로그인한 user를 넣는다
		session.setAttribute("user", user);
		
		//메인페이지로 이동
		response.sendRedirect("list.do");	

		
	}
}