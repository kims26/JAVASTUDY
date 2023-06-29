package action.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import vo.MemberVo;

/**
 * Servlet implementation class MemberInsertAction
 */
@WebServlet("/member/insert.do")
public class MemberInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		///member/insert.do?mem_name=ȫ�浿&mem_id=hong&mem_pwd=1234&mem_zipcode=08768&mem_addr=���� ���Ǳ� ������
		
        //1.�������ڵ� ����
		request.setCharacterEncoding("utf-8");
		
		//2.parameter�ޱ�
		String mem_name		=	request.getParameter("mem_name");
		String mem_id		=	request.getParameter("mem_id");
		String mem_pwd		=	request.getParameter("mem_pwd");
		String mem_zipcode	=	request.getParameter("mem_zipcode");
		String mem_addr		=	request.getParameter("mem_addr");
		
		//3.ip�ޱ�
		String mem_ip		=	request.getRemoteAddr();
		
		//4.����
		MemberVo  vo = new MemberVo(mem_name, mem_id, mem_pwd, mem_zipcode, mem_addr, mem_ip, "�Ϲ�");
		
		//5.DB insert
		int res = MemberDao.getInstance().insert(vo);
		
		
		//�α��� ������ �̵�
		response.sendRedirect("login_form.do");

	}
}






