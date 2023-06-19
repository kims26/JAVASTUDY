
<%--  JSP주석 : 변환된 서블릿영역에서 제외 
      JSP Header
--%>

<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8;"
    pageEncoding="UTF-8"%>

<%-- JSP선언부 --%>
<%!
	//Java Code영역
	//변수선언
	int a;
    //메소드선언
    public void sub(){
    	
    }
%>
 
 
<%
    //Scriptlet(_jspService(영역내에 코딩)
    //지역변수
    int x = 10;
%> 
 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


안녕하세요!!<br>

<!-- out.print(x);동일하다 -->
<%= x %> <br>
<% out.print(x); %>


<br>요청자IP : <%= request.getRemoteAddr() %>


</body>
</html>