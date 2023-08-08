<%@ page language='java' contentType='text/html;charset=UTF-8'  pageEncoding='UTF-8' %>

<%@ taglib prefix='c'    uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt'  uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix='fn'   uri='http://java.sun.com/jsp/jstl/functions' %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <!-- Bootstrap3.x 설정 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <style>
        #box{
            width: 700px;
            margin: auto;
            margin-top: 50px;
        }

        .mystyle{
            border-radius: 3px;
            border: 1px solid rgb(225, 222, 222);
            padding: 5px;
            margin-bottom: 10px;
        }

        #content{
            min-height: 120px;
        }



    </style>


    <script>

      function del(b_idx){

        if(confirm("정말 삭제하시겠습니까?")==false)return;

        location.href="delete.do?b_idx=" + b_idx; 

      }

    </script> 


</head>
<body>
    <div id="box">
       
        <div class="panel panel-primary">
          <div class="panel-heading"><h4>${ vo.mem_name }님의 글:</h4></div>
          <div class="panel-body">
              <!-- 제목 -->
              <label>[제목]</label>
              <div class="mystyle">
                <label>${ vo.b_subject }</label>
              </div>

              <!-- 내용 -->
              <label>[내용]</label>
              <div class="mystyle" id="content">
                <label>${ vo.b_content }</label>
              </div>

              <!-- 작성일자/IP -->
              <label>[작성일자(IP)]</label>
              <div class="mystyle">
                <label>${ fn:substring(vo.b_regdate,0,16) }(${ vo.b_ip})</label>
              </div>

              <!-- 작업버튼 -->
              <input  class="btn btn-primary" type="button" value="목록보기"
                      onclick="location.href='list.do'">
              
              <!-- 로그인상태 및 메인글에서만 사용  -->
              <c:if test="${ (not empty user) and ( vo.b_depth eq 0 ) }">
                 <input  class="btn btn-success" type="button" value="답글쓰기"
                         onclick="location.href='reply_form.do?b_idx=${ vo.b_idx }'">
              </c:if>

              <!-- 글쓴이인 경우만 활성화 -->
              <c:if test="${ user.mem_idx eq vo.mem_idx }">
                <input  class="btn btn-info"    type="button" value="수정하기"
                        onclick="location.href='modify_form.do?b_idx=${ vo.b_idx }'"   >
                <input  class="btn btn-danger"  type="button" value="삭제하기"
                        onclick="del('${ vo.b_idx }');">
              </c:if>

          </div>
       </div>

    </div>
</body>
</html>