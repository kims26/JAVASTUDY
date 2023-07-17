package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.VisitVo;

//            
public class VisitDao {

	//Mybatis��ü����
	SqlSessionFactory factory;
	
	//singleton pattern : 1���� ��ü�� �����ؼ� ��������
	static VisitDao single = null;

	public static VisitDao getInstance() {

		if (single == null)
			single = new VisitDao();

		return single;
	}

	//�ܺο��� new VisitDao() ���ϵ��� ����
	private VisitDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	
	//��ü��ȸ           DB select-> Java ArrayList����
	public List<VisitVo> selectList() {

		List<VisitVo> list = null;
		
		//1.�۾���ü ������
		SqlSession sqlSession = factory.openSession();
		
		//2.�۾�����
		list = sqlSession.selectList("visit.visit_list");
		
		//3.�ݱ�
		sqlSession.close();
		

		return list;
	}
	
	public List<VisitVo> selectList(Map<String, String> map) {
		// TODO Auto-generated method stub
		List<VisitVo> list = null;
		
		//1.�۾���ü ������
		SqlSession sqlSession = factory.openSession();
		
		//2.�۾�����
		list = sqlSession.selectList("visit.visit_list_condition" , map);
		
		//3.�ݱ�
		sqlSession.close();
		

		return list;
	}
	
	
	//�Խù� 1�� ��������
	public VisitVo selectOne(int idx) {

		VisitVo vo = null;
		
		//1.�۾���ü ������
		SqlSession sqlSession = factory.openSession();
		
		//2.�۾�����                 mapper_id        parameter
		vo = sqlSession.selectOne("visit.visit_one" , idx);
		
		//3.�ݱ�
		sqlSession.close();
		

		return vo;
	}
	
	
	

	
	//�߰�
	public int insert(VisitVo vo) {

		//Mybatis���� DML��ɽ� ���ǻ���
		// Transaction����: openSession()���� Transaction������ 
		
		
		int res = 0;//ó���� ������

		//1.�۾���ü ������
		SqlSession sqlSession = factory.openSession();
		
		//2.�۾�����                 mapper_id        parameter
		res = sqlSession.insert("visit.visit_insert" , vo);
		
		//2-1.Ʈ������� ������ �۾�
		sqlSession.commit();//DB����  cf) ���: sqlSession.rollback();
		
		//3.�ݱ�
		sqlSession.close();
		

		return res;
	}

	public int delete(int idx) {

		int res = 0;//ó���� ������

		//1.�۾���ü ������   openSession(boolean) : true<- auto commit
		SqlSession sqlSession = factory.openSession(true);
		
		//2.�۾�����                 mapper_id        parameter
		res = sqlSession.delete("visit.visit_delete" , idx);
		
		
		//3.�ݱ�
		sqlSession.close();
		

		return res;
	}

	public int update(VisitVo vo) {

		int res = 0;//ó���� ������

		//1.�۾���ü ������   openSession(boolean) : true<- auto commit
		SqlSession sqlSession = factory.openSession(true);
		
		//2.�۾�����                 mapper_id        parameter
		res = sqlSession.update("visit.visit_update" , vo);
		
		//3.�ݱ�
		sqlSession.close();

		return res;
	}

	

	
	
	
}
