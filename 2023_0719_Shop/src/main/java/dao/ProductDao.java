package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.ProductVo;

public class ProductDao {

	SqlSessionFactory factory;
	
	//singleton pattern : 1개의 객체만 생성해서 서비스하자
	static ProductDao single = null;

	public static ProductDao getInstance() {

		if (single == null)
			single = new ProductDao();

		return single;
	}

	//외부에서 new ProductDao() 못하도록 설정
	private ProductDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	
	//Category별 조회
	public List<ProductVo> selectList(String category){
		
		List<ProductVo> list = null;
		//1.SqlSession얻어오기
		SqlSession sqlSession = factory.openSession();
		
		//2.작업수행                  namespace.mapper_id
		list = sqlSession.selectList("product.product_list", category);
		
		//3.닫기
		sqlSession.close();
		
		return list;
	}

	public ProductVo selectOne(int p_idx) {
		// TODO Auto-generated method stub
		ProductVo vo = null;
		
		//1.SqlSession얻어오기
		SqlSession sqlSession = factory.openSession();
		
		//2.작업수행                  namespace.mapper_id
		vo = sqlSession.selectOne("product.product_one", p_idx);
		
		//3.닫기
		sqlSession.close();
		
		
		return vo;
	}
	
	
}
