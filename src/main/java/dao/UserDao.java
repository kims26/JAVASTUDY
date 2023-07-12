package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.DBService;
import vo.UserVo;

public class UserDao {
//singleton pattern : 1개의 객체만 생성해서 서비스하자
static UserDao single = null;

public static UserDao getInstance() {

	if (single == null)
		single = new UserDao();

	return single;
}

//외부에서 new UserDao() 못하도록 설정
private UserDao() {
	
}


public List<UserVo> selectList() {
	
	

	List<UserVo> list = new ArrayList<UserVo>();

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = "select * from project_user order by u_idx";

	try {

		//1.Connection 얻어오기
		conn = DBService.getInstance().getConnection();

		//2.SQL명령처리객체 얻어오기
		pstmt = conn.prepareStatement(sql);

		//3.결과행처리객체 얻어오기
		rs = pstmt.executeQuery();

		while (rs.next()) {

			UserVo vo = new UserVo();

			//vo->rs에서 읽어온값 넣기
			vo.setU_idx(rs.getInt("u_idx"));
			vo.setName(rs.getString("name"));
			vo.setNickname(rs.getString("Nickname"));
			vo.setEmail(rs.getString("email"));
			vo.setPassword(rs.getString("password"));
			vo.setAddr(rs.getString("Addr"));
			vo.setPhone_number(rs.getString("phone_number"));
			vo.setProfilelmage(rs.getString("Profilelmage"));
	
			
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

public UserVo selectOne(int u_idx) {

	UserVo vo = null;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = "select * from project_user where u_idx=?";

	try {

		//1.Connection 얻어오기
		conn = DBService.getInstance().getConnection();

		//2.SQL명령처리객체 얻어오기
		pstmt = conn.prepareStatement(sql);

		//3.pstmt parameter index
		pstmt.setInt(1, u_idx);

		//4.결과행처리객체 얻어오기
		rs = pstmt.executeQuery();

		if (rs.next()) {

			vo = new UserVo();

			//vo->rs에서 읽어온값 넣기
			vo.setU_idx(rs.getInt("u_idx"));
			vo.setName(rs.getString("name"));
			vo.setNickname(rs.getString("Nickname"));
			vo.setEmail(rs.getString("email"));
			vo.setPassword(rs.getString("password"));
			vo.setAddr(rs.getString("Addr"));
			vo.setPhone_number(rs.getString("phone_number"));
			vo.setProfilelmage(rs.getString("Profilelmage"));

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

public UserVo selectOne(String email) {

	UserVo vo = null;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = "select * from project_user where email=?";

	try {

		//1.Connection 얻어오기
		conn = DBService.getInstance().getConnection();

		//2.SQL명령처리객체 얻어오기
		pstmt = conn.prepareStatement(sql);

		//3.pstmt parameter index
		pstmt.setString(3, email);

		//4.결과행처리객체 얻어오기
		rs = pstmt.executeQuery();

		if (rs.next()) {

			vo = new UserVo();

			//vo->rs에서 읽어온값 넣기
			vo.setU_idx(rs.getInt("u_idx"));
			vo.setName(rs.getString("name"));
			vo.setNickname(rs.getString("Nickname"));
			vo.setEmail(rs.getString("email"));
			vo.setPassword(rs.getString("password"));
			vo.setAddr(rs.getString("Addr"));
			vo.setPhone_number(rs.getString("phone_number"));
			vo.setProfilelmage(rs.getString("Profilelmage"));

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
public int insert(UserVo vo) {

	int res = 0;//처리된 결과행수

	Connection conn = null;
	PreparedStatement pstmt = null;

	//                                                                 1 2 3 4 5 6
	String sql = "insert into user values(seq_user_u_idx.nextVal,?,?,?,?,?,?)";

	try {
		//1.Connection 얻기
		conn = DBService.getInstance().getConnection();

		//2.pstmt 얻기
		pstmt = conn.prepareStatement(sql);

		//3.pstmt parameter index채우기
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getNickname());
		pstmt.setString(3, vo.getEmail());
		pstmt.setString(4, vo.getPassword());
		pstmt.setString(5, vo.getAddr());
		pstmt.setString(6, vo.getPhone_number());
		

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


public int update(UserVo vo) {

	int res = 0;//처리된 결과행수

	Connection conn = null;
	PreparedStatement pstmt = null;
    //                                       1        2         3                                
	String sql = "update project_user set name=?,nickname=?,emali=?,"+
    //                        4          5        6           7   
                 "password=?,addr=?,phone_number=?,profilelmage=?";
    //                                              8
			    

	try {
		//1.Connection 얻기
		conn = DBService.getInstance().getConnection();

		//2.pstmt 얻기
		pstmt = conn.prepareStatement(sql);

		//3.pstmt parameter index채우기
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getNickname());
		pstmt.setString(3, vo.getEmail());
		pstmt.setString(4, vo.getPassword());
		pstmt.setString(5, vo.getAddr());
		pstmt.setString(6, vo.getPhone_number());
		pstmt.setString(7, vo.getProfilelmage());
        
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

public int delete(int u_idx) {

	int res = 0;//처리된 결과행수

	Connection conn = null;
	PreparedStatement pstmt = null;

	String sql = "delete from project_user where u_idx=?";

	try {
		//1.Connection 얻기
		conn = DBService.getInstance().getConnection();

		//2.pstmt 얻기
		pstmt = conn.prepareStatement(sql);

		//3.pstmt parameter index채우기
        pstmt.setInt(1, u_idx);
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
