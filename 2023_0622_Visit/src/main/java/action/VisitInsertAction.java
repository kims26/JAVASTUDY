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
 * Servlet implementation class VisitInsertAction
 */
@WebServlet("/visit/insert.do")
public class VisitInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//데이터가/inset.do?name=%EA%B9%80%EC%83%81%ED%98%84&content=dd&pwd=1234
		

		//수신인코딩
		request.setCharacterEncoding("utf-8");
		
		
		//parameter수신
		String name = request.getParameter("name");
		
		
		String content = request.getParameter("content").replaceAll("\n", "<br>");
		
		String pwd = request.getParameter("pwd");
		
		
		//ip구하기
		String ip  =request.getRemoteAddr();
		
		//4visit vo로 포장하기
		
		VisitVo vo =new VisitVo(name,content,pwd,ip);
		
		int res =VisitDao.getInstance().insert(vo);
		
		//5목록보기 이동
		response.sendRedirect("list.do");
		
		
	}
}