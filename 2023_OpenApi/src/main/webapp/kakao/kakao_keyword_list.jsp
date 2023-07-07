<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
  #box{
     width: 1000px;
     margin: auto;
     margin-top: 50px;
     
  }
</style>

<!-- Bootstrap3.x 설정 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
  <div id="box">
    <table class="table table-striped">
       <tr class="primary">
          <th>상호</th>
          <th>주소(지번)</th>
          <th>주소(도로명)</th>
          <th>거리(m)</th>    
       </tr>
       
       <!-- for(LocationVo vo : list)  -->
       <c:forEach var="vo"  items="${ list }"> 
         <tr>
	          <td><a href="${ vo.place_url }">${ vo.place_name }</a></td>
	          <td>${ vo.address_name }</td>
	          <td>${ vo.road_address_name }</td>
	          <td>${ vo.distance }</td>
	       
         </tr> 
       </c:forEach>
       
       <tr>
          <td colspan="5" align="center">
             <a href="../kakao_search.html">다시하기</a>
          </td>
       
       </tr>
    
    </table>
    
    
    
    
  
  </div>
</body>
</html>