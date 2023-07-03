<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
  #box{
     width: 800px;
     margin: auto;
     margin-top: 10px;
  }

</style>

<!-- Bootstrap3.x 설정 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>



</head>
<body>

	<div id="box">
	  <table class="table table-hover">
	     <tr class="info">
	        <th>고객번호</th>
	        <th>고객이름</th>
	        <th>고객주소</th>
	        <th>고객주민</th>
	        <th>고객담당</th>	      
	     </tr>
	     
	     <!-- for(SawonVo vo : list) -->
	     <c:forEach var="vo"  items="${ list }">
             <tr>
                 <td>${ vo.gobun }</td>
                 <td>${ vo.goname }</td>
                 <td>${ vo.goaddr }</td>
                 <td>${ vo.gojumin }</td>
                 <td>${ vo.godam }</td>
                
             </tr>	        
	     </c:forEach>
	     
	     
	     
	     
	  </table>
	</div>


</body>
</html>


