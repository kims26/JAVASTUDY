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
		
		//1.�������ڵ� ����
		request.setCharacterEncoding("utf-8");
		
		//2.parameter�ޱ�
		String email = request.getParameter("email");
		
		//3.MemberVo���� ������(mem_id�̿�)
		UserVo  vo = UserDao.getInstance().selectOne(email);
		
		//4.����������� JSON����
		//boolean bResult = (vo==null);
		
		boolean bResult;
		if(vo==null)//������� ���̵� ����
			bResult = true;
		else
			bResult = false;
		
		String json = String.format("{ \"result\" : %b}", bResult);
		
		//5.�������
		response.setContentType("text/json; charset=utf-8;");
		response.getWriter().print(json);
				
		
		
		
	}
}