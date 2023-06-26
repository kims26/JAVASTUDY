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
	
	
}
public List<MemberVo> selectList() {

	List<MemberVo> list = new ArrayList<MemberVo>();

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = "select * from member order by mem_idx";

	try {

		//1.Connection ������
		conn = DBService.getInstance().getConnection();

		//2.SQL���ó����ü ������
		pstmt = conn.prepareStatement(sql);

		//3.�����ó����ü ������
		rs = pstmt.executeQuery();

		while (rs.next()) {

			MemberVo vo = new MemberVo();

			//vo->rs���� �о�°� �ֱ�
			vo.setMem_idx(rs.getInt("mem_idx"));
			vo.setMem_name(rs.getString("mem_name"));
			vo.setMem_id(rs.getString("mem_id"));
			vo.setMem_pwd(rs.getString("mem_pwd"));
			vo.setMem_zipcode(rs.getString("mem_zipcode"));
			vo.setMem_addr(rs.getString("mem_addr"));
			vo.setMem_ip(rs.getString("mem_ip"));
			vo.setMem_regdate(rs.getString("mem_regdate"));
			vo.setMem_grade(rs.getString("mem_grade"));
			
						

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

public MemberVo selectOne(int mem_idx) {
	

	MemberVo vo = null;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = "select * from member where mem_idx=?";

	try {

		//1.Connection ������
		conn = DBService.getInstance().getConnection();

		//2.SQL���ó����ü ������
		pstmt = conn.prepareStatement(sql);

		//3.pstmt parameter index
        pstmt.setInt(1, mem_idx);
		//4.�����ó����ü ������
		rs = pstmt.executeQuery();

		if (rs.next()) {

			vo = new MemberVo();

			//vo->rs���� �о�°� �ֱ�
			
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
public MemberVo selectOne(String mem_id) {

	MemberVo vo = null;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = "select * from member where mem_id=?";

	try {

		//1.Connection ������
		conn = DBService.getInstance().getConnection();

		//2.SQL���ó����ü ������
		pstmt = conn.prepareStatement(sql);

		//3.pstmt parameter index
	    pstmt.setString(1, mem_id);

		//4.�����ó����ü ������
		rs = pstmt.executeQuery();

		if (rs.next()) {

			vo = new MemberVo();

			//vo->rs���� �о�°� �ֱ�
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
//mem_id�� �ش�Ǵ� ȸ������ 1��

public int insert(MemberVo vo) {
	
	int res = 0;//ó���� ������
	
	Connection 			conn	= null;
	PreparedStatement 	pstmt	= null;

	
	String				sql =   //                             1 2 3 4 <- pstmt parameter index  
			"insert into member values( seq_member_mem_idx.nextVal,?,?,?,?,?,?,sysdate,'�Ϲ�')";
	
	try {
		//1.Connection ���
		conn = DBService.getInstance().getConnection();
		
		//2.pstmt ���
		pstmt = conn.prepareStatement(sql);
		
		//3.pstmt parameter indexä���

		
		pstmt.setString(1, vo.getMem_name());
		pstmt.setString(2, vo.getMem_id());
		pstmt.setString(3, vo.getMem_pwd());
		pstmt.setString(4, vo.getMem_zipcode());
		pstmt.setString(5, vo.getMem_addr());
		pstmt.setString(6, vo.getMem_ip());
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

public int update(MemberVo vo) {

	int res = 0;//ó���� ������

	Connection conn = null;
	PreparedStatement pstmt = null;
    //                                   1     2     3     4           5
	String sql = "update member set name=?,id=?,pwd=?,zipcode=?,addr=?,ip=?,grade=?, where idx=?";

	try {
		//1.Connection ���
		conn = DBService.getInstance().getConnection();

		//2.pstmt ���
		pstmt = conn.prepareStatement(sql);

		//3.pstmt parameter indexä���
		pstmt.setString(1, vo.getMem_name());
		pstmt.setString(2, vo.getMem_id());
		pstmt.setString(3, vo.getMem_pwd());
		pstmt.setString(4, vo.getMem_zipcode());
		pstmt.setString(5, vo.getMem_addr());
		pstmt.setString(6, vo.getMem_ip());
		//pstmt.setString(5, vo.getRegdate());
		pstmt.setString(7, vo.getMem_grade());
		pstmt.setInt(8, vo.getMem_idx());
	

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


}

public int delete(int mem_idx) {

	int res = 0;//ó���� ������

	Connection conn = null;
	PreparedStatement pstmt = null;
  //                                          1  
	String sql = "delete from member  where idx=?";

	try {
		//1.Connection ���
		conn = DBService.getInstance().getConnection();

		//2.pstmt ���
		pstmt = conn.prepareStatement(sql);

		//3.pstmt parameter indexä���
      pstmt.setInt(1, mem_idx);
      
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
