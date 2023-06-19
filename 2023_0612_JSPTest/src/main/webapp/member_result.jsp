<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   //Scriptlet Code영역 : _jspService(request,response){}
   //Java Code작성
   
   // JSP내장객체 : request,response,session,application
   //               out
   
   
   //POST방식에서는 한글이 깨짐
	//수신인코딩 설정 : 전송측 인코딩과 일치시켜야 함
	request.setCharacterEncoding("utf-8");
	
	//parameter받기
	String name		= request.getParameter("name");
	//System.out.println(name);
	String id		= request.getParameter("id");
	String pwd		= request.getParameter("pwd");
	String gender	= request.getParameter("gender");
	String blood	= request.getParameter("blood");
	String profile	= request.getParameter("profile");
	
	//배열로 수신 : 넘어오는 변수명이 동일한것이 복수개
	//취미
	String [] hobby_array = request.getParameterValues("hobby");
	//클라이언트측 : checkbox(선택된정보만 넘어온다)
	String hobby_list = "취미없음";
	
	if(hobby_array != null) {// 1개이상이 선택된 정보가 있으면
		//hobby=독서&hobby=낚시
		//String []hobby_array = {"독서","낚시"}
		StringBuffer sb1 = new StringBuffer();
		for(String hobby : hobby_array) {
			
			sb1.append(hobby);
			sb1.append(" ");
		}
		hobby_list = sb1.toString().trim();
	}
	
			
	//친구
	String [] friend_array= request.getParameterValues("friend");
	//String [] friend_array = {"","","",""};
	StringBuffer sb2 = new StringBuffer(); 
	for(String friend: friend_array) {
		  sb2.append(friend);
		  sb2.append(" ");
	}
	
	String friend_list = sb2.toString().trim();
	
	if(friend_list.isEmpty())  // ""
        friend_list = "친구없음";

%>    
   
<!-- 윗쪽 
     Business Logic : Data처리영역
 -->

<!-- 아랫쪽 
     Presentation Logic : UI(화면구성) View영역
-->   
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border='1' align='center' width='400' >
	   <tr>
	      <th>이름</th>
	      <td><%= name %></td>
	   </tr>
	   <tr>
	      <th>아이디</th>
	      <td><%= id %></td>
	   </tr>
	   <tr>
	      <th>비밀번호</th>
	      <td><%= pwd %></td>
	   </tr>
	   
	   <tr>
	      <th>성별</th>
	      <td><%= gender %></td>
	   </tr>
	   
	   <tr>
	      <th>혈액형</th>
	      <td><%= blood %></td>
	   </tr>
	   
	   <tr>
	      <th>취미</th>
	      <td><%= hobby_list %></td>
	   </tr>
	   
	   <tr>
	      <th>친구</th>
	      <td><%= friend_list %></td>
	   </tr>
	   
	   <tr>
	      <th>자기소개</th>
	      <td><%= profile %></td>
	   </tr>
	   
	   <tr><td colspan="2" align="center">
	      <a href='member_input.html'>다시하기</a>
	   </td></tr>
	</table>
</body>
</html>







