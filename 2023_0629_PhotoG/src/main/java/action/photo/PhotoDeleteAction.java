package action.photo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.PhotoDao;
import vo.PhotoVo;

/**
 * Servlet implementation class PhotoDeleteAction
 */
@WebServlet("/photo/delete.do")
public class PhotoDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		
	
		PhotoVo Vo=PhotoDao.getInstance().selectOne(p_idx);
		
		String web_path = "/upload/";
		String abs_path = request.getServletContext().getRealPath(web_path);
		
	
		
		
		File  f = new File("abs_path,vo.getP_filename");
		f.delete();
		
		
	
        //2.DB delete
		int res = PhotoDao.getInstance().delete(p_idx);
		
		//3.목록보기
		response.sendRedirect("list.do");
		
	

	}
}	