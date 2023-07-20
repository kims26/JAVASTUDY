package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.CartVo;

public class CartDao {

	SqlSessionFactory factory;
	//singleton pattern : 1개의 객체만 생성해서 서비스하자
	static CartDao single = null;

	public static CartDao getInstance() {

		if (single == null)
			single = new CartDao();

		return single;
	}

	//외부에서 new CartDao() 못하도록 설정
	private CartDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
		
	}
	
	
	//장바구니 조회
	public List<CartVo> selectList(int mem_idx){
		
		List<CartVo>  list = null;
		
		//1.SqlSession얻기
		SqlSession sqlSession = factory.openSession();
		
		//2.작업수행
		list = sqlSession.selectList("cart.cart_list", mem_idx);
		
		//3.닫기
		sqlSession.close();
		
		return list;
		
	}
	
	//장바구니 총액 구하기
	public int selectTotalAmount(int mem_idx) {
		
		int total_amount = 0;
		
		//1.SqlSession얻기
		SqlSession sqlSession = factory.openSession();
		
		//2.작업수행
		total_amount = sqlSession.selectOne("cart.cart_total_amount", mem_idx);
		
		//3.닫기
		sqlSession.close();
		
		return total_amount;
		
	}

	public int update(CartVo vo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1.SqlSession얻기
		SqlSession sqlSession = factory.openSession(true);// auto commit
		
		//2.작업수행
		res = sqlSession.update("cart.cart_update", vo);
		
		//3.닫기
		sqlSession.close();		
		
		
		return res;
	}
	
	
	
	
	
}
