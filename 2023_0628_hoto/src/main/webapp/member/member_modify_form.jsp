<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Bootstrap3.x 설정 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- 우편번호검색 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<style type="text/css">
  
  #box{
     width: 550px;
     margin: auto;
     margin-top: 60px;
  }
  
  label{
     margin-left: 10px;
     display: inline-block;
     width: 80px;
     text-align: right;
     
  }
  
   
  .seperator{
    margin-bottom: 10px;
  }

</style>



 
 
 
 
 

<script type="text/javascript">

  //jQuery초기화
  //$(document).ready(function(){});
  
  //jQuery초기화 이벤트의 약식표현
  $(function(){
	  
	  $("#mem_grade").val("${ vo.mem_grade }");
	  
  });


</script>





</head>
<body>
<form class="form-inline">

  <input type="hidden" name="mem_idx"  value="${ vo.mem_idx }">
  
  <div id="box">
  	<div class="panel panel-primary">
      <div class="panel-heading"><h4>회원수정</h4></div>
      <div class="panel-body">
        
	        <div class="seperator">
	           <label>이름 : </label>
	           <input class="form-control" name="mem_name" value="${ vo.mem_name }">
	        </div>
	        
	        <div class="seperator">
	           <label>아이디 : </label>
	           <input class="form-control" name="mem_id"   value="${ vo.mem_id }"  readonly="readonly" > 
	        </div>
	        
	        <div class="seperator">
	           <label>비밀번호 : </label>
	           <input class="form-control" type="password" name="mem_pwd"  value="${ vo.mem_pwd }">
	        </div>
	        
	        <div class="seperator">
	           <label>우편번호 : </label>
	           <input class="form-control" 
	                  name="mem_zipcode" id="mem_zipcode" value="${ vo.mem_zipcode }" >
	           <input class="btn  btn-info" type="button"  value="검색"
	                  onclick="find_addr();" >
	        </div>
	        
	        <div class="seperator">
	           <label>주소 : </label>
	           <input class="form-control" name="mem_addr" id="mem_addr" size="50" 
	                  value="${ vo.mem_addr }">
	        </div>
	        
	        <!-- 회원구분 -->
	        <div class="seperator">
	           <label>회원구분 : </label>
	           
	           <c:if test="${ user.mem_grade eq '일반' }">
	               <input class="form-control" name="mem_grade"   value="${ vo.mem_grade }"  readonly="readonly" >
	           </c:if>
	           
	           <c:if test="${ user.mem_grade eq '관리자' }">
	               <select class="form-control" name="mem_grade" id="mem_grade">
	                   <option value="일반">일반</option>
	                   <option value="관리자">관리자</option>
	               </select>
	           </c:if>
	            
	        </div>
	        
	        
	        <hr>
	        
	        <div style="text-align: center">
	           <input class="btn  btn-primary" type="button" value="회원수정"
	                  onclick="send(this.form);">
	                  
	           <input class="btn  btn-success" type="button" value="목록보기"
	                  onclick="location.href='list.do'">
	        </div>
	        
      </div>
  	</div>
  	
  </div>
  
</form>

</body>
</html>