package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.SawonVo;

public class SawonDao {
	//single-ton : ��ü1���� ���� ����
	static SawonDao single = null;

	//SessionFactory�����ϴ� ��ü
	SqlSessionFactory factory;
	
	public SawonDao() {
		super();
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}

	public static SawonDao getInstance() {

		if (single == null)
			single = new SawonDao();

		return single;
	}
	
	//��ü��ȸ
	public List<SawonVo> selectList(){
		
		List<SawonVo> list = null;
		//1.Mybatis�۾���ü ���´�
		SqlSession sqlSession = factory.openSession();

		//2.�۾�����                 namespace.mapper_id
		//     mapper_id�� �̿��ؼ� ��ȸ����� ArrayList�������ش�
		list = sqlSession.selectList("sawon.sawon_list");
		
		//3.�������۾�
		sqlSession.close();
		
		return list;
	}
	
	//�μ�����ȸ
	public List<SawonVo> selectListDeptno(int deptno){
		
		List<SawonVo> list = null;
		//1.Mybatis�۾���ü ���´�
		SqlSession sqlSession = factory.openSession();

		//2.�۾�����                 namespace.mapper_id
		//     mapper_id + parameter�� �̿��ؼ� ��ȸ����� ArrayList�������ش� 
		list = sqlSession.selectList("sawon.sawon_list_deptno" , deptno);
		
		//3.�������۾�
		sqlSession.close();
		
		return list;
	}

	public List<SawonVo> selectListDeptnoSajob(SawonVo vo) {
		// TODO Auto-generated method stub
		List<SawonVo> list = null;
		//1.Mybatis�۾���ü ���´�
		SqlSession sqlSession = factory.openSession();

		//2.�۾�����                 namespace.mapper_id
		//     mapper_id + parameter(vo)�� �̿��ؼ� ��ȸ����� ArrayList�������ش� 
		list = sqlSession.selectList("sawon.sawon_list_deptno_sajob" , vo);
		
		//3.�������۾�
		sqlSession.close();
		
		return list;
	}

	public List<SawonVo> selectListSajob(String sajob) {
		// TODO Auto-generated method stub
		List<SawonVo> list = null;
		//1.Mybatis�۾���ü ���´�
		SqlSession sqlSession = factory.openSession();

		//2.�۾�����                 namespace.mapper_id
		//     mapper_id + parameter(vo)�� �̿��ؼ� ��ȸ����� ArrayList�������ش� 
		list = sqlSession.selectList("sawon.sawon_list_sajob" , sajob);
		
		//3.�������۾�
		sqlSession.close();
		
		return list;
	}
	}
	

