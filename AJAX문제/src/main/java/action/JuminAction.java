package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import myutil.Jumin;


/**
 * Servlet implementation class JuminAction
 */
@WebServlet("/jumin.do")
public class JuminAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//jumin.do?jumin_no=801212-1234560
		
		//1.수신인코딩설정
		request.setCharacterEncoding("utf-8");
	
		//2.parameter받기
		String jumin_no = request.getParameter("jumin_no");
		
		
		
		//3.주민번호 부가정보 얻기
		
		//이미 생성해놓은 객체을 이용해서 부가정보 추출
		Jumin jumin = new Jumin();
		
		jumin.setJumin_no(jumin_no);//주민번호 넣기
		
	
		
		
		//부가정보 추출
		int year		= jumin.getYear(); //출생년도 
		int age			= jumin.getAge();  //나이
		String Tti		= jumin.getTti();
		String season	= jumin.getSeason();
		String gender	= jumin.getGender();
		String ganji	= jumin.getGanji();
		String local	= jumin.getLocal();
		boolean bValid	= jumin.isValid();
		
	
		//JSOn 생성하기 
				JSONObject json= new JSONObject();
				
				json.put("year",year);
				json.put("age",age);
				json.put("tti",Tti);
				json.put("season",season);
				json.put("gender",gender);
				json.put("ganji",ganji);
				json.put("local",local);
				json.put("bValid",bValid);
			
				
				
				//전송타임지정
				response.setContentType("text/json;charset=utf-8;");
				
				//응답
				response.getWriter().print(json.toString());
				
				
				
		
		
	/*	//request binding
		request.setAttribute("year", year);
		request.setAttribute("age", age);
		request.setAttribute("tti", tti);
		request.setAttribute("season", season);
		request.setAttribute("gender", gender);
		request.setAttribute("ganji", ganji);
		request.setAttribute("local", local);
		request.setAttribute("bValid", bValid);
		*/
		
		
		
		//결과 응답 : jumin_result.jsp에게 위임시킴
		
		
		/*
				//Dispatcher(forward)
		RequestDispatcher disp = request.getRequestDispatcher("jumin_result.jsp");
		disp.forward(request, response);
		*/
		
		
		
	}

}
