<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>로그인</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
   
     <link href="../css/test.css?after" rel="stylesheet" />
     <link href="../js/test.js" rel="stylesheet" />
     
     <script type="text/javascript">
   
   function send(f){
	   
	   var email	= f.email.value.trim();
	   var password	= f.password.value.trim();
	   
	   if(email==''){
		   
		   alert('이메일를 입력하세요');
		   f.email.value='';
		   f.email.focus();
		   return;
	   }
	   
	   if(password==''){
		   
		   alert('비밀번호를 입력하세요');
		   f.password.value='';
		   f.password.focus();
		   return;
	   }
	   
	   //전송
	   f.action = "login.do";//MemberLoginAction
	   f.submit();
	   
   }
</script>



<script type="text/javascript">

   //jQuery초기화 이벤트
   $(document).ready(function(){
	   
	   // 0.1초후에 호출 : 로그인폼 show된후에 호출
	   setTimeout(show_message,100);
	   //show_message();
	   
   });
   
   function show_message(){
	   
	 //login_form.do?reason=fail_id
	 if("${ param.reason eq 'fail_email' }" == "true"){
		alert('이메일이 틀렸습니다!!'); 
	 }
	 
	 if("${ param.reason eq 'fail_password' }" == "true"){
		alert('비밀번호가 틀렸습니다!!'); 
	 }		   
			   
	   
   }


</script>
     
</head>



<body>
<form action="login.do">

    <div class="wrapper1">
    
        <h3>Log-in</h3>
        <input class="inputbox" id="email" type="text" value="${ param.email }" placeholder="이메일을 입력해 주세요."/>
        <div class="error" id="error__email"></div>

        <input class="inputbox" id="password" type="password" placeholder="비밀번호를 입력해 주세요."/>
        <div class="error" id="error__password1"></div>

        <hr />
        <div class="footer">
            <button id="signup__button" onclick="send(this.form);return false;" >로그인</button>
        </div>
        
         <div class="footer">
            <button id="signup__button1" onclick="location.href='insert_form.do'" >회원가입</button>
        </div>
        <input class="btn btn-success" type="button"  value="목록보기"
	                            onclick="location.href='list.do'">

    </div>
   </form>
</body>


</html>