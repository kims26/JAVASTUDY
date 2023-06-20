package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.DBService;
import vo.SawonVo;

public class SawonDao {

	//singleton pattern : 1���� ��ü�� �����ؼ� ��������
	static SawonDao single = null;

	public static SawonDao getInstance() {

		if (single == null)
			single = new SawonDao();

		return single;
	}

	//�ܺο��� new SawonDao() ���ϵ��� ����
	private SawonDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	//��ü��ȸ           DB select-> Java ArrayList����
	public List<SawonVo> selectList() {

		List<SawonVo> list = new ArrayList<SawonVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from sawon";

		try {

			//1.Connection ������
			conn = DBService.getInstance().getConnection();

			//2.SQL���ó����ü ������
			pstmt = conn.prepareStatement(sql);

			//3.�����ó����ü ������
			rs = pstmt.executeQuery();

			while (rs.next()) {

				SawonVo vo = new SawonVo();

				//vo->rs���� �о�°� �ֱ�
                vo.setSabun(rs.getInt("sabun"));
                vo.setSaname(rs.getString("saname"));
                vo.setSajob(rs.getString("sajob"));
                vo.setSasex(rs.getString("sasex"));
                vo.setSahire(rs.getString("sahire"));
                vo.setSamgr(rs.getInt("samgr"));
                vo.setSapay(rs.getInt("sapay"));
                vo.setDeptno(rs.getInt("deptno"));
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

	public List<SawonVo> selectDeptnoList(int deptno ) {
		// TODO Auto-generated method stub
		List<SawonVo> list = new ArrayList<SawonVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        //                                             1 <- index
		String sql = "select * from sawon where deptno=?";

		try {

			//1.Connection ������
			conn = DBService.getInstance().getConnection();

			//2.SQL���ó����ü ������
			pstmt = conn.prepareStatement(sql);

			//2-1.pstmt parameter����
			pstmt.setInt(1, deptno);
			
			
			//3.�����ó����ü ������
			rs = pstmt.executeQuery();

			while (rs.next()) {

				SawonVo vo = new SawonVo();

				//vo->rs���� �о�°� �ֱ�
                vo.setSabun(rs.getInt("sabun"));
                vo.setSaname(rs.getString("saname"));
                vo.setSajob(rs.getString("sajob"));
                vo.setSasex(rs.getString("sasex"));
                vo.setSahire(rs.getString("sahire"));
                vo.setSamgr(rs.getInt("samgr"));
                vo.setSapay(rs.getInt("sapay"));
                vo.setDeptno(rs.getInt("deptno"));
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

	public List<SawonVo> selectSajobList(String sajob ) {
		// TODO Auto-generated method stub
		List<SawonVo> list = new ArrayList<SawonVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        //                                             1 <- index
		String sql = "select * from sawon where sajob=?";

		try {

			//1.Connection ������
			conn = DBService.getInstance().getConnection();

			//2.SQL���ó����ü ������
			pstmt = conn.prepareStatement(sql);

			//2-1.pstmt parameter����
			pstmt.setString(1, sajob);
			
			
			//3.�����ó����ü ������
			rs = pstmt.executeQuery();

			while (rs.next()) {

				SawonVo vo = new SawonVo();

				//vo->rs���� �о�°� �ֱ�
                vo.setSabun(rs.getInt("sabun"));
                vo.setSaname(rs.getString("saname"));
                vo.setSajob(rs.getString("sajob"));
                vo.setSasex(rs.getString("sasex"));
                vo.setSahire(rs.getString("sahire"));
                vo.setSamgr(rs.getInt("samgr"));
                vo.setSapay(rs.getInt("sapay"));
                vo.setDeptno(rs.getInt("deptno"));
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
	
	
	
	
}
