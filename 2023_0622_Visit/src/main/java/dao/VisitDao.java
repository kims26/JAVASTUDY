package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.DBService;
import vo.VisitVo;

public class VisitDao {

	//singleton pattern : 1개의 객체만 생성해서 서비스하자
	static VisitDao single = null;

	public static VisitDao getInstance() {

		if (single == null)
			single = new VisitDao();

		return single;
	}

	//외부에서 new VisitTBDao() 못하도록 설정
	private VisitDao() {
		// TODO Auto-generated constructor stub
	}
	
	public List<VisitVo> selectList() {

		List<VisitVo> list = new ArrayList<VisitVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from visit order by idx desc";

		try {

			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2.SQL명령처리객체 얻어오기
			pstmt = conn.prepareStatement(sql);

			//3.결과행처리객체 얻어오기
			rs = pstmt.executeQuery();

			while (rs.next()) {

				VisitVo vo = new VisitVo();

				//vo->rs에서 읽어온값 넣기
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setPwd(rs.getString("pwd"));
				vo.setIp(rs.getString("ip"));
				vo.setRegdate(rs.getString("regdate"));
				
				
							

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
	
	
	
	//게시물 한건 가져오기
	 public VisitVo selectOne(int idx) {
	

		VisitVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from visit where idx=?";

		try {

			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2.SQL명령처리객체 얻어오기
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter index
            pstmt.setInt(1, idx);
			//4.결과행처리객체 얻어오기
			rs = pstmt.executeQuery();

			if (rs.next()) {

				vo = new VisitVo();

				//vo->rs에서 읽어온값 넣기
				
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setPwd(rs.getString("pwd"));
				vo.setIp(rs.getString("ip"));
				vo.setRegdate(rs.getString("regdate"));

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
	 
	 
public int insert(VisitVo vo) {
		
		int res = 0;//처리된 결과행수
		
		Connection 			conn	= null;
		PreparedStatement 	pstmt	= null;

		
		String				sql =   //                             1 2 3 4 <- pstmt parameter index  
				"insert into visit values( seq_visit_idx.nextVal,?,?,?,?,sysdate)";
		
		try {
			//1.Connection 얻기
			conn = DBService.getInstance().getConnection();
			
			//2.pstmt 얻기
			pstmt = conn.prepareStatement(sql);
			
			//3.pstmt parameter index채우기
	
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getPwd());
			pstmt.setString(4, vo.getIp());
			//pstmt.setString(5, vo.getRegdate());
			
		
			//4.DB insert
			res = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null)  conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return res;
	}

public int update(VisitVo vo) {

	int res = 0;//처리된 결과행수

	Connection conn = null;
	PreparedStatement pstmt = null;
    //                                   1     2     3     4           5
	String sql = "update visit set name=?,content=?,pwd=?,ip=? where idx=?";

	try {
		//1.Connection 얻기
		conn = DBService.getInstance().getConnection();

		//2.pstmt 얻기
		pstmt = conn.prepareStatement(sql);

		//3.pstmt parameter index채우기
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, vo.getPwd());
		pstmt.setString(4, vo.getIp());
		//pstmt.setString(5, vo.getRegdate());
		pstmt.setInt(5, vo.getIdx());
	

		//4.DB update
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
	
}//end-update
	
//삭제
	public int delete(int idx) {

		int res = 0;//처리된 결과행수

		Connection conn = null;
		PreparedStatement pstmt = null;
      //                                          1  
		String sql = "delete from visit  where idx=?";

		try {
			//1.Connection 얻기
			conn = DBService.getInstance().getConnection();

			//2.pstmt 얻기
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter index채우기
          pstmt.setInt(1, idx);
          
			//4.DB delete
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
