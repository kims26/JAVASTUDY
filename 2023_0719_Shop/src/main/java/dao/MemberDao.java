package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.MemberVo;

public class MemberDao {

	
	SqlSessionFactory factory;
	
	//singleton pattern : 1���� ��ü�� �����ؼ� ��������
	static MemberDao single = null;

	public static MemberDao getInstance() {

		if (single == null)
			single = new MemberDao();

		return single;
	}

	//�ܺο��� new MemberDao() ���ϵ��� ����
	private MemberDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//ȸ�������ȸ
	//��ü��ȸ           DB select-> Java ArrayList����
	public List<MemberVo> selectList() {

		List<MemberVo> list = null;

		SqlSession sqlSession = factory.openSession();
		
		list = sqlSession.selectList("member.member_list");
		
		sqlSession.close();
		

		return list;
	}
	
	
	//mem_idx�� �ش�Ǵ� ȸ������ 1��
	public MemberVo selectOne(int mem_idx) {

		MemberVo vo = null;

		SqlSession sqlSession = factory.openSession();
		
		vo = sqlSession.selectOne("member.member_one_idx", mem_idx);
		
		sqlSession.close();

		return vo;
	}
	
	//mem_id�� �ش�Ǵ� ȸ������ 1��
	public MemberVo selectOne(String mem_id) {

		MemberVo vo = null;

		SqlSession sqlSession = factory.openSession();
		
		vo = sqlSession.selectOne("member.member_one_id", mem_id);
		
		sqlSession.close();

		return vo;
	}
	
	//insert
	public int insert(MemberVo vo) {

		int res = 0;//ó���� ������

		SqlSession sqlSession = factory.openSession(true);
		
		res = sqlSession.insert("member.member_insert", vo);
		
		sqlSession.close();

		return res;
	}

	//����
	public int update(MemberVo vo) {

		int res = 0;//ó���� ������

		SqlSession sqlSession = factory.openSession(true);
		
		res = sqlSession.update("member.member_update", vo);
		
		sqlSession.close();

		return res;
	}

	public int delete(int mem_idx) {

		int res = 0;//ó���� ������

		SqlSession sqlSession = factory.openSession(true);
		
		res = sqlSession.delete("member.member_delete", mem_idx);
		
		sqlSession.close();

		return res;
	}

	
	
	
	
}
