<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>회원가입</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
     <link href="../css/test.css?after" rel="stylesheet" />
     <link href="../js/test.js" rel="stylesheet" />
     
     <script type="text/javascript">

     function check_email(){
    	 //           document.getElementById("mem_id").value 
    	 let email = $("#email").val();

    	 
    	 if(email.length<3){
    		 
    		 $("#email_message").html("이메일 3자리이상 입력")
    		                 .css("color","red");
    		 
    		 //회원가입버튼 비활성화
    		 $("#btn_register").attr("disabled",true);//비활성화
    		 
    		 return;
    	 }
    	 
 		if(email.includes("@")){
    		 
    		 $("#email_message").html("@입력")
    		                 .css("color","red");
    		 
    		 //회원가입버튼 비활성화
    		 $("#btn_register").attr("disabled",true);//비활성화
    		 
    		 return;
    	 }
 		if( email = email.trim()){
   		 
   		 $("#email_message").html("공백이있어요")
   		                 .css("color","red");
   		 
   		 //회원가입버튼 비활성화
   		 $("#btn_register").attr("disabled",true);//비활성화
   		 
   		 return;
   	 }//db에 소문자로 저장
 		if( email = email.toLowerCase()){
 	   		 
 	   		 $("#email_message").html("공백이있어요")
 	   		                 .css("color","red");
 	   		 
 	   		 //회원가입버튼 비활성화
 	   		 $("#btn_register").attr("disabled",true);//비활성화
 	   		 
 	   		 return;
 	   	 }
    	 
    	 
    	 //서버 : 아이디 중복체크
    	 $.ajax({
    		 
    		 url		:	"check_id.do",  		//MemberCheckIdAction
    		 data		:	{"email" : email },	//parameter=>check_id.do?mem_id=hong
    		 dataType	:	"json",
    		 success	:	function(res_data){
    			 //res_data = {"result" : true } or {"result" : false } 
    			 if(res_data.result==true){
    				
    				 $("#email_message").html("사용가능한 이메일 입니다")
                                     .css("color","blue");
    				 
    				 //회원가입버튼 활성화
    				 $("#btn_register").attr("disabled",false);//활성화
    				 
    			 }else{
    				 
    				 $("#email_message").html("이미 사용중인 이메일 입니다")
                                     .css("color","red");
    				 //회원가입버튼 비활성화
    				 $("#btn_register").attr("disabled",true);//비활성화
    			 }
    			 
    			 
    		 },
    		 error		:	function(err){
    			 
    			 alert(err.responseText);
    			 
    		 }
    	 });
    	 
     }//end:check_id()
     
     
     function send(f){
	 
	 //입력항목 체크(이름/비번/우편번호/주소)
	 var name 		= f.name.value.trim();
	 var nickname 	= f.nickname.value.trim();
	 var password 	= f.password.value.trim();
	 var addr 		= f.addr.value.trim();
	 
	 if(name==''){
		 alert('이름을 입력하세요!!');
		 f.name.value='';
		 f.name.focus();
		 return;
	 }
	 
	 if(passwodd==''){
		 alert('비밀번호를 입력하세요!!');
		 f.passwodd.value='';
		 f.passwodd.focus();
		 return;
	 }
	 
	 if(addr==''){
		 alert('주소를 입력하세요!!');
		 f.addr.value='';
		 f.addr.focus();
		 return;
	 }
	 
	 if(nickname==''){
		 alert('닉네임 입력하세요!!');
		 f.nickname.value='';
		 f.nickname.focus();
		 return;
	 }
	 
	 
	 
	 
	 f.action = "insert.do";//MemberInsertAction
	 f.submit(); //전송(제출)
	 
 }
     
     function changePhone1(){
         const phone1 = document.getElementById("phone1").value
         if(phone1.length === 3) {
             document.getElementById("phone2").focus()
         }
     }

     function changePhone2(){
         const phone2 = document.getElementById("phone2").value
         if(phone2.length === 4) {
             document.getElementById("phone3").focus()
         }
     }

     function changePhone3(){
         const phone1 = document.getElementById("phone1").value
         const phone2 = document.getElementById("phone2").value
         const phone3 = document.getElementById("phone3").value
         if(phone1.length === 3 && phone2.length === 4 && phone3.length === 4){
             document.getElementById("token__button").style = "background-color: #FFFFFF; color: #0068FF; cursor: pointer;"
             document.getElementById("token__button").removeAttribute("disabled")
         }

     }

     function getToken(){
         const token = String(Math.floor(Math.random() * 1000000)).padStart(6, "0")
         document.getElementById("token").innerText = token

         document.getElementById("token__button").style = "background-color: #FFFFFF; cursor: default;"
         document.getElementById("token__button").setAttribute("disabled", "true")
         document.getElementById("token__timer__confirm__button").style="background-color: #0068FF; color: #FFFFFF; cursor: pointer;"
         document.getElementById("token__timer__confirm__button").removeAttribute("disabled")
         getTokenTimer()
     }

     let interval;
     function getTokenTimer(){
         let timer = 10
         interval = setInterval(() => {
             if(timer >= 0){
                 const minutes = Math.floor(timer / 60)
                 const seconds = timer % 60

                 document.getElementById("token__timer").innerText = minutes + ":" + String(seconds).padStart(2, "0")
                 timer -= 1
             } else {
                 document.getElementById("token").innerText = "000000"
                 document.getElementById("token__button").style = ""
                 document.getElementById("token__button").setAttribute("disabled", "true")
                 
                 document.getElementById("token__timer").innerText = "3:00"
                 document.getElementById("token__timer__confirm__button").style = ""
                 document.getElementById("token__timer__confirm__button").setAttribute("disabled", "true")
                 
                 clearInterval(interval)
             }
         }, 1000)
     }

     function getTokenTimerConfirm(){
         clearInterval(interval)
         document.getElementById("token__timer__confirm__button").style = "background-color: #FFFFFF; cursor: default;"
         document.getElementById("token__timer__confirm__button").setAttribute("disabled", "true")
         document.getElementById("token__timer__confirm__button").innerText = "인증완료"
         alert("인증이 완료되었습니다.")

         document.getElementById("signup__button").style = "background-color: #FFFFFF; color: #0068FF; border: 1px solid #0068FF ;cursor: pointer;"
         document.getElementById("signup__button").removeAttribute("disabled")
     }

     function signup(){
         const email = document.getElementById("email").value
         const name = document.getElementById("name").value
         const password1 = document.getElementById("password1").value
         const password2 = document.getElementById("password2").value
         const location = document.getElementById("location").value
         const genderWoman = document.getElementById("gender__woman").checked
         const genderMan = document.getElementById("gender__man").checked

         let isValid = true
         if(email.includes("@") === false) {
             document.getElementById("error__email").innerText = "이메일이 올바르지 않습니다."
             isValid = false
         } else {
             document.getElementById("error__email").innerText = ""
         }

         if(name === "") {
             document.getElementById("error__name").innerText = "이름이 올바르지 않습니다."
             isValid = false
         } else {
             document.getElementById("error__name").innerText = ""
         }

         if(password1 === ""){
             document.getElementById("error__password1").innerText = "비밀번호를 입력해 주세요."
             isValid = false
         } else {
             document.getElementById("error__password1").innerText = ""
         }

         if(password2 === ""){
             document.getElementById("error__password2").innerText = "비밀번호를 입력해 주세요."
             isValid = false
         } else {
             document.getElementById("error__password2").innerText = ""
         }

         if(password1 !== password2) {
             document.getElementById("error__password1").innerText = "비밀번호가 일치하지 않습니다."
             document.getElementById("error__password2").innerText = "비밀번호가 일치하지 않습니다."
             isValid = false
         }

         if(location !== "서울" && location !== "경기" && location !== "인천"){
             document.getElementById("error__location").innerText = "지역을 선택해 주세요."
             isValid = false
         } else {
             document.getElementById("error__location").innerText = ""
         }

         if(genderWoman === false && genderMan === false){
             document.getElementById("error__gender").innerText = "성별을 선택해 주세요."
             isValid = false
         } else {
             document.getElementById("error__gender").innerText = ""
         }

         if(isValid === true){
             alert("가입을 축하합니다.")
         }
     }
     
	 
	 </script>
     
</head>

<body>
    <div class="wrapper">
        <h3>회원가입</h3>
        <input class="inputbox" id="email" type="text" placeholder="이메일을 입력해 주세요."/>
        <div class="error" id="error__email"></div>
        
        <input class="inputbox" id="writer" type="text" placeholder="이름을 입력해 주세요."/>
        <div class="error" id="error__writer"></div>
        
        <input class="inputbox" id="password1" type="password" placeholder="비밀번호를 입력해 주세요."/>
        <div class="error" id="error__password1"></div>
        
        <input class="inputbox" id="password2" type="password" placeholder="비밀번호를 다시 입력해 주세요."/>
        <div class="error" id="error__password2"></div>
        
        <div class="phone__wrapper">
            <input id="phone1" class="phoneNum" type="text" onchange="changePhone1()" onkeyup="onchange()" /> - 
            <input id="phone2" class="phoneNum" onchange="changePhone2()" onkeyup="onchange()" /> -
            <input id="phone3" class="phoneNum" onchange="changePhone3()" onkeyup="onchange()" />
        </div>
        
        <div class="token__wrapper">
            <div class="token" id="token">000000</div>
            <button id="token__button" onclick="getToken()" disabled>인증번호 전송</button>
        </div>
        
        <div class="token__wrapper">
            <div class="token__timer" id="token__timer">3:00</div>
            <button id="token__timer__confirm__button" onclick="getTokenTimerConfirm()" disabled>인증 완료</button>
        </div>
        
        <div class="location__wrapper">
            <select class="location" id="location">
                <option disabled selected>지역을 선택하세요.</option>
                <option>서울</option>
                <option>경기</option>
                <option>인천</option>
            </select>
        </div>
        
        <div class="error" id="error__location"></div>
        <div class="gender__wrapper">
            <div class="gender">
                <input type="radio" name="gender" id="gender__woman" class="gender__radio"> 여성
            </div>
            <div class="gender">
                <input type="radio" name="gender" id="gender__man" class="gender__radio"/> 남성
            </div>
        </div>
        
        <div class="error" id="error__gender"></div>
        <hr />
        <div class="footer">
            <button id="signup__button" onclick="location.href='list.do'" disabled >가입하기</button>
        </div>
    </div>
</body>


</html>