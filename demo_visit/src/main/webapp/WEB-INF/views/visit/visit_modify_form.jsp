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
     width: 500px;
     margin: auto;
     margin-top: 50px;
  }
  
  textarea {
	 resize: none;
  }

</style>


<script type="text/javascript">

  function send(f){
	  
	  //입력값 체크
	  var name    = f.name.value.trim();
	  var content = f.content.value.trim();
	  var pwd     = f.pwd.value.trim();
	  
	  if(name==''){
		  alert('작성자 이름을 입력하세요');
		  f.name.value='';
		  f.name.focus();
		  return;
	  }
	  
	  if(content==''){
		  alert('내용을 입력하세요');
		  f.content.value='';
		  f.content.focus();
		  return;
	  }
	  
	  if(pwd==''){
		  alert('비밀번호를 입력하세요');
		  f.pwd.value='';
		  f.pwd.focus();
		  return;
	  }
	  
	  //서버로 전송
	  f.action = "modify.do";// VisitModifyAction
	  f.submit();// 전송(제출)
	  
	  
  }

</script>


</head>
<body>
<form>

  <input type="hidden"  name="idx"  value="${ vo.idx }">

  <div id="box">
    <div class="panel panel-primary">
      <div class="panel-heading">방명록 수정하기</div>
      <div class="panel-body">
         
         <div>
            <label>작성자 : </label>
            <input class="form-control" name="name" value="${ vo.name }">
         </div>
         
         <div>
            <label>내  용 : </label>
            <textarea class="form-control" rows="5" name="content">${ vo.content }</textarea>
         </div>
         
         <div>
            <label>비밀번호 : </label>
            <input class="form-control" name="pwd" type="password" value="${ vo.pwd }">
         </div>
         
         <div style="margin-top: 10px; text-align: center">
            <input class="btn btn-primary" type="button"  value="수정하기"
                   onclick="send(this.form);">
                   
            <input class="btn btn-success" type="button"  value="목록보기"
                   onclick="location.href='list.do'">
         </div>
      
      </div>
    </div>
  </div>
</form>
  
</body>
</html>