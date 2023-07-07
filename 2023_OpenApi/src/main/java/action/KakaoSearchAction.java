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

		//keyword.do?latitude=37.483782&longitude=126.9003409&query=제로웨이스트&page=1&size=15
		
		//여기서 parameter받아서 처리할 것
		request.setCharacterEncoding("utf-8");
	
		
	
		
		String query 		=  request.getParameter("query");
		//int    page  		=  Integer.parseInt(request.getParameter("page"));
		//int    size  		=  Integer.parseInt(request.getParameter("size"));
		double latitude  	=  Double.parseDouble(request.getParameter("latitude"));  // 37.482107;
		double longitude 	=  Double.parseDouble(request.getParameter("longitude")); //126.9014201;
		
		
		List<LocationVo> list = KakaoSearchUtils.searchKeyword(query,latitude, longitude, 1, 10);
		
		
		//List<LocationVo> list1 = KakaoSearchUtils.searchKeyword(query1,latitude, longitude, 1, 10);
//		for(LocationVo vo : list) {
//			System.out.printf("(%4dm) : %s\n",vo.getDistance(), vo.getPlace_name());
//		}
		
		//request binding
		request.setAttribute("list", list);
		
		
		
		//Cross Domain(다른서버)-> Ajax요청 허락
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setHeader("Access-Control-Allow-Headers","X-Requested-With");
		
		//Dispatcher
		String forward_page = "kakao_keyword_list.jsp";
		request.getRequestDispatcher(forward_page).forward(request, response);

	}
}





