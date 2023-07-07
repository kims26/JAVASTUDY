<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
  
  table{
     width: 500px;
  }
 
</style>

</head>
<body>
 <table border="1" >
   <tr>
      <th>전체이름</th>
      <td>${ fullName }</td>
   </tr>
   <tr>
      <th>성</th>
      <td>${ familyName }</td>
   </tr>
   <tr>
      <th>이름</th>
      <td>${ givenName }</td>
   </tr>
   <tr>
      <th>나이</th>
      <td>${ age }</td>
   </tr>
   <tr>
      <th>주소</th>
      <td>${ addr }</td>
   </tr>
   <tr>
      <th>취미</th>
      <td>${ hobby_list }</td>
   </tr>
 </table>
</body>
</html>




