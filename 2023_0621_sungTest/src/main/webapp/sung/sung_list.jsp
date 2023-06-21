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
     width: 800px;
     margin: auto;
     margin-top: 30px;
  }
  
  #title{
     text-align: center;
     font-family: 굴림체,궁서체;
     font-size: 28px;
     font-weight: bold;
     color: #33aaff;
     text-shadow: 1px 1px 3px black;
  }
  
  
  th,td{
     text-align: center;
  }
  
</style>


<script type="text/javascript">
  
  function del(idx){
	 
	  if(confirm("정말 삭제하시겠습니까?")==false) return;
	  
	  //alert(idx+"번 성적 삭제");
	  
	  location.href="delete.do?idx=" + idx; //SungDeleteAction
	  
	  
	  
  }


</script>



</head>
<body>

<div id="box">
   <h1 id="title">::::성적관리::::</h1>
   <div style="text-align: right;  margin-bottom: 5px;">
      <input class="btn btn-primary"  type="button" value="성적등록"
             onclick="location.href='insert_form.do'"  >
   </div>
   
   <table class="table">
      <tr class="info">
         <th>번호</th>
         <th>이름</th>
         <th>국어</th>
         <th>영어</th>
         <th>수학</th>
         <th>총점</th>
         <th>평균</th>
         <th>등수</th>
         <th>편집</th>
      </tr>
      
      <!-- Data -->
      
      <!-- 데이터가 없으면 -->
      <c:if test="${ empty list }">
         <tr>
            <td colspan="9" align="center">
               <font color="red">데이터 없습니다</font>
            </td>
         </tr>
      </c:if>
      
      
      
      <!--  for(SungVo vo : list) -->
      <c:forEach var="vo"  items="${ list }">
        <tr>
           <td>${ vo.idx }</td>
           <td>${ vo.name }</td>
           <td>${ vo.kor }</td>
           <td>${ vo.eng }</td>
           <td>${ vo.mat }</td>
           <td>${ vo.tot }</td>
           <td>${ vo.avg }</td>
           <td>${ vo.rank }</td>
           <td>
               <input class="btn btn-success" type="button"  value="수정"
                      onclick="location.href='modify_form.do?idx=${ vo.idx }'">
                      
               <input class="btn btn-danger"  type="button"  value="삭제"
                      onclick="del('${ vo.idx }');" >
           </td>
        </tr>
      </c:forEach>
      
      
   </table>
   
   
</div>



</body>
</html>