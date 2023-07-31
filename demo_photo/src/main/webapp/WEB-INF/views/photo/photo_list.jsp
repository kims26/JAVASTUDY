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
     
     box-shadow: 1px 1px 3px black;
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
  
  .subject_type{
     width: 120px;
     overflow:hidden;
     text-overflow:ellipsis;
     white-space:nowrap;
  }
</style>

<script type="text/javascript">

  function photo_insert(){
	 
	  //로그인이 안된경우
	  if("${ empty user }"=="true"){
		  
		  if(confirm("사진등록은 로그인후 이용가능합니다\n로그인 하시겠습니까?")==false) return;
		  
		  location.href="../member/login_form.do";
		  
		  return;
	  }
	  
	  //사진등록폼으로 이동
	  location.href="insert_form.do";// PhotoInsertFormAction
  }//end:photo_insert()
  
  
  var global_p_idx;//전역변수
  var global_p_filename;
  function show_popup(p_idx){
	  
	  global_p_idx = p_idx;
	  
	  var window_width = $(window).width();   //browser폭
	  var popup_width  = $("#popup").width(); //popup폭
	  //alert(window_width + " / " + popup_width );
	  
	  //팝업윈도우가 중앙에 올수 있도록 left위치 계산
	  var left = window_width/2 - popup_width/2;
	  $("#popup").css("left", left);
	  $("#popup").show();
	  
	  
	  //alert(p_idx+"에 대한 자료를 Ajax통해서 요청");
	  
	  $.ajax({
		  url		:	"photo_one.do",      //PhotoOneAction
		  data		:	{"p_idx" : p_idx },
		  dataType	: "json",
		  success	: function(res_data){
			  
			  //res_data = {"p_idx":20, "p_subject": "제목" , "p_filename":"a.jpg" ,.... }
			  
			  //download할 화일명
			  global_p_filename = res_data.p_filename;
			  
			  //이미지 출력
			  //  <img src="">
			  $("#popup > img").attr("src", "../upload/" + res_data.p_filename);
			  
			  $("#subject").html(res_data.p_subject);
			  $("#content").html(res_data.p_content);
			  			  
			  var date = "최초 : " + res_data.p_regdate + 
			             "<br>수정 : " + res_data.p_modifydate;
			  $("#regdate").html(date);
			  
			  $("#mem_idx").html("회원번호:" + res_data.mem_idx);
			  
			  
			  //로그인 여부에따라서 다운로드 버튼 사용여부 결정
			  if("${ empty user }"=="true"){
				  
				  $("#btn_download").hide();
				  
			  }else{
				  
				  $("#btn_download").show();
			  }
			  
			  //수정/삭제버튼의 사용여부 결정(본인 또는 관리자일 경우)
			  if(
			     "${ (user.mem_grade eq '관리자') }"=="true" 
			     ||
			     ( "${ user.mem_idx}" == res_data.mem_idx )
					  
			    )
			  {
				  
				  $("#div_job").show();
				  
			  }else{
				  
				  $("#div_job").hide();
			  }
				  
			  
			  
			  
			  
		  },
		  error		: function(err){
			  
			  alert(err.responseText);
			  
		  }
		  
	  });
	  
  }//end:show_popup()
 
  
  function del_photo(){
	  
	  if(confirm("정말 삭제하시겠습니까?")==false)return;
	  
	  // PhotoDeleteAction
	  location.href="delete.do?p_idx=" + global_p_idx ;
	  
  }
  
  //수정폼 띄우기
  function modify_form(){
	  
	  // PhotoModifyFormAction
	  location.href="modify_form.do?p_idx=" + global_p_idx;
	  
  }
  
  function download(){
	  
	  
	  console.log('안녕' + "/" + encodeURIComponent('안녕'));
	  
	  //현재작업경로: /photo/
	  //화일다운로드 서블릿 호출   : /FileDownload.do
	  //서버측인코딩에 맞춰서 사용 : encodeURIComponent("문자","charset")	  
	  location.href="../FileDownload.do?dir=/upload/&filename=" 
			        +  encodeURIComponent(global_p_filename);
			        //+  encodeURIComponent(global_p_filename,"utf-8");
	  
	  
  }
  
  
  
</script>


</head>
<body>
 
  <!-- 상세보기 팝업 -->
  <%@include file="photo_popup.jsp" %>
 
 
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
                onclick="photo_insert();">
      </div>
      
      <div id="photo_box">
      
         <!-- Data가 없는경우 -->
         <c:if test="${ empty list }">
             <div id="empty_message">사진이 없습니다</div>
         </c:if>
      
      
         <%-- <c:forEach begin="1"  end="20"> --%>
         
         <!--  for(PhotoVo vo : list)  -->
         <c:forEach var="vo"  items="${ list }">
           
           <div class="photo">
              <img src="../upload/${ vo.p_filename }"  
                   onclick="show_popup('${ vo.p_idx }');">
              <p class="subject_type">${ vo.p_subject }</p>
           </div>
           
           
         </c:forEach>
               
      
      </div>
      
  </div>
 
</body>
</html>




