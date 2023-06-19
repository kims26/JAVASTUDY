<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   //1.parameter받는다(body_length,body_weight)
   String str_body_length = request.getParameter("body_length");
   String str_body_weight = request.getParameter("body_weight");
   //2.실수로 변환: Double.parseDouble("180.5");
   double body_length = Double.parseDouble(str_body_length)/100.0;//cm->m 
   double body_weight = Double.parseDouble(str_body_weight); 
		   
   //3.BMI계산
   double bmi = body_weight / (body_length*body_length);
   
   //4.상태
   String body_state="";
   if(bmi<18.5)
	   body_state = "저체중";
   else if(bmi>=18.5 && bmi<23.0)
	   body_state = "정상";
   else if(bmi>=23.0 && bmi<25.0)
	   body_state = "과체중";
   else if(bmi>=25.0)
	   body_state = "비만";
   
   
   
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 4.계산결과 출력  -->
<table border='1' align='center'>
    <tr>
       <th>신장</th>
       <td><%= body_length*100 %></td>
    </tr>
    <tr>
       <th>체중</th>
       <td><%= body_weight %></td>
    </tr>
    <tr>
       <th>BMI</th>
       <td><%= bmi %></td>
    </tr>
    <tr>
       <th>비만도</th>
       <td><%= body_state %></td>
    </tr>
    <tr>
       <td colspan="2" align="center">
          <a href='bmi_input.html'>다시하기</a>
       </td>
    </tr>
</table>
</body>
</html>