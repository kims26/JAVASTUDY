	<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
	    
	    
	    
	<!DOCTYPE html>
	<html lang="ko">
	<head>
	    <title> 회원가입</title>
	    <link href="css/test.css" rel="stylesheet" />
	    <script src="js/test.js" ></script>
	    
	    
	    
	    <script type="text/javascript">
		
		 function check_id(){
			 //           document.getElementById("mem_id").value 
			 var mem_id = $("#mem_id").val();
			 
			 if(mem_id.length<3){
				 
				 $("#id_message").html("아이디는 3자리이상 입력")
				                 .css("color","red");
				 
				 //회원가입버튼 비활성화
				 $("#btn_register").attr("disabled",true);//비활성화
				 
				 return;
			 }
			 
			 
			 //서버 : 아이디 중복체크
			 $.ajax({
				 
				 url		:	"check_id.do",  		//MemberCheckIdAction
				 data		:	{"mem_id" : mem_id },	//parameter=>check_id.do?mem_id=hong
				 dataType	:	"json",
				 success	:	function(res_data){
					 //res_data = {"result" : true } or {"result" : false } 
					 if(res_data.result==true){
						
						 $("#id_message").html("사용가능한 아이디 입니다")
		                                 .css("color","blue");
						 
						 //회원가입버튼 활성화
						 $("#btn_register").attr("disabled",false);//활성화
						 
					 }else{
						 
						 $("#id_message").html("이미 사용중인 아이디 입니다")
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
		 
		 
		 
		 function find_addr(){
			 
			 new daum.Postcode({
			        oncomplete: function(data) {
			            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
			            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
			            
			            // data = {"zonecode": "12345" , "address":"서울시 관악구" ,...}
			            
			            $("#mem_zipcode").val(data.zonecode);
			            $("#mem_addr").val(data.address);
			            
			            
			        }
			    }).open();
			 
		 }//end:find_addr()
		 
		 
		 
		 function send(f){
			 
			 //입력항목 체크(이름/비번/우편번호/주소)
			 var mem_name 		= f.mem_name.value.trim();
			 var mem_pwd 		= f.mem_pwd.value.trim();
			 var mem_zipcode 	= f.mem_zipcode.value.trim();
			 var mem_addr 		= f.mem_addr.value.trim();
			 
			 if(mem_name==''){
				 alert('이름을 입력하세요!!');
				 f.mem_name.value='';
				 f.mem_name.focus();
				 return;
			 }
			 
			 if(mem_pwd==''){
				 alert('비밀번호를 입력하세요!!');
				 f.mem_pwd.value='';
				 f.mem_pwd.focus();
				 return;
			 }
			 
			 if(mem_zipcode==''){
				 alert('우편번호를 입력하세요!!');
				 f.mem_zipcode.value='';
				 f.mem_zipcode.focus();
				 return;
			 }
			 
			 if(mem_addr==''){
				 alert('주소를 입력하세요!!');
				 f.mem_addr.value='';
				 f.mem_addr.focus();
				 return;
			 }
			 
			 
			 
			 
			 f.action = "insert.do";//MemberInsertAction
			 f.submit(); //전송(제출)
			 
		 }
	
		</script>
	</head>
	<body>
	<form action="">
	
	
	
	    <div class="wrapper">
	        <h3>회원가입</h3>
	        <input class="inputbox" id="mem_id" type="text" placeholder="아이디을 입력해 주세요." name="mem_id">
	        <div class="error" id="error__email"></div>
	        
	        <input class="inputbox" id="writer" type="text" placeholder="이름을 입력해 주세요." name="mem_name">
	        <div class="error" id="error__writer"></div>
	        
	        <input class="inputbox" id="mem_pwd" type="password" placeholder="비밀번호를 입력해 주세요." name="mem_pwd">
	        <div class="error" id="error__password1"></div>
	        
	        <input class="inputbox" id="mem_pwd2" type="password" placeholder="비밀번호를 다시 입력해 주세요." name="mem_pwd2">
	        
	        <div class="error" id="error__mem_pwd2"></div>
	        
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
		            <button id="signup__button"  onclick="location.href='list.do'" disabled>가입하기</button>
		        </div>

	            <div class="footer">
	            <button id="signup__button"  onclick="location.href='list.do'" >목록보기</button>
	        </div>
	    </div>
	    </form>
	</body>
	</html>