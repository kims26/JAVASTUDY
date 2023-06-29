package action.photo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PhotoDao;
import vo.PhotoVo;

/**
 * Servlet implementation class PhotoListAction
 */
@WebServlet("/photo/list.do")
public class PhotoListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<PhotoVo> list = PhotoDao.getInstance().selectList();
		
		//request binding
		request.setAttribute("list", list);

		//Dispatcher
		String forward_page = "photo_list.jsp";
		request.getRequestDispatcher(forward_page).forward(request, response);

	}
}