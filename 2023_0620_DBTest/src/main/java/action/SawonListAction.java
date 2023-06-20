package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.SawonDao;
import vo.SawonVo;

/**
 * Servlet implementation class SawonListAction
 */
@WebServlet("/sawon/list.do")
public class SawonListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// /sawon/list.do 
		// /sawon/list.do?deptno=0
		// /sawon/list.do?deptno=10
		// /sawon/list.do?deptno=20
		// /sawon/list.do?deptno=30
		// /sawon/list.do?deptno=40
		
		int deptno = 0;
		
		String sajob = "all";
		
		try {
			
			deptno = Integer.parseInt(request.getParameter("deptno"));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		sajob= request.getParameter("sajob");
		
		
		List<SawonVo> list = null;
		
		
		if(sajob.equals("all")) {
			//전체조회
		    list = SawonDao.getInstance().selectList();
		}else {
			//부서별 조회
			
			list = SawonDao.getInstance().selectSajobList(sajob);
		
		}
		
		//request binding
		request.setAttribute("list", list);
		
		
		//Dispatcher
		String forward_page = "sawon_list.jsp";
		request.getRequestDispatcher(forward_page).forward(request, response);

	}
}