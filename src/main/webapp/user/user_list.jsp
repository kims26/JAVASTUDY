<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Welcome to my shop</title>
   <link href="../css/index.css?after" rel="stylesheet" />
   
   
   <!-- Bootstrap3.x 설정 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>

<body>
<div id="box">

   
   <!-- 로그인/로그아웃  -->
   <div style="text-align: right;">
       <!-- 로그인 안된경우 -->
       <c:if test="${ empty sessionScope.user }">
          <div class="writeBtnBox">
          <button class="writeBtn" onclick="location.href='login_form.do'">로그인 <span class="arrow">></span></button>
        </div>
       </c:if>
       
       <!-- 로그인 된경우 -->
       <c:if test="${ not empty sessionScope.user }">
           <b>${ user.name }</b>님 환영합니다
          <div class="writeBtnBox">
          <button class="writeBtn" onclick="location.href='logout.do'">로그아웃 <span class="arrow">></span></button>
        </div>
       </c:if>
   </div>
   
   </div>


  <section class="mainBanner">
    <h1 class="text">
     멍 친구
    </h1>
  </section>
  <div class="container">
    <section class="wrapper">
      <div class="sidebar">
        <nav class="mainMenu">
          <ul>
            <li><a href="">New Items</a></li>
            <li><a href="">Best Items</a></li>
            <li><a href="">유기견 목록</a></li>
            <li><a href="">강아지용품 마켓</a></li>
          </ul>
        </nav>
        <div class="writeBtnBox">
          <button class="writeBtn">유기견 등록 <span class="arrow">></span></button>
        </div>
        
        <div class="writeBtnBox">
          <button class="writeBtn" onclick="location.href='insert_form.do'">회원가입 <span class="arrow">></span></button>
        </div>
        
         <div class="writeBtnBox">
          <button class="writeBtn" onclick="location.href='login_form.do'">로그인 <span class="arrow">></span></button>
        </div>
          
      </div>
      <div class="itemWrap">
        <div class="item">
          <div class="imgBox">
            <img src="../img/item10.jpg" alt="별이">
          </div>
          <div class="textBox">
            <p class="textBox__name">별이</p>
            <p class="textBox__price">암컷</p>
          </div>
        </div>
        <div class="item">
          <div class="imgBox">
            <img src="../img/item20.jpg" alt="짱구">
          </div>
          <div class="textBox">
            <p class="textBox__name">짱구</p>
            <p class="textBox__price">수컷</p>
          </div>
        </div>
        <div class="item">
          <div class="imgBox">
            <img src="../img/item40.jpg" alt="로로">
          </div>
          <div class="textBox">
            <p class="textBox__name">로로</p>
            <p class="textBox__price">암컷</p>
          </div>
        </div>
        <div class="item">
          <div class="imgBox">
            <img src="../img/item20.jpg" alt="짱구">
          </div>
          <div class="textBox">
            <p class="textBox__name">짱구</p>
            <p class="textBox__price">암컷</p>
          </div>
        </div>
        <div class="item">
          <div class="imgBox">
            <img src="../img/item30.jpg" alt="미니">
          </div>
          <div class="textBox">
            <p class="textBox__name">미니</p>
            <p class="textBox__price">수컷</p>
          </div>
        </div>
        <div class="item">
          <div class="imgBox">
            <img src="../img/item50.jpg" alt="초코">
          </div>
          <div class="textBox">
            <p class="textBox__name">초코</p>
            <p class="textBox__price">암컷</p>
          </div>
        </div>
        <div class="item">
          <div class="imgBox">
            <img src="../img/item20.jpg" alt="홍길동">
          </div>
          <div class="textBox">
            <p class="textBox__name">홍길동</p>
            <p class="textBox__price">수컷</p>
          </div>
        </div>
        <div class="item">
          <div class="imgBox">
            <img src="../img/item10.jpg" alt="머그">
          </div>
          <div class="textBox">
            <p class="textBox__name">머그</p>
            <p class="textBox__price">암컷</p>
          </div>
        </div>
        <div class="item">
          <div class="imgBox">
            <img src="../img/item60.jpg" alt="거실">
          </div>
          <div class="textBox">
            <p class="textBox__name">거실</p>
            <p class="textBox__price">수컷</p>
          </div>
        </div>
        <div class="item">
          <div class="imgBox">
            <img src="../img/item100.jpg" alt="맥">
          </div>
          <div class="textBox">
            <p class="textBox__name">맥</p>
            <p class="textBox__price">암컷</p>
          </div>
        </div>
        <div class="item">
          <div class="imgBox">
            <img src="../img/item100.jpg" alt="컵">
          </div>
          <div class="textBox">
            <p class="textBox__name">컵</p>
            <p class="textBox__price">수컷</p>
          </div>
        </div>
        <div class="item">
          <div class="imgBox">
            <img src="../img/item80.jpg" alt="파리">
          </div>
          <div class="textBox">
            <p class="textBox__name">파리</p>
            <p class="textBox__price">수컷</p>
          </div>
        </div>
      </div>
    </section>
  </div>

</body>

</html>