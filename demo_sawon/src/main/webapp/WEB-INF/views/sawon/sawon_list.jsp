<%@ page language='java' contentType='text/html;charset=UTF-8' pageEncoding='UTF-8' %>
<%@ taglib prefix='c'    	uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt'  	uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix='fn'		uri='http://java.sun.com/jsp/jstl/functions' %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body>
    <table border="1">
      <tr>
          <th>sabun</th>
          <th>saname</th>
          <th>sasex</th>
          <th>deptno</th>
          <th>sajob</th>
          <th>sahire</th>
          <th>samgr</th>
          <th>sapay</th>
      </tr>
      <c:forEach var="vo" items="${ list }">
           <tr>
                <td>${ vo.sabun }</td>
                <td>${ vo.saname }</td>
                <td>${ vo.sasex }</td>
                <td>${ vo.deptno }</td>
                <td>${ vo.sajob }</td>
                <td>${ vo.sahire }</td>
                <td>${ vo.samgr }</td>
                <td>${ vo.sapay }</td>
               
                
           </tr>
        </c:forEach>
  </body>
</html>
