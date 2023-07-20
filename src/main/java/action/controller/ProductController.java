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
import dao.ProductDao;
import vo.MemberVo;
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
	
	//��ǰ�󼼺���
	@RequestMapping("/product/view.do")
	public String view(HttpServletRequest request, HttpServletResponse response) {

		// /product/view.do?p_idx=1
		//1.��ǰ��ȣ �ޱ�
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		
		//2.p_idx��ȣ�� �ش�Ǵ� ��ǰ���� ������
		ProductVo vo = ProductDao.getInstance().selectOne(p_idx);
		
		//3.request bindig
		request.setAttribute("vo", vo);
		
		
		return "product_view.jsp";
	}
	
	//��ǰ���â ����
	@RequestMapping("/product/insert_form.do")
	public String insert_form(HttpServletRequest request, HttpServletResponse response) {

		return "product_insert_form.jsp";
	}
	
	//��ǰ���
	@RequestMapping("/product/insert.do")
	public String insert(HttpServletRequest request, HttpServletResponse response) throws IOException {

		//�α��λ������� ���´�
		HttpSession session = request.getSession();
		MemberVo user 		= (MemberVo) session.getAttribute("user");
		
		if(user==null) {
			
			response.sendRedirect("../member/login_form.do?reason=fail_session_timeout");
		
		}
		
		//��ǰ���
		//1.�����α��ϱ� : �����->������ ���ϱ�
		String web_path = "/images/";
		String abs_path = request.getServletContext().getRealPath(web_path);
		
		//System.out.println(abs_path);
		
		//2.�ִ����� �뷮����
		int max_size = 1024*1024*1024;// 1GB
		
		//3.MultipartRequest
		MultipartRequest mr = new MultipartRequest(
				                                   request,    //request����
				                                   abs_path,   //ȭ�������� 
				                                   max_size,   //����ũ��
				                                   "utf-8",
				                                   new DefaultFileRenamePolicy());
		
		
		// /photo/insert.do?mem_idx=2&p_subject=����&p_content=����&photo_s=a.jpg&photo_l=b.jpg
		//4.����ȭ�ϸ��ϱ�
		String p_image_s="no_file";
		String p_image_l="no_file";
		File  f = mr.getFile("photo_s");
		if(f != null) {
			p_image_s = f.getName();//�������ε�� ȭ�ϸ�
		}
		
		File  f1 = mr.getFile("photo_l");
		if(f1 != null) {
			p_image_l = f1.getName();//�������ε�� ȭ�ϸ�
		}
		
		
		//5.parameter���ϱ�(ȭ�Ͼ��ε�� �Ķ���� ó���� mr�� �ؾߵȴ�)
		String category 	= mr.getParameter("category");
		String p_num 		= mr.getParameter("p_num");
		String p_name 		= mr.getParameter("p_name");
		String p_company 	= mr.getParameter("p_company");
		String p_content 	= mr.getParameter("p_content").replaceAll("\n", "<br>");
		
		int p_price 		= Integer.parseInt(mr.getParameter("p_price"));
		int p_saleprice 	= Integer.parseInt(mr.getParameter("p_saleprice"));
		
				
	    //ProductVO����
		ProductVo vo = new ProductVo(category, p_num, p_name, p_company, p_price, p_saleprice, p_image_s, p_image_l, p_content);
		
		//DB Insert
		int res = ProductDao.getInstance().insert(vo);
		
		
		return "redirect:list.do?category=" + category;
	}
	
	
	
	
	
	
	
}
