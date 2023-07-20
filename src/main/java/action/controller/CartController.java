package action.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import annotation.RequestMapping;
import dao.CartDao;
import vo.CartVo;
import vo.MemberVo;

public class CartController {

	@RequestMapping("/product/cart_list.do")
	public String cart_list(HttpServletRequest request, HttpServletResponse response) throws IOException {

		//�α��λ������� ���´�
		HttpSession session = request.getSession();
		MemberVo user 		= (MemberVo) session.getAttribute("user");
		
		if(user==null) {
			
			response.sendRedirect("../member/login_form.do?reason=fail_session_timeout");
		
		}
		
		//�α��ε� ������ ȸ����ȣ
		int mem_idx = user.getMem_idx();
		
		//��ٱ��� ��ϱ��ϱ�
		List<CartVo> list	= CartDao.getInstance().selectList(mem_idx);
		
		//��ٱ��� �Ѿױ��ϱ�
		int total_amount	= CartDao.getInstance().selectTotalAmount(mem_idx);
		
		//request binding
		request.setAttribute("list", list);
		request.setAttribute("total_amount", total_amount);
	
		return "cart_list.jsp";
	}
	
	
	//��ٱ��� ���� ����
	@RequestMapping("/product/cart_update.do")
	public String cart_update(HttpServletRequest request, HttpServletResponse response) {

		// /product/cart_update.do?c_idx=5&c_cnt=10
		
		int c_idx = Integer.parseInt(request.getParameter("c_idx"));
		int c_cnt = Integer.parseInt(request.getParameter("c_cnt"));
		
		//������ ���� ����
		CartVo vo = new CartVo();
		vo.setC_idx(c_idx);
		vo.setC_cnt(c_cnt);
		
		//DB update
		int res = CartDao.getInstance().update(vo);
		
		
		return "redirect:cart_list.do";
	}
	
	
	
	
	
	
	
	
	
	
}
