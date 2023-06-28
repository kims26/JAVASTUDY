package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.DBService;
import vo.MemberVo;

public class MemberDao {

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
	}
	
	//회원목록조회
	//전체조회           DB select-> Java ArrayList포장
	public List<MemberVo> selectList() {

		List<MemberVo> list = new ArrayList<MemberVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from member order by mem_idx";

		try {

			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2.SQL명령처리객체 얻어오기
			pstmt = conn.prepareStatement(sql);

			//3.결과행처리객체 얻어오기
			rs = pstmt.executeQuery();

			while (rs.next()) {

				MemberVo vo = new MemberVo();

				//vo->rs에서 읽어온값 넣기
				vo.setMem_idx(rs.getInt("mem_idx"));
				vo.setMem_name(rs.getString("mem_name"));
				vo.setMem_id(rs.getString("mem_id"));
				vo.setMem_pwd(rs.getString("mem_pwd"));
				vo.setMem_zipcode(rs.getString("mem_zipcode"));
				vo.setMem_addr(rs.getString("mem_addr"));
				vo.setMem_ip(rs.getString("mem_ip"));
				vo.setMem_regdate(rs.getString("mem_regdate"));
				vo.setMem_grade(rs.getString("mem_grade"));
				
				//ArrayList에 추가
				list.add(vo);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			//마무리 코드작성
			try {

				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}
	
	
	//mem_idx에 해당되는 회원정보 1건
	public MemberVo selectOne(int mem_idx) {

		MemberVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from member where mem_idx=?";

		try {

			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2.SQL명령처리객체 얻어오기
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter index
			pstmt.setInt(1, mem_idx);

			//4.결과행처리객체 얻어오기
			rs = pstmt.executeQuery();

			if (rs.next()) {

				vo = new MemberVo();

				//vo->rs에서 읽어온값 넣기
				vo.setMem_idx(rs.getInt("mem_idx"));
				vo.setMem_name(rs.getString("mem_name"));
				vo.setMem_id(rs.getString("mem_id"));
				vo.setMem_pwd(rs.getString("mem_pwd"));
				vo.setMem_zipcode(rs.getString("mem_zipcode"));
				vo.setMem_addr(rs.getString("mem_addr"));
				vo.setMem_ip(rs.getString("mem_ip"));
				vo.setMem_regdate(rs.getString("mem_regdate"));
				vo.setMem_grade(rs.getString("mem_grade"));

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			//마무리 코드작성
			try {

				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return vo;
	}
	
	//mem_id에 해당되는 회원정보 1건
	public MemberVo selectOne(String mem_id) {

		MemberVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from member where mem_id=?";

		try {

			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2.SQL명령처리객체 얻어오기
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter index
		    pstmt.setString(1, mem_id);

			//4.결과행처리객체 얻어오기
			rs = pstmt.executeQuery();

			if (rs.next()) {

				vo = new MemberVo();

				//vo->rs에서 읽어온값 넣기
				vo.setMem_idx(rs.getInt("mem_idx"));
				vo.setMem_name(rs.getString("mem_name"));
				vo.setMem_id(rs.getString("mem_id"));
				vo.setMem_pwd(rs.getString("mem_pwd"));
				vo.setMem_zipcode(rs.getString("mem_zipcode"));
				vo.setMem_addr(rs.getString("mem_addr"));
				vo.setMem_ip(rs.getString("mem_ip"));
				vo.setMem_regdate(rs.getString("mem_regdate"));
				vo.setMem_grade(rs.getString("mem_grade"));

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			//마무리 코드작성
			try {

				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return vo;
	}
	
	//insert
	public int insert(MemberVo vo) {

		int res = 0;//처리된 결과행수

		Connection conn = null;
		PreparedStatement pstmt = null;

		//                                                                 1 2 3 4 5 6
		String sql = "insert into member values(seq_member_mem_idx.nextVal,?,?,?,?,?,?,sysdate,'일반')";

		try {
			//1.Connection 얻기
			conn = DBService.getInstance().getConnection();

			//2.pstmt 얻기
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter index채우기
			pstmt.setString(1, vo.getMem_name());
			pstmt.setString(2, vo.getMem_id());
			pstmt.setString(3, vo.getMem_pwd());
			pstmt.setString(4, vo.getMem_zipcode());
			pstmt.setString(5, vo.getMem_addr());
			pstmt.setString(6, vo.getMem_ip());

			//4.DB insert
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return res;
	}

	//수정
	public int update(MemberVo vo) {

		int res = 0;//처리된 결과행수

		Connection conn = null;
		PreparedStatement pstmt = null;
        //                                       1        2         3                                
		String sql = "update member set mem_name=?,mem_id=?,mem_pwd=?,"+
        //                        4          5        6           7   
                     "mem_zipcode=?,mem_addr=?,mem_ip=?,mem_grade=?,"+
        //                                              8
				     "mem_regdate=sysdate where mem_idx=?";

		try {
			//1.Connection 얻기
			conn = DBService.getInstance().getConnection();

			//2.pstmt 얻기
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter index채우기
            pstmt.setString(1, vo.getMem_name());
            pstmt.setString(2, vo.getMem_id());
            pstmt.setString(3, vo.getMem_pwd());
            pstmt.setString(4, vo.getMem_zipcode());
            pstmt.setString(5, vo.getMem_addr());
            pstmt.setString(6, vo.getMem_ip());
            pstmt.setString(7, vo.getMem_grade());
            
            pstmt.setInt(8, vo.getMem_idx());
            
			//4.DB insert
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return res;
	}

	public int delete(int mem_idx) {

		int res = 0;//처리된 결과행수

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "delete from member where mem_idx=?";

		try {
			//1.Connection 얻기
			conn = DBService.getInstance().getConnection();

			//2.pstmt 얻기
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter index채우기
            pstmt.setInt(1, mem_idx);
			//4.DB insert
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return res;
	}

	
	
	
	
}
