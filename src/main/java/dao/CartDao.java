package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.CartVo;

public class CartDao {

	SqlSessionFactory factory;
	//singleton pattern : 1���� ��ü�� �����ؼ� ��������
	static CartDao single = null;

	public static CartDao getInstance() {

		if (single == null)
			single = new CartDao();

		return single;
	}

	//�ܺο��� new CartDao() ���ϵ��� ����
	private CartDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
		
	}
	
	
	//��ٱ��� ��ȸ
	public List<CartVo> selectList(int mem_idx){
		
		List<CartVo>  list = null;
		
		//1.SqlSession���
		SqlSession sqlSession = factory.openSession();
		
		//2.�۾�����
		list = sqlSession.selectList("cart.cart_list", mem_idx);
		
		//3.�ݱ�
		sqlSession.close();
		
		return list;
		
	}
	
	//��ٱ��� �Ѿ� ���ϱ�
	public int selectTotalAmount(int mem_idx) {
		
		int total_amount = 0;
		
		//1.SqlSession���
		SqlSession sqlSession = factory.openSession();
		
		//2.�۾�����
		total_amount = sqlSession.selectOne("cart.cart_total_amount", mem_idx);
		
		//3.�ݱ�
		sqlSession.close();
		
		return total_amount;
		
	}

	public int update(CartVo vo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1.SqlSession���
		SqlSession sqlSession = factory.openSession(true);// auto commit
		
		//2.�۾�����
		res = sqlSession.update("cart.cart_update", vo);
		
		//3.�ݱ�
		sqlSession.close();		
		
		
		return res;
	}

	public int delete(int c_idx) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1.SqlSession���
		SqlSession sqlSession = factory.openSession(true);// auto commit
		
		//2.�۾�����
		res = sqlSession.delete("cart.cart_delete", c_idx);
		
		//3.�ݱ�
		sqlSession.close();		
		
		
		return res;
	}

	public CartVo selectOne(CartVo vo) {
		// TODO Auto-generated method stub
		CartVo resVo = null;
		//1.SqlSession���
		SqlSession sqlSession = factory.openSession();
		
		//2.�۾�����
		resVo = sqlSession.selectOne("cart.cart_one_exist", vo);
		
		//3.�ݱ�
		sqlSession.close();
		
		return resVo;
	}

	public int insert(CartVo vo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1.SqlSession���
		SqlSession sqlSession = factory.openSession(true);// auto commit
		
		//2.�۾�����
		res = sqlSession.insert("cart.cart_insert", vo);
		
		//3.�ݱ�
		sqlSession.close();		
		
		
		return res;
	}
	
	
	
	
	
}
