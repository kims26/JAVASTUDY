package action.photo;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.PhotoDao;
import vo.MemberVo;
import vo.PhotoVo;

/**
 * Servlet implementation class PhotoInsertAction
 */
@WebServlet("/photo/insert.do")
public class PhotoInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
        // /photo/insert.do?mem_idx=2&p_subject=����&p_content=����&photo=a.jpg
		
		//�α��λ������� ���´�
		HttpSession session = request.getSession();
		MemberVo user 		= (MemberVo) session.getAttribute("user");
		
		if(user==null) {
			
			response.sendRedirect("../member/login_form.do?reason=fail_session_timeout");
			return;
		}
		
		//�������
		//1.�����α��ϱ� : �����->������ ���ϱ�
		String web_path = "/upload/";
		String abs_path = request.getServletContext().getRealPath(web_path);
		
		System.out.println(abs_path);
		
		//2.�ִ����� �뷮����
		int max_size = 1024*1024*1024;// 1GB
		
		//3.MultipartRequest
		MultipartRequest mr = new MultipartRequest(
				                                   request,    //request����
				                                   abs_path,   //ȭ�������� 
				                                   max_size,   //����ũ��
				                                   "utf-8",
				                                   new DefaultFileRenamePolicy());
		
		
		// /photo/insert.do?mem_idx=2&p_subject=����&p_content=����&photo=a.jpg
		//4.����ȭ�ϸ��ϱ�
		String p_filename="no_file";
		File  f = mr.getFile("photo");
		if(f != null) {
			p_filename = f.getName();//�������ε�� ȭ�ϸ�
		}
		
		//5.parameter���ϱ�(ȭ�Ͼ��ε�� �Ķ���� ó���� mr�� �ؾߵȴ�)
		String p_subject = mr.getParameter("p_subject");
		String p_content = mr.getParameter("p_content").replaceAll("\n", "<br>");
		int    mem_idx   = Integer.parseInt(mr.getParameter("mem_idx"));
		//int    mem_idx   = user.getMem_idx();
		
		//6.ip���ϱ�
		String p_ip	= request.getRemoteAddr();
		
		//7.PhotoVo����
		PhotoVo vo = new PhotoVo(p_subject, p_content, p_filename, p_ip, mem_idx); 
		
		//8.DB insert
		int res = PhotoDao.getInstance().insert(vo);
		
		//9.��Ϻ���
		response.sendRedirect("list.do");
		
		

	}
}



