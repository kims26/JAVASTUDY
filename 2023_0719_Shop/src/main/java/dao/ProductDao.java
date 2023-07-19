package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.ProductVo;

public class ProductDao {

	SqlSessionFactory factory;
	
	//singleton pattern : 1���� ��ü�� �����ؼ� ��������
	static ProductDao single = null;

	public static ProductDao getInstance() {

		if (single == null)
			single = new ProductDao();

		return single;
	}

	//�ܺο��� new ProductDao() ���ϵ��� ����
	private ProductDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	
	//Category�� ��ȸ
	public List<ProductVo> selectList(String category){
		
		List<ProductVo> list = null;
		//1.SqlSession������
		SqlSession sqlSession = factory.openSession();
		
		//2.�۾�����                  namespace.mapper_id
		list = sqlSession.selectList("product.product_list", category);
		
		//3.�ݱ�
		sqlSession.close();
		
		return list;
	}

	public ProductVo selectOne(int p_idx) {
		// TODO Auto-generated method stub
		ProductVo vo = null;
		
		//1.SqlSession������
		SqlSession sqlSession = factory.openSession();
		
		//2.�۾�����                  namespace.mapper_id
		vo = sqlSession.selectOne("product.product_one", p_idx);
		
		//3.�ݱ�
		sqlSession.close();
		
		
		return vo;
	}
	
	
}
