package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.DBService;
import vo.MemberVo;
import vo.PhotoVo;

public class PhotoDao {

	//singleton pattern : 1개의 객체만 생성해서 서비스하자
	static PhotoDao single = null;

	public static PhotoDao getInstance() {

		if (single == null)
			single = new PhotoDao();

		return single;
	}

	//외부에서 new PhotoDao() 못하도록 설정
	private PhotoDao() {
		// TODO Auto-generated constructor stub
	}
	
	//전체조회           DB select-> Java ArrayList포장
	public List<PhotoVo> selectList() {

		List<PhotoVo> list = new ArrayList<PhotoVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from photo order by p_idx desc";

		try {

			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2.SQL명령처리객체 얻어오기
			pstmt = conn.prepareStatement(sql);

			//3.결과행처리객체 얻어오기
			rs = pstmt.executeQuery();

			while (rs.next()) {

				PhotoVo vo = new PhotoVo();

				//vo->rs에서 읽어온값 넣기
				vo.setP_idx(rs.getInt("p_idx"));
				vo.setP_subject(rs.getString("p_subject"));
				vo.setP_content(rs.getString("p_content"));
				vo.setP_filename(rs.getString("p_filename"));
				vo.setP_ip(rs.getString("p_ip"));
				vo.setP_regdate(rs.getString("p_regdate"));
				vo.setP_modifydate(rs.getString("p_modifydate"));
				vo.setMem_idx(rs.getInt("mem_idx"));
				
				

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
	

public PhotoVo selectOne(int p_idx) {
	

	PhotoVo vo = null;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = "select * from photo where p_idx=?";

	try {

		//1.Connection 얻어오기
		conn = DBService.getInstance().getConnection();

		//2.SQL명령처리객체 얻어오기
		pstmt = conn.prepareStatement(sql);

		//3.pstmt parameter index
        pstmt.setInt(1, p_idx);
		//4.결과행처리객체 얻어오기
		rs = pstmt.executeQuery();

		if (rs.next()) {

			vo = new PhotoVo();

			//vo->rs에서 읽어온값 넣기
			
			vo.setP_idx(rs.getInt("p_idx"));
			vo.setP_subject(rs.getString("p_subject"));
			vo.setP_content(rs.getString("p_content"));
			vo.setP_filename(rs.getString("p_filename"));
			vo.setP_ip(rs.getString("p_ip"));
			vo.setP_regdate(rs.getString("p_regdate"));
			vo.setP_modifydate(rs.getString("p_modifydate"));
			vo.setMem_idx(rs.getInt("mem_idx"));

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



public int insert(PhotoVo vo) {

	int res = 0;//처리된 결과행수

	Connection conn = null;
	PreparedStatement pstmt = null;

	//                                                                 1 2 3 4 5 6
	String sql = "insert into member values(seq_photo_p_idx.nextVal,?,?,?)";

	try {
		//1.Connection 얻기
		conn = DBService.getInstance().getConnection();

		//2.pstmt 얻기
		pstmt = conn.prepareStatement(sql);

		//3.pstmt parameter index채우기
		pstmt.setString(1, vo.getP_subject());
		pstmt.setString(2, vo.getP_content());
		pstmt.setString(3, vo.getP_filename());
		


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
