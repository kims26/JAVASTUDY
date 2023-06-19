package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestAction
 */
@WebServlet("/TestAction")
public class TestAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//변수선언
	int a;
	
	//메소드선언
	public void sub() {
		
		
	}
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//지역변수
		int x = 10;
		
		//응답처리
		response.setContentType("text/html; charset=utf-8;");
		PrintWriter out = response.getWriter();
		
		out.println(x);
		
		
		
	}

}
