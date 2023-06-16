package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.KakaoSearchUtils;
import vo.LocationVo;

/**
 * Servlet implementation class KakaoSearchAction
 */
@WebServlet("/kakao/keyword.do")
public class KakaoSearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		String query = "Ä¿ÇÇ";
		int    page  =  1;
		int    size  = 15;
		double latitude  = 37.482107;
		double longitude = 126.9014201;
		
		
		List<LocationVo> list = KakaoSearchUtils.searchKeyword(query, latitude, longitude, page, size);
		
		for(LocationVo vo :list) {
			
			System.out.println(vo.getPlace_name());
		}
		
		//request binding
		request.setAttribute("list", list);
		
		//Dispatcher
		String forward_page = "kakao_keyword_list.jsp";
		request.getRequestDispatcher(forward_page).forward(request, response);

	}
}