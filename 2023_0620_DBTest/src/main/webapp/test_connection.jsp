<%@page import="service.DBService"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%! 

    int count = 0;
%> 
    
<%
    Connection conn = DBService.getInstance().getConnection();

    System.out.println("---Connection Get : success--");
    
    //BasicDataSource에게 close통보 : 다시 채워넣기함
    conn.close();
    
    count++;

%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%= count %>번 호출
</body>
</html>