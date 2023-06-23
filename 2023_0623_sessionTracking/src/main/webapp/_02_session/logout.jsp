<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 

//로그인 처리 : session에 user장보를 저장
session.removeAttribute("user");


//메인페이지로 이동
response.sendRedirect("main_photo.jsp");



%>