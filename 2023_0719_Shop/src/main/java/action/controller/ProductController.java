package action.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import annotation.RequestMapping;
import dao.PhotoDao;
import dao.ProductDao;
import vo.MemberVo;
import vo.PhotoVo;
import vo.ProductVo;

public class ProductController {

	@RequestMapping("/product/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response) {

		// /product/list.do
		// /product/list.do?category=com001
		// /product/list.do?category=ele002
		// /product/list.do?category=sp003
		
		String category = request.getParameter("category");
		if(category==null)
			category = "com001";
		
		List<ProductVo> list = ProductDao.getInstance().selectList(category);
		
		//request binding
		request.setAttribute("list", list);
		
		return "product_list.jsp";
	}
	
	//상품상세보기
	@RequestMapping("/product/view.do")
	public String view(HttpServletRequest request, HttpServletResponse response) {

		// /product/view.do?p_idx=1
		//1.상품번호 받기
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		
		//2.p_idx번호에 해당되는 상품정보 얻어오기
		ProductVo vo = ProductDao.getInstance().selectOne(p_idx);
		
		//3.request bindig
		request.setAttribute("vo", vo);
		
		
		return "product_view.jsp";
	}
	
	//상품등록창 띄우기
	@RequestMapping("/product/insert_form.do")
	public String insert_form(HttpServletRequest request, HttpServletResponse response) {

		return "product_insert_form.jsp";
	}
	
	//상품등록
	@RequestMapping("/product/insert.do")
	public String insert(HttpServletRequest request, HttpServletResponse response) throws IOException {

		//로그인상태정보 얻어온다
		HttpSession session = request.getSession();
		MemberVo user 		= (MemberVo) session.getAttribute("user");
		
		if(user==null) {
			
			response.sendRedirect("../member/login_form.do?reason=fail_session_timeout");
		
		}
		
		//상품등록
		//1.저장경로구하기 : 웹경로->절대경로 구하기
		String web_path = "/images/";
		String abs_path = request.getServletContext().getRealPath(web_path);
		
		//System.out.println(abs_path);
		
		//2.최대저장 용량설정
		int max_size = 1024*1024*1024;// 1GB
		
		//3.MultipartRequest
		MultipartRequest mr = new MultipartRequest(
				                                   request,    //request위임
				                                   abs_path,   //화일저장경로 
				                                   max_size,   //저장크기
				                                   "utf-8",
				                                   new DefaultFileRenamePolicy());
		
		
		// /photo/insert.do?mem_idx=2&p_subject=제목&p_content=내용&photo=a.jpg
		//4.저장화일명구하기
		String p_image_s="no_file";
		String p_image_l="no_file";
		File  f = mr.getFile("photo_s");
		if(f != null) {
			p_image_s = f.getName();//실제업로드된 화일명
		}
		
		File  f1 = mr.getFile("photo_l");
		if(f1 != null) {
			p_image_l = f1.getName();//실제업로드된 화일명
		}
		
		
		//5.parameter구하기(화일업로드시 파라메터 처리는 mr로 해야된다)
		String category 	= mr.getParameter("category");
		String p_num 		= mr.getParameter("p_num");
		String p_name 		= mr.getParameter("p_name");
		String p_company 	= mr.getParameter("p_company");
		String p_content 	= mr.getParameter("p_content");
		
		int p_price 		= Integer.parseInt(mr.getParameter("p_price"));
		int p_saleprice 	= Integer.parseInt(mr.getParameter("p_saleprice"));
		
				
	    //ProductVO포장
		
		//DB Insert
		
		
		
		return "redirect:list.do?category=" + category;
	}
	
	
	
	
	
	
	
}
