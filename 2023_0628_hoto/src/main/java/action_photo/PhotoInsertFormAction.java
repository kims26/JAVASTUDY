package action_photo;


import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class PhotoInsertFormAction
 */
@WebServlet("/photo/insert_form.do")
public class PhotoInsertFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
ServletContext application = request.getServletContext();
		
		String web_path = "/upload/";//URL���
		String abs_path = application.getRealPath(web_path);//File���
		//System.out.println(abs_path);
		int    max_size = 1024*1024*1024;// 1GB
		
		//ȭ�Ͼ��ε� ó�� ��ü
		MultipartRequest mr = new MultipartRequest(
				                           request,   //����
				                           abs_path,  //ȭ��������
				                           max_size,  //ȭ���ִ�뷮
				                           "utf-8",   //�������ڵ�
				                           new DefaultFileRenamePolicy()
				                           );
		//  DefaultFileRenamePolicy : ������ ȭ�ϸ��� �����ϸ� 
		//                            ���ε�ȭ���� ȭ�ϸ��� �����ؼ� ����   
		
		//���ε� ȭ�ϸ� ������
		String filename1 = "no_file";
		String filename2 = "no_file";
		//���1
		
		File f = mr.getFile("photo");
		if(f != null) {
			filename1 = f.getName();//���ε�� ȭ�ϸ�
		}
		
		
		File f1 = mr.getFile("photo2");
		if(f1 != null) {
			filename2 = f1.getName();//���ε�� ȭ�ϸ�
		}
		
	
		
		
		
		
		
		
		//���2
		//filename = mr.getOriginalFileName("photo");
		
		
		//ȭ�ϰ� ���� �Ѿ�� parameter���� ���ϱ�
		String p_subject =  mr.getParameter("p_subject");
		
		
		//request binding
		request.setAttribute("p_subject", p_subject);
		request.setAttribute("filename1", filename1);
		request.setAttribute("filename2", filename2);
		// TODO Auto-generated method stub

		//Dispatcher
		String forward_page = "photo_insert_form.jsp";
		request.getRequestDispatcher(forward_page).forward(request, response);

	}
}

