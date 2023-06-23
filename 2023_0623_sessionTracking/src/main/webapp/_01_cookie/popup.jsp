<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    //Cookie정보 읽어와서 방문목록에 기록
    
    Cookie [] cookies = request.getCookies();

   
StringBuffer sb = new StringBuffer("<h4>최근방문페이지</h4>");
  
if(cookies!=null){
	
for(Cookie  c : cookies){
    	
    	String name =  c.getName(); // A     B     C
    	String value=  c.getValue();// A.jsp B.jsp C.jsp
    	//System.out.println(name);
    	if(name.equals("JSESSIONID")==false){
    	    sb.append(String.format("<a href='%s'>%s</a><br>", value,name));
    	}
    }
    
	
}

    
    
    
    
%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
   #popup{
      width: 150px;
      height: 150px;
      padding: 20px;
      background: white;
      color: yellow;
      
      position: absolute;
      right: 50px;
   } 
   a{
   color: blue;
   }

</style>

</head>
<body>
<div id="popup"><%= sb.toString() %></div>
</body>
</html>