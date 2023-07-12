package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;

import dao.UserDao;
import vo.UserVo;

/**
 * Servlet implementation class UserInsertACtion
 */
@WebServlet("/user/insert.do")
public class UserInsertACtion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String name				= request.getParameter("name");
		String nickname 		= request.getParameter("nickname");
		String emali 			= request.getParameter("email");
		String password 		= request.getParameter("password");
		String addr 			= request.getParameter("addr");
		String phone_neumber 	= request.getParameter("phone_number");
		
		
		UserVo vo = new UserVo( name, nickname, emali, password, addr, phone_neumber);

		
		int res = UserDao.getInstance().insert(vo);
		
		response.sendRedirect("login_form.do");

	}
}