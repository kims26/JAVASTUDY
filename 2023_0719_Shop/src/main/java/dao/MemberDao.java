package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.MemberVo;

public class MemberDao {

	
	SqlSessionFactory factory;
	
	//singleton pattern : 1개의 객체만 생성해서 서비스하자
	static MemberDao single = null;

	public static MemberDao getInstance() {

		if (single == null)
			single = new MemberDao();

		return single;
	}

	//외부에서 new MemberDao() 못하도록 설정
	private MemberDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//회원목록조회
	//전체조회           DB select-> Java ArrayList포장
	public List<MemberVo> selectList() {

		List<MemberVo> list = null;

		SqlSession sqlSession = factory.openSession();
		
		list = sqlSession.selectList("member.member_list");
		
		sqlSession.close();
		

		return list;
	}
	
	
	//mem_idx에 해당되는 회원정보 1건
	public MemberVo selectOne(int mem_idx) {

		MemberVo vo = null;

		SqlSession sqlSession = factory.openSession();
		
		vo = sqlSession.selectOne("member.member_one_idx", mem_idx);
		
		sqlSession.close();

		return vo;
	}
	
	//mem_id에 해당되는 회원정보 1건
	public MemberVo selectOne(String mem_id) {

		MemberVo vo = null;

		SqlSession sqlSession = factory.openSession();
		
		vo = sqlSession.selectOne("member.member_one_id", mem_id);
		
		sqlSession.close();

		return vo;
	}
	
	//insert
	public int insert(MemberVo vo) {

		int res = 0;//처리된 결과행수

		SqlSession sqlSession = factory.openSession(true);
		
		res = sqlSession.insert("member.member_insert", vo);
		
		sqlSession.close();

		return res;
	}

	//수정
	public int update(MemberVo vo) {

		int res = 0;//처리된 결과행수

		SqlSession sqlSession = factory.openSession(true);
		
		res = sqlSession.update("member.member_update", vo);
		
		sqlSession.close();

		return res;
	}

	public int delete(int mem_idx) {

		int res = 0;//처리된 결과행수

		SqlSession sqlSession = factory.openSession(true);
		
		res = sqlSession.delete("member.member_delete", mem_idx);
		
		sqlSession.close();

		return res;
	}

	
	
	
	
}
