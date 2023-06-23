<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    //client측에 A.jsp연결정보를 기록
    //						name	value
    Cookie cookie =new Cookie("C","C.jsp");
    
    //Persistance
     cookie.setMaxAge(60*60*24);//sec단위
   
    
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



여기는 C.jsp영억입니다<br>
<a href="A.jsp">A page이동</a>
</body>
</html>