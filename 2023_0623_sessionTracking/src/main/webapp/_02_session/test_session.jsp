<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    
    //JSP내장객체 : request response session applicatin
    //              out     page  ....
    


    //발급받은 세션정보 얻어온다
    HttpSession session1 = request.getSession();

    session1.setMaxInactiveInterval(60*5); //5minute

    String session_id = session1.getId();
    
    
    
    System.out.println(session_id);
    

%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
발급받는 세션 ID  : <%= session_id %><br>
JSP내에 내장된정보: <%= session.getId() %>

</body>
</html>