package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.DBService;
import vo.PhotoVo;

public class PhotoDao {

	//singleton pattern : 1���� ��ü�� �����ؼ� ��������
	static PhotoDao single = null;

	public static PhotoDao getInstance() {

		if (single == null)
			single = new PhotoDao();

		return single;
	}

	//�ܺο��� new PhotoDao() ���ϵ��� ����
	private PhotoDao() {
		// TODO Auto-generated constructor stub
	}
	
	//��ü��ȸ           DB select-> Java ArrayList����
	public List<PhotoVo> selectList() {

		List<PhotoVo> list = new ArrayList<PhotoVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from photo order by p_idx desc";

		try {

			//1.Connection ������
			conn = DBService.getInstance().getConnection();

			//2.SQL���ó����ü ������
			pstmt = conn.prepareStatement(sql);

			//3.�����ó����ü ������
			rs = pstmt.executeQuery();

			while (rs.next()) {

				PhotoVo vo = new PhotoVo();

				//vo->rs���� �о�°� �ֱ�
				vo.setP_idx(rs.getInt("p_idx"));
				vo.setP_subject(rs.getString("p_subject"));
				vo.setP_content(rs.getString("p_content"));
				vo.setP_filename(rs.getString("p_filename"));
				vo.setP_ip(rs.getString("p_ip"));
				vo.setP_regdate(rs.getString("p_regdate"));
				vo.setP_modifydate(rs.getString("p_modifydate"));
				vo.setMem_idx(rs.getInt("mem_idx"));
							
				//ArrayList�� �߰�
				list.add(vo);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			//������ �ڵ��ۼ�
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

			//1.Connection ������
			conn = DBService.getInstance().getConnection();

			//2.SQL���ó����ü ������
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter index
			pstmt.setInt(1, p_idx);

			//4.�����ó����ü ������
			rs = pstmt.executeQuery();

			if (rs.next()) {

				vo = new PhotoVo();

				//vo->rs���� �о�°� �ֱ�
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

			//������ �ڵ��ۼ�
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

		int res = 0;//ó���� ������

		Connection conn = null;
		PreparedStatement pstmt = null;
        //                                                             1 2 3 4                 5 
		String sql = "insert into photo values(seq_photo_p_idx.nextVal,?,?,?,?,sysdate,sysdate,?)";

		try {
			//1.Connection ���
			conn = DBService.getInstance().getConnection();

			//2.pstmt ���
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter indexä���
			pstmt.setString(1, vo.getP_subject());
			pstmt.setString(2, vo.getP_content());
			pstmt.setString(3, vo.getP_filename());
			pstmt.setString(4, vo.getP_ip());
			pstmt.setInt(5, vo.getMem_idx());

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
	
	public int delete(int p_idx) {

		int res = 0;//ó���� ������

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "delete from photo where p_idx=?";

		try {
			//1.Connection ���
			conn = DBService.getInstance().getConnection();

			//2.pstmt ���
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter indexä���
            pstmt.setInt(1, p_idx);
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
