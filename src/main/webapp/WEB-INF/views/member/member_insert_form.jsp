<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  
  #id_message{
     display: inline-block;
     width: 200px;
     color : red;
     margin-left: 5px;
  }
  
  .seperator{
    margin-bottom: 10px;
  }

</style>

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
<form class="form-inline">
  <div id="box">
  	<div class="panel panel-primary">
      <div class="panel-heading"><h4>회원가입</h4></div>
      <div class="panel-body">
        
	        <div class="seperator">
	           <label>이름 : </label>
	           <input class="form-control" name="mem_name">
	        </div>
	        
	        <div class="seperator">
	           <label>아이디 : </label>
	           <input class="form-control" name="mem_id"  id="mem_id"
	                  onkeyup="check_id();"> 
	           <span id="id_message"></span> 
	        </div>
	        
	        <div class="seperator">
	           <label>비밀번호 : </label>
	           <input class="form-control" type="password" name="mem_pwd">
	        </div>
	        
	        <div class="seperator">
	           <label>우편번호 : </label>
	           <input class="form-control" 
	                  name="mem_zipcode" id="mem_zipcode" >
	           <input class="btn  btn-info" type="button"  value="검색"
	                  onclick="find_addr();" >
	        </div>
	        
	        <div class="seperator">
	           <label>주소 : </label>
	           <input class="form-control" name="mem_addr" id="mem_addr" size="50">
	        </div>
	        
	        <hr>
	        
	        <div style="text-align: center">
	           <input class="btn  btn-primary" type="button" value="회원가입"
	                  id="btn_register"  disabled="disabled"
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