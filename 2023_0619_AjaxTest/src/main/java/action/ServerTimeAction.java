package action;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServerTimeAction
 */
@WebServlet("/server_time.do")
public class ServerTimeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Calendar  now = Calendar.getInstance();
		
		int year 	= now.get(Calendar.YEAR);
		int month	= now.get(Calendar.MONTH) + 1;
		int day		= now.get(Calendar.DATE);
		
		int hour	= now.get(Calendar.HOUR_OF_DAY);
		int minute	= now.get(Calendar.MINUTE);
		int second	= now.get(Calendar.SECOND);
		

		String dateFormat = String.format("%d-%02d-%02d %02d:%02d:%02d",
									       year,month,day,hour,minute,second    
									);
		
		//응답처리
		response.setContentType("text/html; charset=utf-8;");
		
		//전송(응답)
		response.getWriter().print(dateFormat);
		
		
		
		
	}
}




