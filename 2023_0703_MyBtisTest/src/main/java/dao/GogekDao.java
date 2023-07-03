package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.GogekVo;
import vo.SawonVo;

public class GogekDao {
	//single-ton : 객체1개만 생성 서비스
	static GogekDao single = null;

	//SessionFactory생성하는 객체
	SqlSessionFactory factory;
	
	public GogekDao() {
		super();
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}

	public static GogekDao getInstance() {

		if (single == null)
			single = new GogekDao();

		return single;
	}
	
	//전체조회
	public List<GogekVo> selectList(){
		
		List<GogekVo> list = null;
		//1.Mybatis작업객체 얻어온다
		SqlSession sqlSession = factory.openSession();

		//2.작업수행                 namespace.mapper_id
		//     mapper_id를 이용해서 조회결과를 ArrayList포장해준다
		list = sqlSession.selectList("sawon.sawon_list");
		
		//3.마무리작업
		sqlSession.close();
		
		return list;
	}
	
	//부서별조회
	public List<GogekVo> selectListDeptno(int gobun){
		
		List<GogekVo> list = null;
		//1.Mybatis작업객체 얻어온다
		SqlSession sqlSession = factory.openSession();

		//2.작업수행                 namespace.mapper_id
		//     mapper_id + parameter를 이용해서 조회결과를 ArrayList포장해준다 
		list = sqlSession.selectList("sawon.sawon_list_deptno" , gobun);
		
		//3.마무리작업
		sqlSession.close();
		
		return list;
	}

	public List<SawonVo> selectListDeptnoSajob(SawonVo vo) {
		// TODO Auto-generated method stub
		List<SawonVo> list = null;
		//1.Mybatis작업객체 얻어온다
		SqlSession sqlSession = factory.openSession();

		//2.작업수행                 namespace.mapper_id
		//     mapper_id + parameter(vo)를 이용해서 조회결과를 ArrayList포장해준다 
		list = sqlSession.selectList("sawon.sawon_list_deptno_sajob" , vo);
		
		//3.마무리작업
		sqlSession.close();
		
		return list;
	}

	public List<SawonVo> selectListSajob(String sajob) {
		// TODO Auto-generated method stub
		List<SawonVo> list = null;
		//1.Mybatis작업객체 얻어온다
		SqlSession sqlSession = factory.openSession();

		//2.작업수행                 namespace.mapper_id
		//     mapper_id + parameter(vo)를 이용해서 조회결과를 ArrayList포장해준다 
		list = sqlSession.selectList("sawon.sawon_list_sajob" , sajob);
		
		//3.마무리작업
		sqlSession.close();
		
		return list;
	}
	}
	

