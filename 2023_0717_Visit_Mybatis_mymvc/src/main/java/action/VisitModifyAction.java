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

@WebServlet("/visit/modify.do")
public class VisitModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// /visit/modify.do?name=박길동&content=왔다갑니다&pwd=1234
		
		//1.수신인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		//2.parameter수신
		int    idx      =  Integer.parseInt(request.getParameter("idx"));
		String name    	= request.getParameter("name");
		
		//     content  =  "동해물과\r\n백두산이\r\n"  :   \n => <br> 
		String content 	= request.getParameter("content").replaceAll("\n", "<br>");
		
		String pwd		= request.getParameter("pwd");
		
		
		//3.ip구하기
		String ip		= request.getRemoteAddr();
		
		//4.VisitVo포장
		VisitVo vo = new VisitVo(idx, name, content, pwd, ip);
		
		//5.DB update
		int res = VisitDao.getInstance().update(vo);
		
		//6.목록보기 이동
		response.sendRedirect("list.do");
		

	}
}







