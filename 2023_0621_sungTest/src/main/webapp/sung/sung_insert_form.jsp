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

<style type="text/css">
  #box{
     width: 400px;
     margin: auto;
     margin-top: 100px;
  }
  
</style>  
 
 
<script type="text/javascript">
  
  //0~100점사이값 체크 정규식
  var regular_jumsu = /(^[0-9]{1,2}$|100)/;
  
  function send(f){
	  
	  var name = f.name.value.trim();
	              
	  var kor  = f.kor.value;
	  var eng  = f.eng.value;
	  var mat  = f.mat.value;
	  
	  if(name==''){
		  alert('이름을 입력하세요!!!');
		  f.name.value='';
		  f.name.focus();
		  return;
	  }
	  
	  
	  if(regular_jumsu.test(kor)==false){
		  
		  alert('국어점수는 0~100사이의 숫자만 입력하세요!!!');
		  f.kor.value='';
		  f.kor.focus();
		  return;
	  }
	  
	  if(regular_jumsu.test(eng)==false){
		  
		  alert('영어점수는 0~100사이의 숫자만 입력하세요!!!');
		  f.eng.value='';
		  f.eng.focus();
		  return;
	  }
	  
	  if(regular_jumsu.test(mat)==false){
		  
		  alert('수학점수는 0~100사이의 숫자만 입력하세요!!!');
		  f.mat.value='';
		  f.mat.focus();
		  return;
	  }
	  
	  
	  f.action = "insert.do"; //SungInsertAction
	  f.submit(); //전송(제출)
	  
	
  }

</script> 
 
 
  
</head>
<body>

<form>

	<div id="box">
		<div class="panel panel-primary">
	      <div class="panel-heading"><h4>성적입력폼</h4></div>
	      <div class="panel-body">
	         <table class="table">
	             
	             <tr>
	                <th>이름</th>
	                <td><input class="form-control" name="name"></td>
	             </tr>
	             
	             <tr>
	                <th>국어</th>
	                <td><input class="form-control" name="kor"></td>
	             </tr>
	             
	             <tr>
	                <th>영어</th>
	                <td><input class="form-control"  name="eng"></td>
	             </tr>
	              
	             <tr>
	                <th>수학</th>
	                <td><input class="form-control" name="mat"></td>
	             </tr>
	             
	             <tr>
	                <td colspan="2" align="center">
	                    
	                    <input class="btn btn-primary" type="button"  value="등록하기"
	                           onclick="send(this.form);">
	                    <input class="btn btn-success" type="button"  value="목록보기"
	                           onclick="location.href='list.do'">
	                
	                </td>
	             </tr>
	             

	              
	         </table>
	      </div>
	    </div>
	</div>

</form>

</body>
</html>




