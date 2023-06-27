package action;



	import java.io.IOException;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import vo.MemberVo;



	/**
	 * Servlet implementation class MemberCheckPwdAction
	 */
	@WebServlet("/member/check_pwd.do")
	public class MemberCheckPwdAction extends HttpServlet {
		private static final long serialVersionUID = 1L;

		/**
		 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void service(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// TODO Auto-generated method stub
			//1.수신인코딩 설정
			request.setCharacterEncoding("utf-8");
			
			//2.parameter수신
			int    mem_idx 		= Integer.parseInt(request.getParameter("mem_idx"));
			String c_pwd 		= request.getParameter("c_pwd");
			
			//3.idx에 해당되는 게시물 1건을 얻어온다
			MemberVo vo = MemberDao.getInstance().selectOne(mem_idx);
			
			//4.비밀번호 같은지 여부 체크(게시물비번==내가입력한비번)
			boolean bResult = vo.getMem_pwd().equals(c_pwd);
			
			
			//결과문서 생성(JSON){ "result" : true }
			String json = String.format("{ \"result\" : %b }", bResult);
			
			//결과전송
			response.setContentType("text/json; charset=utf-8;");
			response.getWriter().print(json);

		

		}
	}

