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

<c:if test="${empty sessionScope.user }">
<script type="text/javascript">

alert("현재 컨텐츠를 이용하려면 로그인 을 하세요")

location.href="main_photo.jsp";
</script>

</c:if>

김태희 사진
<hr>
<img src="../kim.png"><br>

<a href="main_photo.jsp">다시하기</a>
</body>
</html>