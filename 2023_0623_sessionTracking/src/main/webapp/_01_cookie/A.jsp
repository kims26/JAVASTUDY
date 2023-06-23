<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    //client측에 A.jsp연결정보를 기록
    Cookie cookie =new Cookie("A","A.jsp");
    cookie.setMaxAge(60*60*24);
    response.addCookie(cookie);
    
    
    
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="popup.jsp" %>



여기는 A.jsp영억입니다<br>
<a href="B.jsp">B page이동</a>
</body>
</html>