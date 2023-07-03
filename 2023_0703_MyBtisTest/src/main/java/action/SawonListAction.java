package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SawonDao;
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

		// /sawon/list.do?deptno=0&sajob=all
		// /sawon/list.do?deptno=10
		// /sawon/list.do?deptno=20
		int deptno = 0;
		
		String sajob =request.getParameter("sajob");
		
		if(sajob==null)sajob="all";
		
		try {
			
			deptno = Integer.parseInt(request.getParameter("deptno"));
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//전체리스트 가져오기
		List<SawonVo> list = null;
		
		if(deptno==0 && sajob.equals("all")) { 
			// 전체보기
		    list = SawonDao.getInstance().selectList();
		}else if(deptno==0 && sajob.equals("all")){
		    //부서별 보기
			list = SawonDao.getInstance().selectListDeptno(deptno);
		}
		else if(deptno==0 && !sajob.equals("all")){
		    //직급별 보기
			list = SawonDao.getInstance().selectListSajob(sajob);
		}
		else if(deptno!=0 && !sajob.equals("all")){
		    //부서별 직급별 조회
			SawonVo vo =new SawonVo();
			vo.setSajob(sajob);
			vo.setDeptno(deptno);
			
			list = SawonDao.getInstance().selectListDeptnoSajob(vo);
			
			
		}
		
	
		//request binding
		request.setAttribute("list", list);
		
		
		//Dispatcher
		String forward_page = "sawon_list.jsp";
		request.getRequestDispatcher(forward_page).forward(request, response);

	}
}