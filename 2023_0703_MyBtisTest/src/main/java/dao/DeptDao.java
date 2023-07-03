package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.DeptVo;

public class DeptDao {

	//Mybatis객체
	SqlSessionFactory factory;
	
	//singleton pattern : 1개의 객체만 생성해서 서비스하자
	static DeptDao single = null;

	public static DeptDao getInstance() {

		if (single == null)
			single = new DeptDao();

		return single;
	}

	//외부에서 new DeptDao() 못하도록 설정
	private DeptDao() {
		// TODO Auto-generated constructor stub
		
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
		
	}
	
	
	public List<DeptVo>  selectList(){
		
		List<DeptVo> list = null;
		
		//1.작업객체 얻어오기
		SqlSession sqlSession = factory.openSession();
		
		//2.작업수행
		list = sqlSession.selectList("dept.dept_list");
		
		//3.닫기
		sqlSession.close();//내부적으로 conn.close()
		
		
		return list;
	}
	
	
	
	
	
}
