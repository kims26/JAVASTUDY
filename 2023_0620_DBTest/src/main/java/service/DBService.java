package service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBService {

	
	DataSource ds;
	
	//singleton pattern : 1���� ��ü�� �����ؼ� ��������
	static DBService single = null;

	public static DBService getInstance() {

		if (single == null)
			single = new DBService();

		return single;
	}

	//�ܺο��� new DBService() ���ϵ��� ����
	private DBService() {
		// TODO Auto-generated constructor stub
		
		//JNDI(Java Naming Directory Interface)���ؼ� 
		//DataSource������ ���´�
		
		try {
			//1.JDNI������ ���� ��ü����
			InitialContext ic = new InitialContext();
			//2.Resource����� Context���� ������
			Context context = (Context) ic.lookup("java:comp/env");
			//3.Context���� �ִ� Resource�� ���ؼ� ������ DataSource
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
