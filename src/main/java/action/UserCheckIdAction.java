package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import vo.UserVo;



/**
 * Servlet implementation class MemberCheckIdAction
 */
@WebServlet("/user/check_id.do")
public class UserCheckIdAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// /member/check_id.do?mem_id=hong
		
		//1.수신인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		//2.parameter받기
		String email = request.getParameter("email");
		
		//3.MemberVo정보 얻어오기(mem_id이용)
		UserVo  vo = UserDao.getInstance().selectOne(email);
		
		//4.결과값얻어온후 JSON포장
		//boolean bResult = (vo==null);
		
		boolean bResult;
		if(vo==null)//사용중인 아이디가 없다
			bResult = true;
		else
			bResult = false;
		
		String json = String.format("{ \"result\" : %b}", bResult);
		
		//5.결과전송
		response.setContentType("text/json; charset=utf-8;");
		response.getWriter().print(json);
				
		
		
		
	}
}