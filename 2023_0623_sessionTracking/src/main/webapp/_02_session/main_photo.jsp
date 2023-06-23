<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 로그인이 안된 상태에서 -->

<c:if test="${empty sessionScope.user }">

<input type="button" value="로그인" onclick="location.href='login.jsp'">

</c:if>

<!-- 로그인이 된상태에서 -->
	<c:if test="${not empty sessionScope.user }">
	${user}님 화녕합니다
<input type="button" value="로그아웃"
		onclick="location.href='logout.jsp'">
</c:if>


<hr>
<a href="kim.jsp">김태희사진</a>

</body>
</html>