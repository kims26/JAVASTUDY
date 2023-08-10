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

        /* 댓글창 */
        #comment_content{
          width: 80%;
          height:80px;
          resize: none;
          padding: 5px;
          float: left;
        }

        #comment_btn{
          width: 18%;
          height: 80px;
          float: left;
          margin-left: 10px;
        }


    </style>


    <script>

      function del(b_idx){

        if(confirm("정말 삭제하시겠습니까?")==false)return;

        location.href="delete.do?b_idx=" + b_idx +"&page=${ param.page }&search=${param.search}&search_text=${ param.search_text }"; 

      }

      // 댓글작성
      let global_comment_page = 1;

      function comment_insert(){
          //로그인여부체크
          if("${ empty user }" =="true"){
            
            if(confirm("댓글쓰기는 로그인후 가능합니다\n로그인 하시겠습니까?")==false)return;

            //로그인폼으로 이동
            location.href="../member/login_form.do?url=" 
                          + encodeURIComponent(location.href);
            return;
          }

          let comment_content = $("#comment_content").val().trim();

          if(comment_content==""){
              alert("댓글내용을 작성하세요!!");
              $("#comment_content").val("");
              $("#comment_content").focus();
              return;
          }

          //댓글쓰기
          $.ajax({

            url   : "comment_insert.do",
            data  : {
                       "b_idx"          : "${ vo.b_idx }",
                       "comment_content": comment_content,
                       "mem_idx"        : "${ user.mem_idx }",
                       "mem_id"         : "${ user.mem_id }",
                       "mem_name"       : "${ user.mem_name }"
                    },
            dataType : "json",
            success  : function(res_data){
               //res_data = {"result" : "success" }
               //res_data = {"result" : "fail" }
               if(res_data.result=="success"){
                  
                  //이전 댓글내용삭제
                  $("#comment_content").val("");
                  //댓글목록을 재요청
                  comment_list(1);
               }

            },
            error    : function(err){
               alert(err.responseText);
            }
          });
      }

      //댓글조회
      function comment_list(comment_page){

         $.ajax({
            url   : "comment_list.do",  // comment_list.do?b_idx=5&page=1
            data  : {
                      "b_idx":"${ vo.b_idx }",
                      "page" : comment_page
                    },
            success: function(res_data){
              
               //댓글영역 넣어준다
               $("#comment_display").html(res_data);

            },
            error  : function(err){

               alert(err.responseText);

            }        

         });
      }

    </script> 


    <script>
        
        //현재 html문서배치완료되면 댓글목록 가져와서 출력
        $(document).ready(function(){
           
          comment_list(1);

        });

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
                      onclick="location.href='list.do?page=${ param.page }&search=${ param.search }&search_text=${ param.search_text }'">
              
              <!-- 로그인상태 및 메인글에서만 + 검색조건이 all일때 사용  -->
              <c:if test="${ (not empty user) and ( vo.b_depth eq 0 ) and ( param.search eq 'all' )  }">
                 <input  class="btn btn-success" type="button" value="답글쓰기"
                         onclick="location.href='reply_form.do?b_idx=${ vo.b_idx }&page=${ param.page }'">
              </c:if>

              <!-- 글쓴이인 경우만 활성화 -->
              <c:if test="${ user.mem_idx eq vo.mem_idx }">
                <input  class="btn btn-info"    type="button" value="수정하기"
                        onclick="location.href='modify_form.do?b_idx=${ vo.b_idx }&page=${ param.page }&search=${ param.search }&search_text=${ param.search_text }'"   >
                <input  class="btn btn-danger"  type="button" value="삭제하기"
                        onclick="del('${ vo.b_idx }');">
              </c:if>

          </div>
       </div>

       <!-- 댓글입력창 -->
       <div>
           <textarea id="comment_content" 
                     placeholder="댓글은 로그인후에 작성가능합니다"></textarea>   
           <input    id="comment_btn" type="button" value="댓글쓰기"
                     onclick="comment_insert();"   >
       </div>
 
       <hr style="clear:both;">
       <!-- 댓글출력영역 -->
       <div id="comment_display"></div>


    </div>
</body>
</html>