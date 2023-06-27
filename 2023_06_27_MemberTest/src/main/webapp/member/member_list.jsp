<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Bootstrap3.x 설정 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<style type="text/css">
  #box{
     width: 1100px;
     margin: auto;
     margin-top: 30px;
  }
  
  #title{
     text-align: center;
     font-size: 28px;
     font-weight: bold;
     
     color: green;
     text-shadow: 1px 1px 3px black;
  }
  
  #empty_message{
     text-align: center;
     margin-top: 200px;
     color : red;
     font-weight: bold;
     font-size: 26px;
  }

</style>

</head>
<body>


<div id="box">
   <h1 id="title">::::회원관리::::</h1>
   
   <!-- 로그인/로그아웃  -->
   <div style="text-align: right;">
       <!-- 로그인 안된경우 -->
       <c:if test="${ empty sessionScope.user }">
           <input class="btn btn-primary" type="button"  value="로그인" 
                  onclick="location.href='login_form.do'">
       </c:if>
       
       <!-- 로그인 된경우 -->
       <c:if test="${ not empty sessionScope.user }">
           <b>${ user.mem_name }</b>님 환영합니다
           <input class="btn btn-primary" type="button" value="로그아웃" 
                  onclick="location.href='logout.do'">
       </c:if>
   </div>
   
   <div style="margin-bottom: 10px;">
       <input class="btn btn-primary"  type="button"  value="회원가입"
              onclick="location.href='insert_form.do'">
   </div>
   
  
   
   
   <table class="table">
   
   <!-- title  -->
   <tr class="success">
      <th>회원번호</th>
      <th>회원명</th>
      <th>아이디</th>
      <th>비밀번호</th>
      <th>우편번호</th>
      <th>주소</th>
      <th>아이피</th>
      <th>가입일자</th>
      <th>회원구분</th>
      <th>편집</th>
   </tr>
   
   
   <c:if test="${ empty list }">
     <tr>
        <td colspan="10">
        <div id="empty_message">가입된 회원정보가 없습니다</div>
        </td>
     </tr> 
   </c:if>
   
   <!-- for(MemberVo vo : list)      -->
   <c:forEach var="vo"  items="${ list }">
      <tr>
         <td>${ vo.mem_idx }</td>
         <td>${ vo.mem_name }</td>
         <td>${ vo.mem_id }</td>
         <td>${ vo.mem_pwd_mask }</td>
         <td>${ vo.mem_zipcode }</td>
         <td>${ vo.mem_addr }</td>
         <td>${ vo.mem_ip }</td>
         <td>${ vo.mem_regdate }</td>
         <td>${ vo.mem_grade }</td>
         
         
         <td>
	         <!-- 관리자이거나 로그인사용자 본인일경우만 -->
	         <c:if test="${ (user.mem_grade eq '관리자') or (user.mem_idx == vo.mem_idx) }">
	             <input class="btn btn-success" type="button"  value="수정"
	                    onclick="location.href='modify_form.do?mem_idx=${ vo.mem_idx }'">
	                    
	             <input class="btn btn-danger"  type="button"  value="삭제"
	              onclick="del('${ vo.mem_idx }');" >
	         </c:if>    
         </td>

         
         
      </tr> 
   </c:forEach>
   
   
   </table>
   
   
   
   
</div>



</body>
</html>