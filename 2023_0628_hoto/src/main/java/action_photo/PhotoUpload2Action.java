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
 * Servlet implementation class FileUploadAction
 */
@WebServlet("/upload2.do")
public class PhotoUpload2Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// /upload.do?title=사진&photo=a.jpg
		
		
		ServletContext application = request.getServletContext();
		
		String web_path = "/upload/";//URL경로
		String abs_path = application.getRealPath(web_path);//File경로
		//System.out.println(abs_path);
		int    max_size = 1024*1024*1024;// 1GB
		
		//화일업로드 처리 객체
		MultipartRequest mr = new MultipartRequest(
				                           request,   //위임
				                           abs_path,  //화일저장위
				                           max_size,  //화일최대용량
				                           "utf-8",   //수신인코딩
				                           new DefaultFileRenamePolicy()
				                           );
		//  DefaultFileRenamePolicy : 동일한 화일명이 존재하면 
		//                            업로드화일의 화일명을 변경해서 저장   
		
		//업로드 화일명 얻어오기
		String filename1 = "no_file";
		String filename2 = "no_file";
		//방법1
		
		File f = mr.getFile("photo");
		if(f != null) {
			filename1 = f.getName();//업로드된 화일명
		}
		
		
		File f1 = mr.getFile("photo2");
		if(f1 != null) {
			filename2 = f1.getName();//업로드된 화일명
		}
		

		
		//방법2
		//filename = mr.getOriginalFileName("photo");
		
		
		//화일과 같이 넘어온 parameter정보 구하기
		String p_subject =  mr.getParameter("p_subject");
		
		
		//request binding
		request.setAttribute("p_subject", p_subject);
		
		request.setAttribute("filename1", filename1);
		request.setAttribute("filename2", filename2);
		
		
		
		//Dispatcher
		String forward_page = "photo_list.jsp";
		request.getRequestDispatcher(forward_page).forward(request, response);

	}
}