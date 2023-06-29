package service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBService {

	
	DataSource ds;
	
	//singleton pattern : 1개의 객체만 생성해서 서비스하자
	static DBService single = null;

	public static DBService getInstance() {

		if (single == null)
			single = new DBService();

		return single;
	}

	//외부에서 new DBService() 못하도록 설정
	private DBService() {
		// TODO Auto-generated constructor stub
		
		//JNDI(Java Naming Directory Interface)통해서 
		//DataSource정보를 얻어온다
		
		try {
			//1.JDNI정보를 얻어올 객체생성
			InitialContext ic = new InitialContext();
			//2.Resource저장된 Context정보 얻어오기
			Context context = (Context) ic.lookup("java:comp/env");
			//3.Context내에 있는 Resource에 의해서 생성된 DataSource
			ds = (DataSource) context.lookup("jdbc/oracle_test");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Connection getConnection() throws SQLException {
		
		return ds.getConnection();
	}
	
	
	
	
	
	
}
