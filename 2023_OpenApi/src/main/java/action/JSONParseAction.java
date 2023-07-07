package action;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


/**
 * Servlet implementation class JSONParseAction
 */
@WebServlet("/json1.do")
public class JSONParseAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			
			String json_data = "{\"name\":\"ȫ�浿\",\"age\":30}";
			
			
			JSONObject json   = new JSONObject(json_data);
			
			
			String name = json.getString("name");
			int    age  = json.getInt("age");
			
			//request binding
			request.setAttribute("name", name);
			request.setAttribute("age", age);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Dispatcher
		String forward_page="result_json1.jsp";
		request.getRequestDispatcher(forward_page)
		       .forward(request, response);
		
	}
}
