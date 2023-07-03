package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.DeptVo;

public class DeptDao {

	//Mybatis��ü
	SqlSessionFactory factory;
	
	//singleton pattern : 1���� ��ü�� �����ؼ� ��������
	static DeptDao single = null;

	public static DeptDao getInstance() {

		if (single == null)
			single = new DeptDao();

		return single;
	}

	//�ܺο��� new DeptDao() ���ϵ��� ����
	private DeptDao() {
		// TODO Auto-generated constructor stub
		
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
		
	}
	
	
	public List<DeptVo>  selectList(){
		
		List<DeptVo> list = null;
		
		//1.�۾���ü ������
		SqlSession sqlSession = factory.openSession();
		
		//2.�۾�����
		list = sqlSession.selectList("dept.dept_list");
		
		//3.�ݱ�
		sqlSession.close();//���������� conn.close()
		
		
		return list;
	}
	
	
	
	
	
}
