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
			//1.�������ڵ� ����
			request.setCharacterEncoding("utf-8");
			
			//2.parameter����
			int    mem_idx 		= Integer.parseInt(request.getParameter("mem_idx"));
			String c_pwd 		= request.getParameter("c_pwd");
			
			//3.idx�� �ش�Ǵ� �Խù� 1���� ���´�
			MemberVo vo = MemberDao.getInstance().selectOne(mem_idx);
			
			//4.��й�ȣ ������ ���� üũ(�Խù����==�����Է��Ѻ��)
			boolean bResult = vo.getMem_pwd().equals(c_pwd);
			
			
			//������� ����(JSON){ "result" : true }
			String json = String.format("{ \"result\" : %b }", bResult);
			
			//�������
			response.setContentType("text/json; charset=utf-8;");
			response.getWriter().print(json);

		

		}
	}

