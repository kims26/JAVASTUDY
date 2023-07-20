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

		//로그인상태정보 얻어온다
		HttpSession session = request.getSession();
		MemberVo user 		= (MemberVo) session.getAttribute("user");
		
		if(user==null) {
			
			response.sendRedirect("../member/login_form.do?reason=fail_session_timeout");
		
		}
		
		//로그인된 유저의 회원번호
		int mem_idx = user.getMem_idx();
		
		//장바구니 목록구하기
		List<CartVo> list	= CartDao.getInstance().selectList(mem_idx);
		
		//장바구니 총액구하기
		int total_amount	= CartDao.getInstance().selectTotalAmount(mem_idx);
		
		//request binding
		request.setAttribute("list", list);
		request.setAttribute("total_amount", total_amount);
	
		return "cart_list.jsp";
	}
	
	
	//장바구니 수량 수정
	@RequestMapping("/product/cart_update.do")
	public String cart_update(HttpServletRequest request, HttpServletResponse response) {

		// /product/cart_update.do?c_idx=5&c_cnt=10
		
		int c_idx = Integer.parseInt(request.getParameter("c_idx"));
		int c_cnt = Integer.parseInt(request.getParameter("c_cnt"));
		
		//수정할 정보 포장
		CartVo vo = new CartVo();
		vo.setC_idx(c_idx);
		vo.setC_cnt(c_cnt);
		
		//DB update
		int res = CartDao.getInstance().update(vo);
		
		
		return "redirect:cart_list.do";
	}
	
	
	
	
	
	
	
	
	
	
}
