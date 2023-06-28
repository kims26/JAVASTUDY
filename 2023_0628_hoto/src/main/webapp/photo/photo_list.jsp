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
     width: 700px;
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
  
  #photo_box{
     height: 450px;
     border: 2px solid blue;
     overflow-y: scroll;
    
  }
  
  .photo{
     float: left;
     margin: 15px;
     margin-top: 30px;
     padding: 5px;
     height: 170px;
     border: 1px solid #cccccc;
  }
  
  .photo:hover{
    border-color: red; 
  }
  
  .photo > img{
     width: 120px;
     height: 120px;
     border: 1px  solid #cccccc;
     outline: 1px solid #333333;
  }
  
  .photo > p{
     margin-top: 5px;
     border: 1px solid #cccccc;
     padding: 5px;
  }
  
  #empty_message{
     font-size: 20px;
     font-weight: bold;
     text-align: center;
     margin-top: 200px;
     color: red;
  }
</style>

<script type="text/javascript">


function photo_insert(){
	
	
	//로그인이 안된경우 
	if("${empty user}"=="true"){
		
		if(confirm("사진등록은 로그인 후 이용가능합니다 \n로그인 해라")==false) return;
		
		location.href="../member/login_form.do";
		
		return;
		
	}
	
	
	//사진등록폼으로 이동
	location.href="insert_form.do";
	
}


</script>


</head>
<body>
 
  <div id="box">
      <h1 id="title">::::PhotoGallery::::</h1>
      <div style="text-align: right;">
         <!-- 로그인 안된경우 -->
         <c:if test="${ empty user }">
             <input  class="btn btn-primary" type="button" value="로그인" 
                     onclick="location.href='../member/login_form.do'">
         </c:if>
         
         <!-- 로그인 된 경우 -->
         <c:if test="${ not empty user }">
             <b>${ user.mem_name }</b>님 환영합니다
             <input class="btn btn-primary" type="button" value="로그아웃"  
                     onclick="location.href='../member/logout.do'">
         </c:if>
         
      </div>
      <div style="margin-bottom: 5px;">
         <input class="btn btn-primary"  type="button" value="사진등록"
         onclick="photo_insert();" >
      </div>
      
      <div id="photo_box">
      
         <!-- Data가 없는경우 -->
         <c:if test="${ empty list }">
             <div id="empty_message">사진이 없습니다</div>
         </c:if>
      
      
         <%-- <c:forEach begin="1"  end="20"> --%>
         
         <c:forEach var="vo"  items="${ list }">
           
           <div class="photo">
              <img src="../upload/back.png">
              <p>제목</p>
           </div>
           
           
         </c:forEach>
               
      
      </div>
      
  </div>
 
</body>
</html>




