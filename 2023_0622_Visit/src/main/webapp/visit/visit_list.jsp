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

<style type="text/css">
  #box{
     width: 500px;
     margin: auto;
     margin-top: 20px;
  }
  
  #title{
     text-align: center;
     font-size: 28px;
     font-family: 굴림체,궁서체;
     font-weight: bold;
     color: #3366cc;
     text-shadow: 1px 1px 2px black;
  } 
  
  .content{
     min-height: 80px;
     height: auto;
     border : 1px solid #eeeeee;
     padding: 5px;
     font-weight: bold;
     color: #6688ff;
  }
  
  .regdate{
     margin-top: 5px;
     margin-bottom: 5px;
  }
  
  #empty_message{
     text-align: center;
     margin-top: 150px;
     color:red;
     font-weight: bold;
     font-size: 26px;
     
     text-shadow: 1px 1px 3px black;
  
  }
  
</style>

<script type="text/javascript">
  
  function del(f){
	
	  var idx   = f.idx.value;
	  var c_pwd = f.c_pwd.value.trim();
	  
	  if(c_pwd==''){
		  alert('삭제 비밀번호를 입력하세요!');
		  f.c_pwd.value='';
		  f.c_pwd.focus();
		  return;
	  }
	  
	  
	  //서버로 비밀번호 맞는지 여부 확인(Ajax) : check_pwd.do?idx=5&c_pwd=1234
	  $.ajax({
		  url		: "check_pwd.do",                 //VisitCheckPwdAction
		  data		: { 'idx': idx, 'c_pwd': c_pwd }, //parameter
		  dataType 	: 'json',
		  success	: function(res_data){
			  // res_data = {"result": true} or {"result": false}
			  
			  if(res_data.result==false){
				  alert('비밀번호가 틀렸습니다');
				  return;
			  }
			  
			  //삭제수행(확인)
			  if(confirm("정말 삭제하시겠습니까?")==false)return;
			  
			  //삭제수행
			  location.href="delete.do?idx=" + idx;
			  
			  
		  },
		  error		: function(err){
			  
			  alert(err.responseText);
			  
		  }
	  });
  }//end-del
  
  
  
  function modify_form(f){
	  
	  var idx   = f.idx.value;
	  var c_pwd = f.c_pwd.value.trim();
	  
	  if(c_pwd==''){
		  alert('수정 비밀번호를 입력하세요!');
		  f.c_pwd.value='';
		  f.c_pwd.focus();
		  return;
	  }
	  
	  
	  //서버로 비밀번호 맞는지 여부 확인(Ajax) : check_pwd.do?idx=5&c_pwd=1234
	  $.ajax({
		  url		: "check_pwd.do",                 //VisitCheckPwdAction
		  data		: { 'idx': idx, 'c_pwd': c_pwd }, //parameter
		  dataType 	: 'json',
		  success	: function(res_data){
			  // res_data = {"result": true} or {"result": false}
			  
			  if(res_data.result==false){
				  alert('비밀번호가 틀렸습니다');
				  return;
			  }
			 
			  
			  //수정폼 띄우기
			  location.href="modify_form.do?idx=" + idx;
			  
			  
		  },
		  error		: function(err){
			  
			  alert(err.responseText);
			  
		  }
	  });
	
  }

</script>



</head>
<body>

  <div id="box">
    
    <h1 id="title">::::방명록::::</h1>
    
    <div style="margin-bottom: 10px; margin-top: 20px;">
       <button class="btn btn-primary" 
               onclick="location.href='insert_form.do'">글올리기</button>
    </div>
  
    <!-- 데이터가 없는경우 -->
    <c:if test="${ empty list }">
    
      <div id="empty_message">데이터가 없습니다</div>
    
    </c:if> 
  
  
    <!-- 방명록 내용 --> 
    <!--  for(VisitVo vo : list)  -->
    <c:forEach var="vo"  items="${ list }">
       
       <div class="panel panel-primary">
	      <div class="panel-heading"><h5><b>${ vo.name }</b>님의 글(${ vo.ip })</h5></div>
	      <div class="panel-body">
	         
	         <div class="content">${ vo.content }</div>
	         <div class="regdate"><label>작성일자 : ${ vo.regdate }</label></div>
	        
	         <form class="form-inline">
	             <input type="hidden" name="idx"  value="${ vo.idx }">
		         <div class="pwd">
		            <label>비밀번호(${ vo.pwd }) : </label>
		            <input class="form-control" type="password"  name="c_pwd">
		            
		            <input class="btn btn-info"   type="button" value="수정"
		                   onclick="modify_form(this.form);" >
		                   
		            <input class="btn btn-danger" type="button" value="삭제"
		                   onclick="del(this.form);">
		            
		         </div>
	         </form>
	         
	      </div>
	   </div>
	   
	   
    </c:forEach>
  </div>

</body>
</html>








