<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
  
  
  
  

</style>

</head>
<body>

<table border="1">
  <!-- title -->
  <tr>
     
     <th>상호명</th>
     <th>링크</th>
     <th>장소 주소</th>
      <th>거리</th>
     <th>폰</th>
     <th>경도</th>
     <th>위도</th>
   
  </tr>
  <!-- data -->
  <!-- for(ProductVo vo : list)  -->
  <c:forEach var="vo"  items="${ list }">
	<tr>
	  
	   <td>${ vo. place_name }</td>
	   <td><a href="${ vo.place_url }">${ vo.place_name }</a></td>
	   <td>${ vo.road_address_name }</td>
	   <td>${ vo.distance }</td>
	   <td>${ vo.phone }</td>
	  <td>${ vo.latitude }</td>
	   <td>${ vo.longitude }</td>
	  
	</tr>
  </c:forEach>




</body>
</html>