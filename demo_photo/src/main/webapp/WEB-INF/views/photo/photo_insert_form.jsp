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
  
  .style1{
     margin-bottom: 20px;
  }
  
  textarea {
	 resize: none;
  }
  
  
</style>
  
<script type="text/javascript">
  function send(f){
	  
	  //입력값체크(제목/내용/사진)
	  var p_subject	= f.p_subject.value.trim();
	  var p_content	= f.p_content.value.trim();
	  var photo		= f.photo.value; 
	  
	  if(p_subject==''){
		  alert('제목을 입력하세요!!');
		  f.p_subject.value='';
		  f.p_subject.focus();
		  return;
	  }
	  
	  if(p_content==''){
		  alert('내용을 입력하세요!!');
		  f.p_content.value='';
		  f.p_content.focus();
		  return;
	  }
	  
	  if(photo==''){
		  alert('사진을 선택하세요!!');
		  return;
	  }
	 
	  
	  f.action="insert.do"; //PhotoInsertAction
	  f.submit();//전송
	  
  }

</script>


</head>
<body>

<form method="POST" enctype="multipart/form-data">
  
  <input type="hidden"  name="mem_idx" value="${ user.mem_idx }">
  
  <div id="box">
    <div class="panel panel-primary">
      <div class="panel-heading"><h4>사진등록</h4></div>
      <div class="panel-body">
         <div class="style1">
            <label>제목</label>
            <input class="form-control" name="p_subject">
         </div>
         <div class="style1">
            <label>내용</label>
            <textarea class="form-control" name="p_content" rows="5" cols=""></textarea>
         </div>
         
         <div class="style1">
            <label>사진</label>
            <input class="form-control" type="file" name="photo">
         </div>
      
         <div style="text-align: center;">
              <input class="btn  btn-primary" type="button"  value="사진등록"
                     onclick="send(this.form);">
              <input class="btn  btn-success" type="button"  value="목록보기"
                     onclick="location.href='list.do'">
         </div>
      </div>
    </div>
  </div>
  
</form> 
  
</body>
</html>