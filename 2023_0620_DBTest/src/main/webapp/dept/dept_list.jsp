<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">






#box{
width: 500px;
margin: auto;
margin-top: 100px;
}



</style>

<!-- Bootstrap3.x 설정 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


</head>
<body>

    
<div id="box">
<table class="table table-striped table-hover">
<tr>
	<th>부서번호</th>
	<th>부서명</th>
	<th>위치</th>
</tr>

<!-- for(DeptVo vo:list -->
<c:forEach var="vo" items="${list }">
<tr>
	<td>${vo.deptno }</td>
	<td>${vo.dname }</td>
	<td>${vo.loc }</td>
</tr>


</c:forEach>

</table>

</div>

</body>
</html>