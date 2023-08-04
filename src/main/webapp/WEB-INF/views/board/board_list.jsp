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
            width: 800px;
            margin: auto;
            margin-top: 50px;
        }

        #title{
            text-align: center; 
            font-size: 26px;
            font-weight: bold;
            text-shadow: 1px 1px 2px black;
            color: rgb(22, 81, 22);
        }

        .subject{
            width: 400px;

            /* elipsis:말줄임 ... */
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
            word-break: break-all;
        }

    </style>

    <script>

        function insert_form(){

            //로그인 체크(안된경우)
            if("${ empty sessionScope.user }"=="true"){
               
                if(confirm("글쓰기는 로그인후 가능합니다\n로그인 하시겠습니까?")==false)return;
                //현재경로 : /board/
                location.href="../member/login_form.do";

                return;
            }

            //글쓰기 폼으로 이동
            location.href="insert_form.do";

        }

    </script>

</head>
<body>
    <div id="box">
        <h1 id="title">::::게시판::::</h1>
        
        <!-- 글쓰기 -->
        <div style="position: relative; margin-top: 15px; margin-bottom: 5px;">
            <input  class="btn btn-primary" type="button" value="새글쓰기" 
                    onclick="insert_form();">


            <!-- 로그인 기능 -->
            <span style="position: absolute ; right: 0; display: inline-block;">
              
                <!-- 로그인 안된경우 -->
                <c:if test="${ empty user }">
                    <input  class="btn btn-primary" type="button" value="로그인"
                            onclick="location.href='../member/login_form.do'"  >
                </c:if>

                <!-- 로그인 된경우 -->
                <c:if test="${ not empty user }">
                    <label>${ user.mem_name}님 환영합니다</label>
                    <input  class="btn btn-primary" type="button" value="로그아웃"
                            onclick="location.href='../member/logout.do'"  >
                </c:if>

            </span>
        </div>

        <table class="table">
            <tr class="success">
                <th>번호</th>
                <th width="50%">제목</th>
                <th>작성자</th>
                <th>작성일자</th>
                <th>조회수</th>
            </tr>
            <!-- Data가 없는경우 -->
            <c:if test="${ empty list }">
                <tr>
                    <td colspan="5" align="center">
                        <font color="red">등록된 게시물이 없습니다</font>
                    </td>                      
                </tr>
            </c:if>


            <!-- Data가 있는경우 -->
            <!-- for(BoardVo vo : list ) -->
            <c:forEach var="vo" items="${ list }">
               <tr>
                  <td>${ vo.b_idx }</td>
                  <td>
                    <div class="subject">
                        <!-- 답글에 대한 처리(들여쓰기/ㄴ) -->
                        <c:forEach begin="1" end="${ vo.b_depth }">
                           &nbsp;&nbsp; 
                        </c:forEach>
                        
                        <c:if test="${ vo.b_depth ne 0 }">
                        ㄴ
                        </c:if>

                        <a href="view.do?b_idx=${ vo.b_idx }">${ vo.b_subject }</a>
                    </div>
                  </td>
                  <td>${ vo.mem_name }</td>
                  <td>${ fn:substring(vo.b_regdate,0,16) }</td>
                  <td>${ vo.b_readhit }</td>

               </tr>   
            </c:forEach>


        </table>

    </div>
</body>
</html>