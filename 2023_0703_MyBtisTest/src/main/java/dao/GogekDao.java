package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.GogekVo;
import vo.SawonVo;

public class GogekDao {
	//single-ton : ��ü1���� ���� ����
	static GogekDao single = null;

	//SessionFactory�����ϴ� ��ü
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
	
	//��ü��ȸ
	public List<GogekVo> selectList(){
		
		List<GogekVo> list = null;
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
	public List<GogekVo> selectListDeptno(int gobun){
		
		List<GogekVo> list = null;
		//1.Mybatis�۾���ü ���´�
		SqlSession sqlSession = factory.openSession();

		//2.�۾�����                 namespace.mapper_id
		//     mapper_id + parameter�� �̿��ؼ� ��ȸ����� ArrayList�������ش� 
		list = sqlSession.selectList("sawon.sawon_list_deptno" , gobun);
		
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
	

