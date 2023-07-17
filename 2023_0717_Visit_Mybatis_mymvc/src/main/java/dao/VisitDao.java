package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.VisitVo;

//            
public class VisitDao {

	//Mybatis객체정보
	SqlSessionFactory factory;
	
	//singleton pattern : 1개의 객체만 생성해서 서비스하자
	static VisitDao single = null;

	public static VisitDao getInstance() {

		if (single == null)
			single = new VisitDao();

		return single;
	}

	//외부에서 new VisitDao() 못하도록 설정
	private VisitDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	
	//전체조회           DB select-> Java ArrayList포장
	public List<VisitVo> selectList() {

		List<VisitVo> list = null;
		
		//1.작업객체 얻어오기
		SqlSession sqlSession = factory.openSession();
		
		//2.작업수행
		list = sqlSession.selectList("visit.visit_list");
		
		//3.닫기
		sqlSession.close();
		

		return list;
	}
	
	public List<VisitVo> selectList(Map<String, String> map) {
		// TODO Auto-generated method stub
		List<VisitVo> list = null;
		
		//1.작업객체 얻어오기
		SqlSession sqlSession = factory.openSession();
		
		//2.작업수행
		list = sqlSession.selectList("visit.visit_list_condition" , map);
		
		//3.닫기
		sqlSession.close();
		

		return list;
	}
	
	
	//게시물 1건 가져오기
	public VisitVo selectOne(int idx) {

		VisitVo vo = null;
		
		//1.작업객체 얻어오기
		SqlSession sqlSession = factory.openSession();
		
		//2.작업수행                 mapper_id        parameter
		vo = sqlSession.selectOne("visit.visit_one" , idx);
		
		//3.닫기
		sqlSession.close();
		

		return vo;
	}
	
	
	

	
	//추가
	public int insert(VisitVo vo) {

		//Mybatis에서 DML명령시 유의사항
		// Transaction주의: openSession()에서 Transaction설정함 
		
		
		int res = 0;//처리된 결과행수

		//1.작업객체 얻어오기
		SqlSession sqlSession = factory.openSession();
		
		//2.작업수행                 mapper_id        parameter
		res = sqlSession.insert("visit.visit_insert" , vo);
		
		//2-1.트랜잭션의 마무리 작업
		sqlSession.commit();//DB적용  cf) 취소: sqlSession.rollback();
		
		//3.닫기
		sqlSession.close();
		

		return res;
	}

	public int delete(int idx) {

		int res = 0;//처리된 결과행수

		//1.작업객체 얻어오기   openSession(boolean) : true<- auto commit
		SqlSession sqlSession = factory.openSession(true);
		
		//2.작업수행                 mapper_id        parameter
		res = sqlSession.delete("visit.visit_delete" , idx);
		
		
		//3.닫기
		sqlSession.close();
		

		return res;
	}

	public int update(VisitVo vo) {

		int res = 0;//처리된 결과행수

		//1.작업객체 얻어오기   openSession(boolean) : true<- auto commit
		SqlSession sqlSession = factory.openSession(true);
		
		//2.작업수행                 mapper_id        parameter
		res = sqlSession.update("visit.visit_update" , vo);
		
		//3.닫기
		sqlSession.close();

		return res;
	}

	

	
	
	
}
