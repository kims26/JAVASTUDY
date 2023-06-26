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
     width: 1000px;
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

<script type="text/javascript">


function del(f){
	
	  var mem_idx   = f.mem_idx.value;
	  var c_pwd 	= f.c_pwd.value.trim();
	  
	  if(c_pwd==''){		
		  
		  Swal.fire({
			  icon: 'error',
			  title: '삭제비밀번호 입력하세요',
			  text: '삭제비밀번호...',
			  returnFocus:false,
			  confirmButtonText:'확인'
			}).then((result)=>{
				
				f.c_pwd.value='';
				f.c_pwd.focus();
			});
		  
		  /*
		  alert('삭제 비밀번호를 입력하세요!');
		  f.c_pwd.value='';
		  f.c_pwd.focus();
		  */
		  
		  return;
	  }
	  
	  
	  //서버로 비밀번호 맞는지 여부 확인(Ajax) : check_pwd.do?idx=5&c_pwd=1234
	  $.ajax({
		  url		: "check_pwd.do",                 //VisitCheckPwdAction
		  data		: { 'mem_idx': mem_idx, 'c_pwd': c_pwd }, //parameter
		  dataType 	: 'json',
		  success	: function(res_data){
			  // res_data = {"result": true} or {"result": false}
			  
			  if(res_data.result==false){
				  
				  Swal.fire({
					  icon: 'error',
					  title: '삭제비밀번호 다시입력하세요',
					  text: '삭제비밀번호...',
					  returnFocus:false,
					  confirmButtonText:'확인'
					}).then((result)=>{
						
						f.c_pwd.value='';
						f.c_pwd.focus();
					});
				 
				  /*
				  alert('비밀번호가 틀렸습니다');
				  */
				  return;
			  }
			  
			  //삭제수행(확인)
			  if(confirm("정말 삭제하시겠습니까?")==false)
				  Swal.fire({
					  icon: 'error',
					  title: '정말 삭제하시겠습니까',
					  text: '삭제비밀번호...',
					  returnFocus:false,
					  confirmButtonText:'확인'
					}).then((result)=>{
						
						f.confirm.value='';
						f.confirm.focus();
					});
				  return;
			  
			  //삭제수행
			  location.href="delete.do?mem_idx=" + mem_idx;
			  
			  
		  },
		  error		: function(err){
			  
			  
			  
			  
			//  alert(err.responseText);
			  
		  }
	  });
}//end-del



function modify_form(f){
	  
	  var mem_idx   = f.mem_idx.value;
	  var c_pwd		 =f.c_pwd.value.trim();
	  
	  if(c_pwd==''){
		  
		  Swal.fire({
			  icon: 'error',
			  title: '수정비밀번호 입력하세요',
			  text: '수정비밀번호...',
			  returnFocus:false,
			  confirmButtonText:'확인'
			}).then((result)=>{
				
				f.c_pwd.value='';
				f.c_pwd.focus();
			});
		  
		  
		  /*
		  alert('수정 비밀번호를 입력하세요!');
		  f.c_pwd.value='';
		  f.c_pwd.focus();
		  */
		  return;
	  }
	  
	  
	  //서버로 비밀번호 맞는지 여부 확인(Ajax) : check_pwd.do?idx=5&c_pwd=1234
	  $.ajax({
		  url		: "check_pwd.do",                 //VisitCheckPwdAction
		  data		: { 'mem_idx': mem_idx, 'c_pwd': c_pwd }, //parameter
		  dataType 	: 'json',
		  success	: function(res_data){
			  // res_data = {"result": true} or {"result": false}
			  
			  if(res_data.result==false){				  				  
				  Swal.fire({
					  icon: 'error',
					  title: '삭제비밀번호 다시입력하세요',
					  text: '비밀번호틀렸다...',
					  returnFocus:false,
					  confirmButtonText:'확인'
					}).then((result)=>{
						
						f.c_pwd.value='';
						f.c_pwd.focus();
					
					});
				 /*
				  alert('비밀번호가 틀렸습니다');
				 */
				  return;
			  }
			 
			  
			  //수정폼 띄우기
			  location.href="modify_form.do?mem_idx=" + mem_idx;
			  
			  
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
           <input class="btn btn-primary" type="button" value="logout" 
                  onclick="loaction.href='logout.do'">
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
         <td>${ vo.mem_pwd }</td>
         <td>${ vo.mem_zipcode }</td>
         <td>${ vo.mem_addr }</td>
         <td>${ vo.mem_ip }</td>
         <td>${ vo.mem_regdate }</td>
         <td>${ vo.mem_grade }</td>
         <td>
           
              <form class="form-inline">
	             <input type="hidden" name="idx"  value="${ vo.mem_idx }">
		         <div class="mem_pwd">
		            <label>비밀번호(${ vo.mem_pwd }) : </label>
		            <input class="form-control" type="password"  name="c_pwd">
		            
		            <input class="btn btn-info"   type="button" value="수정"
		                   onclick="modify_form(this.form);" >
		                   
		            <input class="btn btn-danger" type="button" value="삭제"
		                   onclick="del(this.form);">
		            
		         </div>
	         </form>
            
         </td>
      </tr> 
   </c:forEach>
   
   
   </table>
   
   
   
   
</div>



</body>
</html>