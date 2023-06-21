package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.DBService;
import vo.SungVo;

public class SungTBDao {

	//singleton pattern : 1���� ��ü�� �����ؼ� ��������
	static SungTBDao single = null;

	public static SungTBDao getInstance() {

		if (single == null)
			single = new SungTBDao();

		return single;
	}

	//�ܺο��� new SungTBDao() ���ϵ��� ����
	private SungTBDao() {
		// TODO Auto-generated constructor stub
	}
	
	//��ü��ȸ           DB select-> Java ArrayList����
	public List<SungVo> selectList() {

		List<SungVo> list = new ArrayList<SungVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from sungtb_view";

		try {

			//1.Connection ������
			conn = DBService.getInstance().getConnection();

			//2.SQL���ó����ü ������
			pstmt = conn.prepareStatement(sql);

			//3.�����ó����ü ������
			rs = pstmt.executeQuery();

			while (rs.next()) {

				SungVo vo = new SungVo();

				//vo->rs���� �о�°� �ֱ�
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setKor(rs.getInt("kor"));
				vo.setEng(rs.getInt("eng"));
				vo.setMat(rs.getInt("mat"));
				vo.setTot(rs.getInt("tot"));
				vo.setAvg(rs.getString("avg"));
				vo.setRank(rs.getInt("rank"));
							

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
	
	
	//idx �� �ش�Ǵ� ���ڵ� 1�� ��������
	public SungVo selectOne(int idx) {

		SungVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from sungtb_view where idx=?";

		try {

			//1.Connection ������
			conn = DBService.getInstance().getConnection();

			//2.SQL���ó����ü ������
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter index
            pstmt.setInt(1, idx);
			//4.�����ó����ü ������
			rs = pstmt.executeQuery();

			if (rs.next()) {

				vo = new SungVo();

				//vo->rs���� �о�°� �ֱ�
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setKor(rs.getInt("kor"));
				vo.setEng(rs.getInt("eng"));
				vo.setMat(rs.getInt("mat"));
				vo.setTot(rs.getInt("tot"));
				vo.setAvg(rs.getString("avg"));
				vo.setRank(rs.getInt("rank"));
				

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
	
	
	
	
	
	
	
	//insert
	public int  insert(SungVo vo) {
		
		int res = 0;//ó���� ������
		
		Connection 			conn	= null;
		PreparedStatement 	pstmt	= null;

		
		String				sql =   //                             1 2 3 4 <- pstmt parameter index  
				"insert into sungtb values( seq_sungtb_idx.nextVal,?,?,?,?)";
		
		try {
			//1.Connection ���
			conn = DBService.getInstance().getConnection();
			
			//2.pstmt ���
			pstmt = conn.prepareStatement(sql);
			
			//3.pstmt parameter indexä���
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getKor());
			pstmt.setInt(3, vo.getEng());
			pstmt.setInt(4, vo.getMat());
			
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
	
	//����(update)
	public int update(SungVo vo) {

		int res = 0;//ó���� ������

		Connection conn = null;
		PreparedStatement pstmt = null;
        //                                   1     2     3     4           5
		String sql = "update sungtb set name=?,kor=?,eng=?,mat=? where idx=?";

		try {
			//1.Connection ���
			conn = DBService.getInstance().getConnection();

			//2.pstmt ���
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter indexä���
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getKor());
			pstmt.setInt(3, vo.getEng());
			pstmt.setInt(4, vo.getMat());
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
	
	
	//����
	public int delete(int idx) {

		int res = 0;//ó���� ������

		Connection conn = null;
		PreparedStatement pstmt = null;
        //                                          1  
		String sql = "delete from sungtb  where idx=?";

		try {
			//1.Connection ���
			conn = DBService.getInstance().getConnection();

			//2.pstmt ���
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter indexä���
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
