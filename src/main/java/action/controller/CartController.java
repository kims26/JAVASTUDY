package action.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import annotation.RequestMapping;
import annotation.ResponseBody;
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
	
	
	//��ٱ��� ����
	@RequestMapping("/product/cart_delete.do")
	public String cart_delete(HttpServletRequest request, HttpServletResponse response) {

		// /product/cart_delete.do?c_idx=5
		
		int c_idx = Integer.parseInt(request.getParameter("c_idx"));

		
		//DB update
		int res = CartDao.getInstance().delete(c_idx);
		
		
		return "redirect:cart_list.do";
	}
	
	
	
	//��ٱ��� ���
	@RequestMapping(value="/product/cart_insert.do", 
			        produces="text/json;charset=utf-8;")
	@ResponseBody
	public String cart_insert(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// /product/cart_insert.do?p_idx=5&mem_idx=1
		
		//�α��λ������� ���´�
		HttpSession session = request.getSession();
		MemberVo user 		= (MemberVo) session.getAttribute("user");
		
		if(user==null) {
			
			response.sendRedirect("../member/login_form.do?reason=fail_session_timeout");
		
		}
		
		//parameter�ޱ�
		int p_idx 	= Integer.parseInt(request.getParameter("p_idx"));
		int mem_idx	= user.getMem_idx();


		//��ٱ��Ͽ� �̵̹�ϵȴ��� ���� üũ
		CartVo vo = new CartVo();
		vo.setP_idx(p_idx);
		vo.setMem_idx(mem_idx);
		
		CartVo resVo = CartDao.getInstance().selectOne(vo);
		
		String result = "success";
		
		//��ϵǾ����� ������=>���
		if(resVo==null) {
			
			int res = CartDao.getInstance().insert(vo);
			
			if(res==0)
				result = "fail";
		}else {
			//�̵̹�� �Ǿ� ������
			result = "exist";

		}
		
		//���=>JSON���� : {"result":"success"}
		JSONObject json = new JSONObject();
		json.put("result", result);
		
		
		return json.toString();
	}
	
	
	
	
	
	
	
	
}
